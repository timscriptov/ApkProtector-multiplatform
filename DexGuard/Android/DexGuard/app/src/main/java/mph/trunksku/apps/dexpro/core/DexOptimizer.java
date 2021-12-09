package mph.trunksku.apps.dexpro.core;
import com.android.dexj.Dex;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import mph.trunksku.apps.dexpro.dexmerger.DexFileComparator;
import mph.trunksku.apps.dexpro.dexmerger.DexFileFilter;
import mph.trunksku.apps.dexpro.dexmerger.DexUtils;
import mph.trunksku.apps.dexpro.dexmerger.DxContext;
import mph.trunksku.apps.dexpro.dexmerger.MultDexMerger;
import mph.trunksku.apps.dexpro.logger.DebugLog;
import mph.trunksku.apps.dexpro.logger.Log;
import mph.trunksku.apps.dexpro.utils.FileUtils;
import org.jf.dexlib2.DexFileFactory;
import org.jf.dexlib2.Opcodes;
import org.jf.dexlib2.dexbacked.DexBackedDexFile;
import org.jf.dexlib2.iface.ClassDef;
import org.jf.dexlib2.iface.DexFile;
import org.jf.dexlib2.iface.reference.MethodReference;
import org.jf.dexlib2.immutable.ImmutableDexFile;
import org.jf.dexlib2.rewriter.ClassDefRewriter;
import org.jf.dexlib2.rewriter.DexRewriter;
import org.jf.dexlib2.rewriter.Rewriter;
import org.jf.dexlib2.rewriter.RewriterModule;
import org.jf.dexlib2.rewriter.Rewriters;
import org.jf.dexlib2.writer.pool.DexPool;
import org.jf.dexlib2.writer.builder.DexBuilder;
import com.google.common.collect.Lists;
import org.jf.smali.Smali;
import org.jf.smali.SmaliOptions;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import mph.trunksku.apps.dexpro.model.ReplaceItem;
import mph.trunksku.apps.dexpro.MyApp;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;
import java.util.Locale;
import org.jf.dexlib2.iface.Method;

public class DexOptimizer {
    List<ClassDef> classes = new ArrayList<>();

    public void optimize(String path, String dex, String dex2) throws Exception {
        DexFile dexFile = DexFileFactory.loadDexFile(new File(dex).getAbsolutePath(), Opcodes.getDefault());

		for (ClassDef classDef : dexFile.getClasses()) {
            classes.add(classDef);
        }
        DexFile dexFile2 = DexFileFactory.loadDexFile(new File(dex2).getAbsolutePath(), Opcodes.getDefault());
        for (ClassDef classDef : dexFile2.getClasses()) {
            classes.add(classDef);
        }
        DexFileFactory.writeDexFile(path + "/classes.dex", new ImmutableDexFile(Opcodes.getDefault(), classes));
        new File(new StringBuffer().append(path).append("/classes-op.dex").toString()).delete();
    }

    public void merge_multidex(String path) throws Exception {
        List<File> array2List = DexUtils.fileList(new File(path).listFiles(new DexFileFilter()));
        if (array2List == null) {
            throw new IOException("Failed to get DEX list");
        } else if (array2List.isEmpty()) {
            throw new IOException("No DEX file");
        } else {
            if (array2List.size() > 1) {
                Collections.sort(array2List, new DexFileComparator());
                MultDexMerger.Builder builder = new MultDexMerger.Builder(new DxContext());
                builder.add(array2List.toArray(new File[0]));
                /*for (int i = 0; i < array2List.size(); i++) {
                 //this.mContext.out.println(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append("[").append(i).toString()).append("] ").toString()).append(((File) array2List.get(i)).getName()).toString());
                 }*/
                try {
                    List merger = builder.create().merger();
                    for (File delete : array2List) {
                        delete.delete();
                    }
                    for (int i2 = 0; i2 < merger.size(); i2++) {
                        String str;
                        Dex dex = (Dex) merger.get(i2);
                        String str2 = "classes%s.dex";
                        if (i2 < 1) {
                            str = "";
                        } else {
                            str = String.valueOf(i2 + 1);
                        }
                        str = String.format(str2, new Object[]{str});
                        //this.mContext.out.println(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append("[").append(i2).toString()).append("] ").toString()).append(str).toString());
                        File file2 = new File(path);
                        if (!file2.exists()) {
                            file2.mkdirs();
                        }
                        dex.writeTo(new File(file2, str));
                    }

                    throw new IOException("Fix MultiDex");
                } catch (IOException e2) {
                    //this.mContext.err.println("DEX merge failed");
                    throw e2;
                }
            } else {
                throw new IOException("Fix MultiDex Fail!");
            }

        }
    }

