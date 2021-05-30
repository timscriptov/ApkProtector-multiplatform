package com.mcal.dexprotect.patchers;

import com.mcal.dexprotect.data.Preferences;

import org.jfclone.dexlib2.DexFileFactory;
import org.jfclone.dexlib2.Opcodes;
import org.jfclone.dexlib2.dexbacked.DexBackedDexFile;
import org.jfclone.dexlib2.iface.DexFile;
import org.jfclone.dexlib2.rewriter.DexRewriter;
import org.jfclone.dexlib2.rewriter.Rewriter;
import org.jfclone.dexlib2.rewriter.RewriterModule;
import org.jfclone.dexlib2.rewriter.Rewriters;
import org.jfclone.dexlib2.writer.pool.DexPool;

public class DexCloner {
    public static boolean dexPatching(String dexPath) {
        try {
            DexBackedDexFile dexFile = DexFileFactory.loadDexFile(dexPath, Opcodes.forApi(19));
            DexRewriter rewriter = new DexRewriter(Opcodes.forApi(19), new RewriterModule() {
                public Rewriter<String> getTypeRewriter(Rewriters rewriters) {
                    return value -> {
                        if (value.equals("Lcom/mcal/apkprotector/ProtectApplication;")) {
                            return "L" + Preferences.getPackageName().replace(".", "/") + "/ProtectApplication;";
                        }
                        return value;
                    };
                }
            });
            DexFile rewrittenDexFile = rewriter.rewriteDexFile(dexFile);
            DexPool.writeTo(dexPath.replace("dexloader.dex", "dexloader2.dex"), rewrittenDexFile);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
