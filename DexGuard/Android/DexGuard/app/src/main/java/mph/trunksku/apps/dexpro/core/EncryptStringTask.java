package mph.trunksku.apps.dexpro.core;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import org.apache.commons.lang3.StringEscapeUtils;
import org.jf.dexlib2.Opcodes;
import org.jf.dexlib2.dexbacked.DexBackedDexFile;
import org.jf.dexlib2.iface.ClassDef;
import org.jf.dexlib2.writer.builder.DexBuilder;
import org.jf.smali.Smali;
import org.jf.smali.SmaliOptions;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipFile;
import mph.trunksku.apps.dexpro.utils.ZipOut;
import mph.trunksku.apps.dexpro.utils.BYProtectUtil;
import mph.trunksku.apps.dexpro.dexmerger.DexUtils;
import mph.trunksku.apps.dexpro.utils.BES;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import mph.trunksku.apps.dexpro.utils.FileUtils;
import mph.trunksku.apps.dexpro.utils.FastFileWriter;
import android.os.Environment;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import mph.trunksku.apps.dexpro.logger.Log;
import java.io.ByteArrayOutputStream;

public class EncryptStringTask {


    static Context context;
    
    //Conf conf;
	public List<String> dexEntries = new ArrayList<>();
	
    List<String> pkgs=new ArrayList<>();

   // public boolean useKey = false;
    ZipFile zipFile;
    ZipOut zipOut;

    List<ClassDef> allClassDefs=new ArrayList<>();
    int ClassSize=0;
    private AtomicInteger ProcessedSize = new AtomicInteger(0);
    int strCount=0;

    long Allstart;
    long Allend;

    FastFileWriter tmpStringPool;
    FastFileWriter tmpMethodPool;

    HashMap<String,String> StringPoolMap=new HashMap<>();

    static final String BYDecoder_ClassType_src="Lcom/beingyi/encrypt/BYDecoder;";
    static final String StringPool_ClassType_src="Lcom/beingyi/encrypt/StringPool;";
    static final String MethodPool_ClassType_src="Lcom/beingyi/encrypt/MethodPool;";

    static final String BYDecoder_DecodeMethod_src="#decode#";

    String BYDecoder_ClassType;
    String StringPool_ClassType;
   String MethodPool_ClassType;
    String BYDecoder_DecodeMethod;


