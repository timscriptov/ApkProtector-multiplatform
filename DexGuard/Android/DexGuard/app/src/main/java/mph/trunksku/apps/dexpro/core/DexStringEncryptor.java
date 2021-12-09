package mph.trunksku.apps.dexpro.core;



import com.google.common.collect.Lists;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import mph.trunksku.apps.dexpro.MyApp;
import mph.trunksku.apps.dexpro.dexmerger.DexUtils;
import mph.trunksku.apps.dexpro.logger.DebugLog;
import mph.trunksku.apps.dexpro.utils.qtfreet00;
import org.apache.commons.lang3.StringEscapeUtils;
import org.jf.dexlib2.Opcodes;
import org.jf.dexlib2.dexbacked.DexBackedDexFile;
import org.jf.dexlib2.iface.ClassDef;
import org.jf.dexlib2.writer.builder.DexBuilder;
import org.jf.smali.Smali;
import org.jf.smali.SmaliOptions;


public class DexStringEncryptor {

    byte[] out;

    DexBackedDexFile dex;
    List<String> keeps;
    EncryptCallBack callBack;

	private DexBuilder dexBuilder;

    public DexStringEncryptor(String Input, List<String> keeps, EncryptCallBack mCallBack) throws Exception {
        File file = new File(Input);
        dex = DexBackedDexFile.fromInputStream(Opcodes.getDefault(), new BufferedInputStream(new FileInputStream(file)));
        this.keeps = keeps;
        this.callBack = mCallBack;
        init();

    }

    public DexStringEncryptor(byte[] Input, List<String> keeps, EncryptCallBack mCallBack) throws Exception {
        dex = DexBackedDexFile.fromInputStream(Opcodes.getDefault(), new ByteArrayInputStream(Input));
        this.keeps = keeps;
        this.callBack = mCallBack;
        init();
    }


    public void init() {

        if (keeps == null) {
            keeps = new ArrayList<>();
        }

         
		 keeps.add("javax.");
		 keeps.add("sun1.");
		 keeps.add("sun.");
		 keeps.add("java.");
		 keeps.add("com.google.");
		 keeps.add("android.");
		 keeps.add("androidx.");

    }

    public void start() throws Exception {
		dexBuilder = new DexBuilder(Opcodes.getDefault());
        dexBuilder.setIgnoreMethodAndFieldError(true);
		
        
       /* DexBackedDexFile baseDex = DexBackedDexFile.fromInputStream(Opcodes.getDefault(), new BufferedInputStream(getStreamFromAssets("EncryptString.dex")));

        List<ClassDef> baseClassDefs = (List<ClassDef>) Lists.newArrayList(baseDex.getClasses());

        for (int i = 0; i < baseClassDefs.size(); i++) {
            try {
                dexBuilder.internClassDef(baseClassDefs.get(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/

		//List<ClassDef> classDefs = (List<ClassDef>) Lists.newArrayList(dex.getClasses());

        List<ClassDef> classDefs = new ArrayList<>();
        for (ClassDef classDef : dex.getClasses()) {
            classDefs.add(classDef);
        }
        for (int i = 0; i < classDefs.size(); i++) {
            String type = classDefs.get(i).getType();
            String pkg = DexUtils.getPkgNameByType(type);

            if (pkg.equals("android.app.Application")) {
            }
			try {
				if (keeps == null || keeps.size() == 0) {
					callBack.onProgress(i, classDefs.size());
					
                    dealClassDef( classDefs.get(i));
                } else {
                    boolean encrypt=true;
                    for (String cla : keeps) {
                        if (type.startsWith(DexUtils.getTypeByPkg(cla))) {
                            encrypt = false;
                            break;
                        }
                    }
                    if (encrypt) {
						callBack.onProgress(i, classDefs.size());
						//DebugLog.i("", classDefs.get(i).getType());
                        dealClassDef( classDefs.get(i));
                    } else {
                        dexBuilder.internClassDef(classDefs.get(i));
                    }
					
                }
			} catch (Exception e) {
				DebugLog.i("", e.getMessage());
				break;
			}
			//dealClassDef(dexBuilder, classDefs.get(i));


        }




		//  System.out.println(MyApplication.getInstance().getString("400047895b647639339b218df76f3d28"));
    }

	public byte[] getData() throws IOException {
        return DexUtils.getDexBuilderData(dexBuilder);
    }



	private void dealClassDef( ClassDef classDef) throws Exception {
        String smali = DexUtils.getSmali(classDef);
		smali = smali.replace("#MODE", "0");
        smali = dealSmali(smali);
        //System.out.println(classDef.getType());
        Smali.assembleSmaliFile(smali, dexBuilder, new SmaliOptions());
    }

    public String dealSmali(String smali) throws Exception {


		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(smali.getBytes())));
		String str = "";

		while ((str = br.readLine()) != null) {

			Matcher m = Pattern.compile("const-string ([vp]\\d{1,2}), \"(.*)\"").matcher(str);
			if (m.find()) {
				String tmp = m.group(2);
				if (tmp.equals("")) {
					sb.append(str).append("\n");
					continue;
				}
				//字符串转义,过滤掉\（如\",不转义时获取到的为\"，但理想效果应为"）以及将smali中的unicode转为中文字符
				tmp = StringEscapeUtils.unescapeJava(tmp);
				String register = m.group(1);
				//register代表寄存器
				String enc = qtfreet00.encode(tmp);
				//混淆字符串
				String sign = "    const-string " + register + ", " + "\"" + enc + "\"";
				String dec = "";
				if (Integer.parseInt(register.substring(1)) > 15 && register.startsWith("v")) {
					//此处考虑寄存器个数，如果v寄存器大于15时，应使用range方式传参
					dec = "    invoke-static/range {" + register + " .. " + register + "}, Lxyz/dexpro/engine/d/d;->ab(Ljava/lang/String;)Ljava/lang/String;";
					//添加解密方法
				} else if (register.startsWith("v") || (register.startsWith("p") && Integer.parseInt(register.substring(1)) < 10)) {
					//此处p在10以上（不清楚具体），也会出现一些问题，由于没太接触过较大p寄存器，这里直接忽略掉了10以上的，实际应用中也很少会出现
					//p在方法中一般代表入参，静态方法中从p0开始，非静态方法从p1开始，p0带表this
					dec = "    invoke-static {" + register + "}, Lxyz/dexpro/engine/d/d;->ab(Ljava/lang/String;)Ljava/lang/String;";
				} else {
					sb.append(str).append("\n");
					continue;
				}
				String mov = "    move-result-object " + register;
				sb.append(sign).append("\n\n");
				sb.append(dec).append("\n\n");
				sb.append(mov).append("\n");
			} else {
				sb.append(str).append("\n");
			}
		}
		br.close();
        return sb.toString();
    }

    
    public interface EncryptCallBack {
        void onProgress(int progress, int total);

        void onClassDefName(String Name);
    }

	public static InputStream getStreamFromAssets(String fileName) {
        try {
            InputStream localInputStream = MyApp.app().getAssets().open(fileName);
            return localInputStream;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