    public boolean rename_all_class(String path) {
        try {
            DexBackedDexFile dexFile = DexFileFactory.loadDexFile(path, Opcodes.getDefault());
            DexRewriter rewriter = new DexRewriter(new RewriterModule() {
                    public Rewriter<String> getTypeRewriter(Rewriters rewriters) {
                        return new Rewriter<String>() {
                            // ResGuardStringBuilder resN = new ResGuardStringBuilder();
                            int i = 0;
                            public String rewrite(String value) {
                                if (value.startsWith("Lxyz/dexpro/engine/tc")) {
                                    i++ ;
                                    String[] bits = value.replace(".", "/").replace("L", "").replace(";", "").split("/");
                                    String lastOne = bits[bits.length - 1];
                                    return "Ltr/obs/" + createName(lastOne) + ";";
                                    //return value.replace(lastOne, IntToLetter(i));
                                } else {
                                    return value;
                                }
                            }
                        };
                    }
                });
            DexFile rewrittenDexFile = rewriter.rewriteDexFile(dexFile);
            DexPool.writeTo(path + ".tmp", rewrittenDexFile);
            /*  OutputStream outputTmp = new FileOutputStream(output + ".tmp");
             outputTmp.write(dexStringEncryptor.getData());
             outputTmp.close();*/
            FileUtils.copyFile(path + ".tmp", path);
            // new File(path + ".tmp").delete();
            return true;
        } catch (Exception e) {
            DebugLog.i("", e.getMessage());
            return false;
        }
    }

    public String createSequenceElement(int index) {
        String sequenceElement = "";
        int first  = index / 26;
        int second = index % 26;
        if (first < 1) {
            sequenceElement +=  (char) ('A' + second);
        } else {
            sequenceElement +=  createSequenceElement(first) + (char) ('A' + second);
        }
        return sequenceElement ;
    }

    public static String IntToLetter(int Int) {
        if (Int < 27) {
            return Character.toString((char)(Int + 96));
        } else {
            if (Int % 26 == 0) {
                return IntToLetter((Int / 26) - 1) + IntToLetter(((Int - 1) % 26 + 1));
            } else {
                return IntToLetter(Int / 26) + IntToLetter(Int % 26);
            }
        }
    }

    public static String getNextAlphaCharSequence(String charSeqStr) {

        String nextCharSeqStr       = null;
        char[] charSeqArr           = null;
        boolean isResetAllChar      = false;
        boolean isResetAfterIndex   = false;
        Integer resetAfterIndex     = 0;

        if (StringUtils.isBlank(charSeqStr)) {
            charSeqArr = new char[] { 'A' };
        } else {
            charSeqArr = charSeqStr.toCharArray();
            Integer charSeqLen = charSeqArr.length;

            for (int index = charSeqLen - 1; index >= 0; index--) {
                char charAtIndex = charSeqArr[index];

                if (Character.getNumericValue(charAtIndex) % 35 == 0) {
                    if (index == 0) {
                        charSeqArr = Arrays.copyOf(charSeqArr, charSeqLen + 1);
                        isResetAllChar = true;
                    } else {
                        continue;
                    }
                } else {
                    char nextCharAtIndex = (char) (charAtIndex + 1);
                    charSeqArr[index] = nextCharAtIndex;
                    if (index + 1 < charSeqLen) {
                        isResetAfterIndex = true;
                        resetAfterIndex = index;
                    }
                    break;
                }
            }
            charSeqLen = charSeqArr.length;
            if (isResetAllChar) {
                for (int index = 0; index < charSeqLen; index++) {
                    charSeqArr[index] = 'A';
                }
            } else if (isResetAfterIndex) {
                for (int index = resetAfterIndex + 1; index < charSeqLen; index++) {
                    charSeqArr[index] = 'A';
                }
            }
        }

        nextCharSeqStr = String.valueOf(charSeqArr);
        return nextCharSeqStr;
    }