    public EncryptStringTask(Context context,String inputPath, String outputPath) throws Exception {
        //super(inputPath, outputPath);
		try {
            keys = inet("encryptString/keys.txt");
            method= inet("encryptString/method.smali");
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		String path = Environment.getExternalStorageDirectory() + "/DexProtect";
		new File(path + "/tmp").mkdirs();

		tmpStringPool=new FastFileWriter(new File(path+"/tmp/Strings.txt"),true);
		tmpMethodPool=new FastFileWriter(new File(path+"/tmp/Methods.txt"),true);
		
        this.context = context;
        
        //this.conf = new Conf(context);
        //useKey = new Conf(context).getUseKey();
        
        zipFile = new ZipFile(inputPath);
		readZip(zipFile);
        zipOut = new ZipOut(outputPath).setInput(zipFile);

        /*this.BYDecoder_ClassType=getObfusedTypeName(BYDecoder_ClassType_src);
        this.StringPool_ClassType=getObfusedTypeName(StringPool_ClassType_src);
        this.MethodPool_ClassType=getObfusedTypeName(MethodPool_ClassType_src);

        this.BYDecoder_DecodeMethod=getRandomStr(10);*/
		this.BYDecoder_ClassType=BYDecoder_ClassType_src;
        this.StringPool_ClassType=StringPool_ClassType_src;
        this.MethodPool_ClassType=MethodPool_ClassType_src;

        this.BYDecoder_DecodeMethod=BYDecoder_DecodeMethod_src;
        

    }
	
	private void readZip(ZipFile zip) throws Exception {
		Enumeration enums = zip.entries();
		while (enums.hasMoreElements()) {
			ZipEntry entry = (ZipEntry) enums.nextElement();
			String entryName = entry.getName();
			// System.out.println(entryName);
			//entries.add(entryName);

			if (entryName.startsWith("classes") && entryName.endsWith(".dex")) {
				dexEntries.add(entryName);
			}

			//if (entryName.startsWith("res/layout/") && entryName.endsWith(".xml")) {
				//resEntries.add(entryName);
			//}

		}

	}

    private void println(String str){
       /*activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fragment.tv_log.append(str+"\n");
                fragment.sc_log.scrollTo(0, fragment.tv_log.getMeasuredHeight()+100);
            }
        });*/

    }


    public void start(int threadCount,List<String> pkgs) throws Exception {
        this.pkgs.clear();
        this.pkgs.addAll(pkgs);
        try {
            tmpStringPool.getPath().delete();
            tmpMethodPool.getPath().delete();
            String MethodPoolSmali=inet("encryptString/MethodPool.smali").replace(MethodPool_ClassType_src,MethodPool_ClassType);
            Log.i("", MethodPoolSmali);
			tmpMethodPool.write(MethodPoolSmali);
        }catch (Exception e){
            e.printStackTrace();
			Log.i("", e.getMessage());
        }

        Allstart = System.currentTimeMillis();

        //println(context.getString(R.string.string_encryption_in_progress) + inputPath);

        HashMap<String,String> map = new HashMap<>();
        HashMap<String, DexBuilder> dexBuilders = new HashMap<>();
        for (String dexName : dexEntries) {
            DexBuilder dexBuilder = new DexBuilder(Opcodes.getDefault());
            dexBuilder.setIgnoreMethodAndFieldError(true);
            dexBuilders.put(dexName, dexBuilder);

            DexBackedDexFile dex = DexBackedDexFile.fromInputStream(Opcodes.getDefault(), getZipInputStream(dexName));
            List<ClassDef> classDefs = new ArrayList();
            for (ClassDef classDef : dex.getClasses()) {
                classDefs.add(classDef);

            }
            for (ClassDef classDef : classDefs) {
                map.put(classDef.getType(),dexName);
                allClassDefs.add(classDef);
            }

            classDefs.clear();

        }



        ClassSize=allClassDefs.size();

       /* activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fragment.progressBar.setMax(ClassSize);
            }
        });*/


        int pre=ClassSize/threadCount;

        List<Thread> threads=new ArrayList<>();
        for(int i=0;i<threadCount;i++){
            int start=pre * i;
            int end=pre * (i + 1);

            if(i==threadCount-1){
                end=allClassDefs.size();
            }

            DexThread dexThread = new DexThread(allClassDefs, start, end,map,dexBuilders);
            Thread thread=new Thread(dexThread);
            thread.start();

            threads.add(thread);
        }

        for(int i=0;i<threads.size();i++){
            threads.get(i).join();
        }


        for (String key : dexBuilders.keySet()) {
            DexBuilder dexBuilder = dexBuilders.get(key);
            zipOut.addFile(key, DexUtils.getDexBuilderData(dexBuilder));
        }

        DexBuilder dexBuilder=new DexBuilder(Opcodes.getDefault());
        dexBuilder.setIgnoreMethodAndFieldError(true);

        //println(context.getString(R.string.collecting_strings));

       // compilePool(dexBuilder);

        zipOut.addFile("classes"+(dexEntries.size()+1)+".dex",DexUtils.getDexBuilderData(dexBuilder));

        zipOut.save();

       // println(context.getString(R.string.file_out_puting)+outputPath);

        //signAPK();

        Allend = System.currentTimeMillis();
        long total = (Allend - Allstart) / 1000;

        //println(context.getString(R.string.finished_in)+total+"s");

        release();

      //showFinish(context,new File(outputPath));

    }
	
	public InputStream getZipInputStream(String entry) throws IOException {
		
		return new ByteArrayInputStream(FileUtils.toByteArray(zipFile.getInputStream(zipFile.getEntry(entry))));
	}

    private void release(){
        ProcessedSize.addAndGet(0);
        CurStringCount=0;

        /*activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fragment.tv_label.setText(context.getString(R.string.finished_processing));
                fragment.greenBtn();
            }
        });*/

    }

    int CurStringCount=0;
    private void compilePool(DexBuilder dexBuilder)throws Exception{

        StringBuilder fieldContent=new StringBuilder();
        StringBuilder fieldValueContent=new StringBuilder();

        int max=StringPoolMap.size();

       /* activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fragment.progressBar.setMax(max);
            }
        });*/

        for(String key:StringPoolMap.keySet()){

            String field=key;
            String enStr=StringPoolMap.get(key);

            //println("field->"+field);

            //fieldContent+="\n.field public static final "+field+":Ljava/lang/String; = \""+enStr+"\"\n";
            fieldContent.append("\n.field public static "+field+":Ljava/lang/String;\n");

            fieldValueContent.append("    const-string v0, \""+enStr.replace("\r","")+"\"\n");
            fieldValueContent.append("    sput-object v0, Lcom/beingyi/encrypt/StringPool;->"+field+":Ljava/lang/String;\n");

            CurStringCount++;
           /* activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    fragment.progressBar.setProgress(CurStringCount);
                    fragment.tv_label.setText(context.getString(R.string.processing_character)+ CurStringCount +"/"+max);
                }
            });*/


        }

        String StringPoolSmali=inet("encryptString/StringPool.smali");
        StringPoolSmali=StringPoolSmali.replace("#fields",fieldContent);
        StringPoolSmali=StringPoolSmali.replace("#input",fieldValueContent);
        StringPoolSmali=StringPoolSmali.replace(StringPool_ClassType_src,StringPool_ClassType);

		Log.i("", StringPoolSmali);
        Smali.assembleSmaliFile(StringPoolSmali, dexBuilder, new SmaliOptions());
        Smali.assembleSmaliFile(inet("encryptString/BYDecoder.smali").replace(BYDecoder_ClassType_src,BYDecoder_ClassType).replace(StringPool_ClassType_src,StringPool_ClassType).replace(BYDecoder_DecodeMethod_src,BYDecoder_DecodeMethod), dexBuilder, new SmaliOptions());

       // println(context.getString(R.string.processing_method_collection));

        Smali.assembleSmaliFile(tmpMethodPool.getPath(), dexBuilder, new SmaliOptions());

    }

    

    private void dealClassDef(ClassDef classDef,DexBuilder dexBuilder) throws Exception {
        String smali = DexUtils.getSmali(classDef);
        smali = dealSmali(smali, classDef.getType());
        //println(smali);

        Smali.assembleSmaliFile(smali, dexBuilder, new SmaliOptions());

    }


    private String dealSmali(String smali, String type) throws Exception {
        StringBuilder result = new StringBuilder();
        String[] lines=smali.split("\n");

        for (String line:lines) {
            Matcher m = Pattern.compile("const-string ([vp]\\d{1,2}), \"(.*)\"").matcher(line);
            if (m.find()) {
                String tmp = m.group(2);
                if (tmp.equals("")) {
                    result.append(line).append("\n");
                    continue;
                }

                String register = m.group(1);
                tmp = StringEscapeUtils.unescapeJava(tmp);
                println("String->"+tmp);

                strCount++;

                //String methodName= getRandomLitter(5)+getRandomStr(15)+ getRandomLitter(5);
                String methodName = "decode";
				String pass= BES.MD5.encode16(type);
                //String fieldName= getRandomLitter(5)+getRandomStr(15)+ getRandomLitter(5);
                String fieldName = "test";
                String enStr = BES.encode(tmp, pass);
                StringPoolMap.put(fieldName, enStr);

                String methodSmali=createMethod(methodName, BES.MD5.encode16(pass),fieldName);
                tmpMethodPool.write(methodSmali);
                //println(methodSmali);

                String sign = "    const-string " + register + ", " + "\"\"";
                String dec = "";
                if (Integer.parseInt(register.substring(1)) > 15 && register.startsWith("v")) {
                    //dec = "    invoke-static/range {" + register + " .. " + register + "}, " + type + "->" + methodName + "(Ljava/lang/String;)Ljava/lang/String;";
                    dec = "    invoke-static {}, " + MethodPool_ClassType + "->" + methodName + "()Ljava/lang/String;";
                } else if (register.startsWith("v") || (register.startsWith("p") && Integer.parseInt(register.substring(1)) < 10)) {
                    dec = "    invoke-static {}, " + MethodPool_ClassType + "->" + methodName + "()Ljava/lang/String;";
                } else {
                    result.append(line).append("\n");
                    continue;
                }
                String mov = "    move-result-object " + register;
                result.append(sign).append("\n\n");
                result.append(dec).append("\n\n");
                result.append(mov).append("\n");
            } else {
                result.append(line).append("\n");
            }
        }



        return result.toString();
    }

    private String createMethod(String methodName,String pass,String fieldName){
        //StringBuilder sb=new StringBuilder();
        //sb.append("\n");
        //sb.append("\n");
        return method.replaceFirst("#decode",methodName).replace("#pass",pass).replace("#field",fieldName).replace(BYDecoder_ClassType_src,BYDecoder_ClassType).replace(StringPool_ClassType_src,StringPool_ClassType).replace(BYDecoder_DecodeMethod_src,BYDecoder_DecodeMethod);
    }


    private static String keys;
    private static String method;

    static {
       
    }


    private String getObfusedTypeName(String type) {
        int length = 0;
        String[] types = type.split("/");
        if (types != null) {
            length = types.length;
        } else {
            length = 1;
        }

        StringBuffer sb = new StringBuffer();
        sb.append("L");
        for (int i = 0; i < length; i++) {
            sb.append(getRandomStr2(10) + "/");
        }
        sb.append(";");

        String result = sb.toString();


        return result.replace("/;", ";");
    }



    private String getRandomStr2(int length) {
        String[] keyArray = keys.split("[\\n\\r]");
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        for (int i = 0; i < length; i++) {
            int num = r.nextInt(keyArray.length);
            sb.append(keyArray[num]);
        }
        return sb.toString().replace(".", "");
    }

    private static String getRandomLitter2(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < length; ++i) {
            sb.append(str.charAt(random.nextInt(str.length())));
        }
        return sb.toString();
    }


