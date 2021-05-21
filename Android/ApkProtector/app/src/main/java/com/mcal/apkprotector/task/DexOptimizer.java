package com.mcal.apkprotector.task;

import com.android.dex.Dex;
import com.android.dx.command.dexer.DxContext;
import com.android.dx.merge.CollisionPolicy;
import com.android.dx.merge.DexMerger;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

/**
 * Разделение дексов на несколько. Классы указанные в списке игнорирования не будут зашифрованы
 */

public class DexOptimizer {
    public static boolean dexMerge(@NotNull String inputDex, @NotNull String inputLibs, @NotNull String outputDex) {
        try {
            Dex[] toBeMerge = {new Dex(new File(inputDex)), new Dex(new File(inputLibs))};
            DexMerger dexMerger = new DexMerger(toBeMerge, CollisionPolicy.FAIL, new DxContext());
            Dex merged = dexMerger.merge();
            merged.writeTo(new File(outputDex));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void init(String path) {
        String classesOp = path + "/classes-op.dex";
        String classes = path + "/classes.dex";
        String dexloader = path + "/dexloader.dex";

        if (new File(classesOp).exists()) {
            if (dexMerge(dexloader, classesOp, classes)) {
                new File(classesOp).delete();
                new File(dexloader).delete();
            }
        } else {
            new File(dexloader).renameTo(new File(classes));
        }
    }
}