    /*public static void main(String args[]) {
     String nextAlphaSequence = null;
     for (int index = 0; index < 1000; index++) {
     nextAlphaSequence = getNextAlphaCharSequence(nextAlphaSequence);
     System.out.print(nextAlphaSequence + ",");
     }
     }*/


    private ArrayList<String> mMakeName = new ArrayList();
    private String charra1 = "";
    public String makeName() {
        String n = getNextAlphaCharSequence(null);
        if (checkN(n)) {
            n = makeName();
        } else {
            mMakeName.add(n);
        }

        return n;
    }

    public boolean checkN(String n) {
        boolean isExist = false;
        for (String name : mMakeName) {
            if (name.equals(n)) {
                isExist = true;
                break;
            } else {
                isExist = false;
            }
        }
        return isExist;
    }

    private static char chara = 'a';

    private static Map<String, String> mUsingName = new HashMap<String, String>(16);

    public static String createName(String type) {
       /* String[] arr = inet("encryptString/keys.txt").split("\n");
        chara = arr[0];*/
        String name = mUsingName.get(type);
        if (name == null) {
            name = String.valueOf(chara);
            mUsingName.put(type, name);
            return name;
        } else {
            char[] chars = name.toCharArray();
            StringBuilder stringBuilder = new StringBuilder(chars.length);
            if (add(chars, chars.length - 1)) {
                stringBuilder.append(chara);
            }
            stringBuilder.append(chars);
            mUsingName.put(type, stringBuilder.toString());
            return stringBuilder.toString();
        }
    }

    private static boolean add(char[] c, int index) {
        //String[] arr = inet("encryptString/keys.txt").split("\n");

        if (c[index] < /*arr[arr.length - 1].charAt(0)*/ 'z') {
            c[index]++;
            return false;
        } else {
            c[index] = chara;
            if (index > 0) {
                return add(c , index - 1);
            } else {
                return true;
            }
        }
    }