    class DexThread extends Thread{

        List<ClassDef> classDefs;
        int start;
        int end;
        HashMap<String,String> map;
        HashMap<String, DexBuilder> dexBuilders;
        public DexThread(List<ClassDef> classDefs,int start,int end,HashMap<String,String> map,HashMap<String, DexBuilder> dexBuilders){
            this.classDefs=classDefs;
            this.start=start;
            this.end=end;
            this.map=map;
            this.dexBuilders=dexBuilders;
        }

        @Override
        public void run() {
            super.run();

            //println(currentThread().getName()+context.getString(R.string.running));
            try {

                for (int i = start; i < end; i++) {
                    ClassDef classDef = classDefs.get(i);

                    DexBuilder dexBuilder=dexBuilders.get(map.get(classDef.getType()));

                    if(pkgs.size()==0){
                        dealClassDef(classDef, dexBuilder);
                    }else {
                        boolean encrypt=false;
                        for (String pkg : pkgs) {
                            if (classDef.getType().startsWith(pkg)) {
                                encrypt = true;
                                break;
                            }
                        }
                        if (encrypt) {
                            dealClassDef(classDef, dexBuilder);
                        } else {
                            dexBuilder.internClassDef(classDef);
                        }
                    }

                    addCount();

                }

            }catch (Exception e){
                e.printStackTrace();
            }

        }

        private synchronized void addCount(){
           /* activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    int count = ProcessedSize.incrementAndGet();
                    fragment.progressBar.setProgress(count);
                    fragment.tv_label.setText(context.getString(R.string.compiling)+count+"/"+ClassSize);
                }
            });*/
        }

    }

	public static String inet(String fileName)
    {
        try
        {
            InputStream localInputStream = context.getAssets().open(fileName);
            byte[] arrayOfByte = new byte[localInputStream.available()];
            localInputStream.read(arrayOfByte);
            localInputStream.close();
            String str = new String(arrayOfByte, "utf-8");
            return str;
        }
        catch (Exception localIOException)
        {
            localIOException.printStackTrace();
			Log.i("", localIOException.getMessage());
        }
        return "";
    }
	
	public  static String inet2( String name) {
        try {
            InputStream openRawResource = context.getAssets().open(name);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            for (int read = openRawResource.read(); read != -1; read = openRawResource.read()) {
                byteArrayOutputStream.write(read);
            }
            openRawResource.close();
            return byteArrayOutputStream.toString();
        } catch (Exception e) {
            e.printStackTrace();
			// Toast.makeText(context, e.getMessage(), 0).show();
            return "";
        }

    }
    

    



}

