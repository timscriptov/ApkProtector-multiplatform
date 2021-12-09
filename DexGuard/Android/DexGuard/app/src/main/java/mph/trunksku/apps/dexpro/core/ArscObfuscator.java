package mph.trunksku.apps.dexpro.core;

import com.iyfeng.arsceditor.AndrolibResources;
import com.iyfeng.arsceditor.ResDecoder.ARSCCallBack;
import com.iyfeng.arsceditor.ResDecoder.data.ResTable;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Map;
import mph.trunksku.apps.dexpro.logger.DebugLog;
import mph.trunksku.apps.dexpro.MyApp;

public class ArscObfuscator {
    AndrolibResources mAndRes;
    List<String> configs=new ArrayList<>();
    List<String> types=new ArrayList<>();
    List<String> keys=new ArrayList<>();
    List<String> values=new ArrayList<>();

    List<String> changedValues=new ArrayList<>();
    List<String> resTypes=new ArrayList<>();
    List<String> resValues=new ArrayList<>();
    HashMap<String,String> obfusedMap=new HashMap<>();

    byte[] input;

    public ArscObfuscator(InputStream in)throws Exception{

        resTypes.add("layout");
        resTypes.add("mipmap");
        resTypes.add("menu");
        resTypes.add("drawable");
        resTypes.add("anim");
        resTypes.add("animator");
        resTypes.add("xml");
        resTypes.add("interpolator");
        resTypes.add("color");
        resTypes.add("raw");
		resTypes.add("transition");

        this.input=toByteArray(in);

        byte[] newBytes=input.clone();
        mAndRes=new AndrolibResources();
        ResTable resTable=mAndRes.getResTable(new ByteArrayInputStream(newBytes));

        ARSCCallBack callback = new ARSCCallBack() {
            @Override
            public void back(String config, String type, String key, String value) {

                if (type != null) {
                    //System.out.println("type="+type+",key="+key+",value="+value);

                    configs.add(config);
                    types.add(type);
                    keys.add(key);
                    values.add(value);
                    changedValues.add("");

                    if(resTypes.contains(type)&& value.startsWith("res/")){
                        resValues.add(value);
                    }

                }

            }
        };

        mAndRes.decodeARSC(resTable, callback);
		DebugLog.i("", "Shrink Starting...");
		DebugLog.i("", "Resources Count: "+resValues.size());
		
        for(String value:resValues){
            String suffix = getSuffix(value);
            String obfusedValue="r/"+createName(suffix)+suffix;
            obfusedMap.put(value, obfusedValue);
            mAndRes.mARSCDecoder.replace(value, obfusedValue);
            
        }
		DebugLog.i("", "Shrink Completed");
		
		//DebugLog.i("", createName("xml"));

    }

	
	private static String chara = "a";

    private Map<String, String> mUsingName = new HashMap<String, String>(16);

    public String createName(String type){
        String[] arr = inet("encryptString/keys.txt").split("\n");
        chara = arr[0];
        String name = mUsingName.get(type);
        if(name == null){
            name = String.valueOf(chara);
            mUsingName.put(type, name);
            return name;
        }else{
            char[] chars = name.toCharArray();
            StringBuilder stringBuilder = new StringBuilder(chars.length);
            if(add(chars, chars.length -1)){
                stringBuilder.append(chara);
            }
            stringBuilder.append(chars);
            mUsingName.put(type, stringBuilder.toString());
            return stringBuilder.toString();
        }
    }

    private boolean add(char[] c, int index){
        String[] arr = inet("encryptString/keys.txt").split("\n");
        
        if(c[index] < arr[arr.length - 1].charAt(0)){
            c[index]++;
            return false;
        }else{
            c[index] = chara.charAt(0);
            if(index > 0){
                return add(c , index-1);
            }else{
                return true;
            }
        }
    }
	
    public  static String inet( String name) {
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
	
    public static String getSuffix(String path){
        String result="";
        if(new File(path).isDirectory()){
            return "";
        }
        result=path.subSequence(path.lastIndexOf(".")+1,path.length()).toString();
        if(result.toLowerCase().equals("png")){
			return ".aac";
		}else if(path.toLowerCase().contains("layout")){
			return "";
		}/*else if(path.toLowerCase().contains("raw")){
            return "";
        }*/
		return "."+result.toLowerCase();
    }

    public HashMap<String,String> getMap(){
        return obfusedMap;
    }

    private String getKey(String value){
        int position = values.indexOf(value);
        return keys.get(position);
    }

    private void changeValue(String key,String value){

        int position = keys.indexOf(key);
        if(position == -1){
            System.err.println("not found:"+key);
            return;
        }
        //System.out.printalueln("found: " + values.get(position));
        changedValues.remove(position);
        changedValues.add(position, value);


    }

    public byte[] getData()throws Exception{
        return mAndRes.mARSCDecoder.write(new ByteArrayInputStream(input), values, changedValues);
    }


    public static ByteArrayOutputStream cloneInputStream(InputStream input) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
            int len;
            while ((len = input.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            return baos;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static byte[] toByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n = 0;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        in.close();
        return out.toByteArray();
    }


}