    public  static String inet(String name) {
        try {
            InputStream openRawResource = MyApp.app().getAssets().open(name);
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

    public boolean rename_class(String path, final String old, final String change) {
        try {
            DexBackedDexFile dexFile = DexFileFactory.loadDexFile(path, Opcodes.getDefault());
            DexRewriter rewriter = new DexRewriter(new RewriterModule() {
                    public Rewriter<String> getTypeRewriter(Rewriters rewriters) {
                        return new Rewriter<String>() {
                            public String rewrite(String value) {


                                return value.replace(old.replace(".", "/"), change.replace(".", "/"));
                            }
                        };
                    }
                });
            DexFile rewrittenDexFile = rewriter.rewriteDexFile(dexFile);
            DexPool.writeTo(path, rewrittenDexFile);
            return true;
        } catch (Exception e) {
            Log.d("|", e.getMessage());
            return false;
        }
    }

    public void rename(String path, String old, String bago) throws Exception {
        DexBackedDexFile dex = DexBackedDexFile.fromInputStream(Opcodes.getDefault(), new ByteArrayInputStream(FileUtils.cloneInputStream(new FileInputStream(path)).toByteArray()));
        DexBuilder dexBuilder = new DexBuilder(Opcodes.getDefault());
        dexBuilder.setIgnoreMethodAndFieldError(true);

        //List<ClassDef> classDefs = (List<ClassDef>) Lists.newArrayList(dex.getClasses());
        List<ClassDef> classDefs = new ArrayList<>();
        for (ClassDef classDef : dex.getClasses()) {
            classDefs.add(classDef);
        }
        for (int i = 0; i < classDefs.size(); i++) {
            String type = classDefs.get(i).getType();
            String pkg = DexUtils.getPkgNameByType(type);

            /*if (pkg.equals("android.app.Application")) {
             }*/
            try {

                /* if (type.startsWith(old.replace(".", "/"))) {

                 }*/

                //if (type.contains(old.replace(".", "/"))) {
                // callBack.onProgress(i, classDefs.size());
                //DebugLog.i("", classDefs.get(i).getType());
                String smali = DexUtils.getSmali(classDefs.get(i));
                smali = smali.replace(old.replace(".", "/"), bago.replace(".", "/"));
                //smali = dealSmali(smali);
                //System.out.println(classDef.getType());
                Smali.assembleSmaliFile(smali, dexBuilder, new SmaliOptions());
                // dealClassDef( classDefs.get(i));
                // } else {
                //  dexBuilder.internClassDef(classDefs.get(i));
                //}


            } catch (Exception e) {
                DebugLog.i("", e.getMessage());
                break;
            }
            //dealClassDef(dexBuilder, classDefs.get(i));


        }

        //ByteArrayOutputStream baos = FileUtils.cloneInputStream(new FileInputStream(path));
        OutputStream output = new FileOutputStream(path);
		output.write(DexUtils.getDexBuilderData(dexBuilder));
        output.close();
    }
    
    public void obfuscate1(String path, ArrayList<ReplaceItem> item) throws Exception {
        DexBackedDexFile dex = DexBackedDexFile.fromInputStream(Opcodes.getDefault(), new ByteArrayInputStream(FileUtils.cloneInputStream(new FileInputStream(path)).toByteArray()));
        DexBuilder dexBuilder = new DexBuilder(Opcodes.getDefault());
        dexBuilder.setIgnoreMethodAndFieldError(true);
        List<String> keeps = new ArrayList<>();
        keeps.add("adrt");

        //List<ClassDef> classDefs = (List<ClassDef>) Lists.newArrayList(dex.getClasses());
        List<ClassDef> classDefs = new ArrayList<>();
        for (ClassDef classDef : dex.getClasses()) {
            classDefs.add(classDef);
        }
        for (int i = 0; i < classDefs.size(); i++) {
            String type = classDefs.get(i).getType();
            String pkg = DexUtils.getPkgNameByType(type);

            /*if (pkg.equals("android.app.Application")) {
             }*/
            try {


                boolean encrypt=true;
                for (String cla : keeps) {
                    if (type.startsWith(DexUtils.getTypeByPkg(cla))) {
                        encrypt = false;
                        break;
                    }else if(type.contains(DexUtils.getTypeByPkg("BuildConfig"))){
                        encrypt = false;
                        break;
                    }
                }
                if (encrypt) {
                    String smali = DexUtils.getSmali(classDefs.get(i));
                    smali = smali.replace(findFromLines(smali, ".source"), "\"DexPro_Security.java\"");
                    for (int pos = 0; pos < item.size(); pos++) {

                        smali = smali.replace(item.get(pos).getOld(), item.get(pos).getNew());
                    }
                    if (findFromLines(smali, ".super").equals(DexUtils.getTypeByPkg("android.app.Application"))) {

                    }else{
                        String[] bits = type.replace(".", "/").replace("L", "").replace(";", "").split("/");
                        String lastOne = bits[bits.length - 1];
                        smali = smali.replace(type, DexUtils.getTypeByPkg("tc.n"+"."+IntToLetter(i+1)));
                    }
                    Smali.assembleSmaliFile(smali, dexBuilder, new SmaliOptions());

                }



            } catch (Exception e) {
                DebugLog.i("", e.getMessage());
                break;
            }
            //dealClassDef(dexBuilder, classDefs.get(i));


        }

        //ByteArrayOutputStream baos = FileUtils.cloneInputStream(new FileInputStream(path));
        OutputStream output = new FileOutputStream(path);
        output.write(DexUtils.getDexBuilderData(dexBuilder));
        output.close();
    }
    ArrayList<ReplaceItem> Obfitem = new ArrayList<>();
    
    public void obfuscate(String path) throws Exception {
        DexBackedDexFile dex = DexBackedDexFile.fromInputStream(Opcodes.getDefault(), new ByteArrayInputStream(FileUtils.cloneInputStream(new FileInputStream(path)).toByteArray()));
        DexBuilder dexBuilder = new DexBuilder(Opcodes.getDefault());
        dexBuilder.setIgnoreMethodAndFieldError(true);
        //ArrayList<ReplaceItem> item = new ArrayList<>();
        List<String> keeps = new ArrayList<>();
        keeps.add("adrt");
        
        //List<ClassDef> classDefs = (List<ClassDef>) Lists.newArrayList(dex.getClasses());
       // for(ClassDefItem classItem: dex.ClassDefsSection.getItems()){
        List<ClassDef> classDefs = new ArrayList<>();
        for (ClassDef classDef : dex.getClasses()) {
            classDefs.add(classDef);
        }
        for (int i = 0; i < classDefs.size(); i++) {
            //Iterable<? extends Method> method = String.join("",classDefs.get(i).getMethods());
            
            String sup = classDefs.get(i).getSuperclass();
            String source = classDefs.get(i).getSourceFile();
           // String source = classDefs.get(i).;
            String type = classDefs.get(i).getType();
            String pkg = DexUtils.getPkgNameByType(type);
            try {
              //  System.out.println(type);
                boolean encrypt=true;
                for (String cla : keeps) {
                    if (type.startsWith(DexUtils.getTypeByPkg(cla))) {
                        encrypt = false;
                        break;
                    }else if(type.contains(DexUtils.getTypeByPkg("BuildConfig"))){
                        encrypt = false;
                        break;
                    }
                }
                if (encrypt) {
                    String smali = DexUtils.getSmali(classDefs.get(i));
                    //smali = smali.replace(findFromLines(smali, ".source"), "\"DexPro_Security.java\"");
                    
                    if (findFromLines(smali, ".super").equals(DexUtils.getTypeByPkg("android.app.Application"))) {

                    }/*else if(pkg.startsWith("xyz.dexpro.engine.d.d")){
                        
                    }*/else{
                        String[] bits = type.replace(".", "/").replace("L", "").replace(";", "").split("/");
                        String lastOne = bits[bits.length - 1];
                        String newN = DexUtils.getTypeByPkg("dp."+createName("smali")+"."+lastOne);
                        smali = smali.replace(type, newN);
                        Obfitem.add(new ReplaceItem(type, newN));
                    }
                    for (int pos = 0; pos < Obfitem.size(); pos++) {
                        smali = smali.replace(Obfitem.get(pos).getOld(), Obfitem.get(pos).getNew());
                        //System.out.println(item.get(pos).getOld() + " -> " + item.get(pos).getNew());
                    }
                    
                    Smali.assembleSmaliFile(smali, dexBuilder, new SmaliOptions());

                }
            } catch (Exception e) {
                DebugLog.i("", e.getMessage());
                break;
            }
           
        }

        //ByteArrayOutputStream baos = FileUtils.cloneInputStream(new FileInputStream(path));
        OutputStream output = new FileOutputStream(path);
        output.write(DexUtils.getDexBuilderData(dexBuilder));
        output.close();
        reobfuscate(path);
    }
    
    public void reobfuscate(String path) throws Exception {
        DexBackedDexFile dex = DexBackedDexFile.fromInputStream(Opcodes.getDefault(), new ByteArrayInputStream(FileUtils.cloneInputStream(new FileInputStream(path)).toByteArray()));
        DexBuilder dexBuilder = new DexBuilder(Opcodes.getDefault());
        dexBuilder.setIgnoreMethodAndFieldError(true);
        
        //List<ClassDef> classDefs = (List<ClassDef>) Lists.newArrayList(dex.getClasses());
        List<ClassDef> classDefs = new ArrayList<>();
        for (ClassDef classDef : dex.getClasses()) {
            classDefs.add(classDef);
        }
        for (int i = 0; i < classDefs.size(); i++) {
            String type = classDefs.get(i).getType();
            String pkg = DexUtils.getPkgNameByType(type);
            try {
                
                    String smali = DexUtils.getSmali(classDefs.get(i));
                    //smali = smali.replace(findFromLines(smali, ".source"), "\"DexPro_Security.java\"");

                    
                for (int pos = 0; pos < Obfitem.size(); pos++) {
                    smali = smali.replace(Obfitem.get(pos).getOld(), Obfitem.get(pos).getNew());
                        //System.out.println(item.get(pos).getOld() + " -> " + item.get(pos).getNew());
                    }
                    Smali.assembleSmaliFile(smali, dexBuilder, new SmaliOptions());

                
            } catch (Exception e) {
                DebugLog.i("", e.getMessage());
                break;
            }

        }

        //ByteArrayOutputStream baos = FileUtils.cloneInputStream(new FileInputStream(path));
        OutputStream output = new FileOutputStream(path);
        output.write(DexUtils.getDexBuilderData(dexBuilder));
        output.close();
    }

    public void replace(String path, ArrayList<ReplaceItem> item) throws Exception {
        DexBackedDexFile dex = DexBackedDexFile.fromInputStream(Opcodes.getDefault(), new ByteArrayInputStream(FileUtils.cloneInputStream(new FileInputStream(path)).toByteArray()));
        DexBuilder dexBuilder = new DexBuilder(Opcodes.getDefault());
        dexBuilder.setIgnoreMethodAndFieldError(true);
        List<String> keeps = new ArrayList<>();
        keeps.add("adrt");
        
       // List<ClassDef> classDefs = (List<ClassDef>) Lists.newArrayList(dex.getClasses());
        List<ClassDef> classDefs = new ArrayList<>();
        for (ClassDef classDef : dex.getClasses()) {
            classDefs.add(classDef);
        }
        for (int i = 0; i < classDefs.size(); i++) {
            String type = classDefs.get(i).getType();
            String pkg = DexUtils.getPkgNameByType(type);

            /*if (pkg.equals("android.app.Application")) {
             }*/
            try {

                
                boolean encrypt=true;
                for (String cla : keeps) {
                    if (type.startsWith(DexUtils.getTypeByPkg(cla))) {
                        encrypt = false;
                        break;
                    }else if(type.endsWith("BuildConfig;")){
                        encrypt = false;
                        break;
                    }else if (type.endsWith("R;") || type.contains("R$")) {
                        encrypt = false;
                        break;
                    }
                }
                if (encrypt) {
                    String smali = DexUtils.getSmali(classDefs.get(i));
                    smali = smali.replace(findFromLines(smali, ".source"), "\"DexPro_Security.java\"");
                    for (int pos = 0; pos < item.size(); pos++) {
                        
                        smali = smali.replace(item.get(pos).getOld(), item.get(pos).getNew());
                    }
                    
                    Smali.assembleSmaliFile(smali, dexBuilder, new SmaliOptions());
                    
                }



            } catch (Exception e) {
                DebugLog.i("", e.getMessage());
                break;
            }
            //dealClassDef(dexBuilder, classDefs.get(i));


        }

        //ByteArrayOutputStream baos = FileUtils.cloneInputStream(new FileInputStream(path));
        OutputStream output = new FileOutputStream(path);
        output.write(DexUtils.getDexBuilderData(dexBuilder));
        output.close();
    }

    private String findFromLines(String lines, String text) {
        int lineNumber = 0;
        String tagString = "";
        String m_str = "";
        
        try {
            for (String line : lines.split("\\r?\\n")) {
                lineNumber++;
                String[] items = line.split("\\s+");
                if (items.length < 2) {
                    continue;
                }
                tagString = items[0].toLowerCase(Locale.ENGLISH).trim();
                if (!tagString.startsWith("#")) {
                    if (tagString.equals(text)) {
                        m_str = items[1];
                    } 

                }
            }
            return m_str;
        } catch (Exception e) {
            return m_str;
        }
    }

    public static void EncryptString(String input, String output, DexStringEncryptor.EncryptCallBack listen) {

		try {
            ByteArrayOutputStream baos = FileUtils.cloneInputStream(new FileInputStream(input));

            OutputStream outputTmp = new FileOutputStream(output + ".tmp");
			DexStringEncryptor dexStringEncryptor=new DexStringEncryptor(baos.toByteArray(), null, listen);
			dexStringEncryptor.start();
			//mph.trunksku.apps.dexpro.dexmerger.DexStringEncryptor encryptor = new mph.trunksku.apps.dexpro.dexmerger.DexStringEncryptor(baos.toByteArray(), null);
            // output.write(encryptor.getData());
		    outputTmp.write(dexStringEncryptor.getData());
			outputTmp.close();
            FileUtils.copyFile(output + ".tmp", output);
            new File(output + ".tmp").delete();
		} catch (Exception e) {

		}

	}

    public void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[2048];
        while (true) {
            int read = inputStream.read(bArr);
            int i = read;
            if (read != -1) {
                outputStream.write(bArr, 0, i);
            } else {
                return;
            }
        }
    }
}
