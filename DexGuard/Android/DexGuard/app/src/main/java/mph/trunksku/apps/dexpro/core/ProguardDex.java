package mph.trunksku.apps.dexpro.core;

import com.googlecode.d2j.reader.BaseDexFileReader;
import com.googlecode.d2j.reader.MultiDexFileReader;
import com.googlecode.d2j.dex.Dex2jar;
import java.io.File;
import java.util.ArrayList;
import android.content.Context;
import proguard.ProGuard;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ProguardDex {

	private Context context;

    public ProguardDex(Context context) {
		this.context = context;
	}

	public void start(File in) {
		if (dex2jar(in, new File(in.getAbsolutePath() + ".jar"))) {

			if (proguard(new File(in.getAbsolutePath() + ".jar"))) {

			}
		}
	}

	private void runDex(File in, File out) throws Exception {
        StringBuilder cmd = new StringBuilder();
        cmd.append("dalvikvm -Xcompiler-option --compiler-filter=speed -Xmx256m -cp " + new File(context.getFilesDir(), "d8.jar").getAbsolutePath() + " com.android.tools.r8.D8");
        cmd.append(" --release");
        cmd.append(" --lib " + new File(context.getFilesDir(), "android.jar").getAbsolutePath());
        cmd.append(" --min-api " + 16);
		/* if (isJava8) {
		 cmd.append(" --classpath " + sdk.getAbsolutePath() + "/rt.jar");
		 } else {*/
		cmd.append(" --no-desugaring");
		// }
        cmd.append(" --output " + out.getAbsolutePath());
        cmd.append(" --intermediate");
        //cmd.append(" --file-per-class");
        //getAllFilesOfDir(".class", classes.getAbsolutePath(), cmd);

		cmd.append(" ");
		cmd.append(in.getAbsolutePath());
		Process dexProcess = Runtime.getRuntime().exec(cmd.toString());
        String error = readInputStreem(dexProcess.getErrorStream());
        if (!error.isEmpty()) {
            if (error.contains("Error")) {
                throw new Exception("Dexer:\n\n" + error);
            }
        }
    }

	private void runDexwR8(File in, File out) throws Exception {
        StringBuilder cmd = new StringBuilder();
        cmd.append("dalvikvm -Xcompiler-option --compiler-filter=speed -Xmx256m -cp " + new File(context.getFilesDir(), "d8.jar").getAbsolutePath() + " com.android.tools.r8.R8");
        cmd.append(" --release");
        cmd.append(" --lib " + new File(context.getFilesDir(), "android.jar").getAbsolutePath());
        cmd.append(" --min-api " + 16);
		/* if (isJava8) {
		 cmd.append(" --classpath " + sdk.getAbsolutePath() + "/rt.jar");
		 } else {*/
		cmd.append(" --no-desugaring");
		// }
        cmd.append(" --output " + out.getAbsolutePath());
		//  cmd.append(" --intermediate");
		//cmd.append(" --pg-conf " + m_proguard_rules);

        //cmd.append(" --file-per-class");
        //getAllFilesOfDir(".class", classes.getAbsolutePath(), cmd);
		for (File f : in.listFiles()) {
			if (f.getName().startsWith("classes")) {
				cmd.append(" ");
				cmd.append(f.getAbsolutePath());

			}
		}
		/*cmd.append(" ");
		 cmd.append(in.getAbsolutePath());*/
		Process dexProcess = Runtime.getRuntime().exec(cmd.toString());
        String error = readInputStreem(dexProcess.getErrorStream());
        if (!error.isEmpty()) {
            if (error.contains("Error")) {
                throw new Exception("Dexer:\n\n" + error);
            }
        }
    }

	public static String readInputStreem(InputStream in) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append('\n');
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString().trim();
    }

	public boolean proguard(File in) {
		try {
			ArrayList<String> args = new ArrayList<>();
			args.add("-libraryjars");
			//args.add(configPath + "/android5.jar");
			args.add(context.getFilesDir().getAbsolutePath() + "/android.jar");
			//File dictionary = new File(configPath.getAbsolutePath() + "/dictionary.txt");
			File dictionary = new File(context.getFilesDir().getAbsolutePath() + "/dictionary.txt");
			if (dictionary.exists()) {
				args.add("-obfuscationdictionary");
				args.add(dictionary.getAbsolutePath());
				args.add("-classobfuscationdictionary");
				args.add(dictionary.getAbsolutePath());
				args.add("-packageobfuscationdictionary");
				args.add(dictionary.getAbsolutePath());
			} else {
				System.out.println("Dictionary Disabled");
			}

			args.add("-include");
			//args.add(configPath.getAbsolutePath() + "/proguard-rules.pro");
			args.add(context.getFilesDir().getAbsolutePath() + "/proguard.pro");
			/*File proin = new File(workingPath.getAbsolutePath());
			 for (File f : proin.listFiles()) {
			 if (f.getName().startsWith("classes")) {

			 }
			 }*/
			args.add("-injars");
			args.add(in.getAbsolutePath());
			args.add("-outjars");
			File fout = new File(in.getAbsolutePath().replace(in.getName(), "classes.jar"));
			args.add(fout.getAbsolutePath());

			String[] arg = args.toArray(new String[args.size()]);
			System.out.println("Starting Proguard...");
			ProGuard.main(arg);
			args.clear();
			in.delete();

			runDex(fout, new File(fout.getAbsolutePath().replace(fout.getName(), "")));
			fout.delete();
			//jar2dex(new File(in.getAbsolutePath().replace(in.getName(), "classes.jar")), new File(in.getAbsolutePath().replace(in.getName(), "classes.dexp")))
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());

			return false;
		}
	}

    private boolean dex2jar(File in, File out)  {
		try {
			System.out.println("Processing input " + in.getAbsolutePath());

			BaseDexFileReader reader = MultiDexFileReader.open(in.getAbsolutePath());
			Dex2jar d2j = Dex2jar.from(reader);
			d2j.reUseReg(false)
				.topoLogicalSort()
				.skipDebug(false)
				.optimizeSynchronized(false)
				.printIR(false)
				.noCode(false)
				.skipExceptions(false)
				.to(out);

			System.out.println("Final output " + out.getAbsolutePath());
			return true;
		} catch (Exception e) {
			return false;
		}
    }

    private boolean jar2dex(File in, File out)  {
        try {
			System.out.println("Processing input " + in.getAbsolutePath());
			String[] args2 = new String[]{"--dex", 
				// "--verbose",
				"--no-strict",
				"--output=" + out.getAbsolutePath(),
				in.getAbsolutePath()};
			com.android.dx.command.Main.main(args2);
			System.out.println("Final output " + out.getAbsolutePath());
			return true;
		} catch (Exception e) {
			return false;
		}
    }


}
