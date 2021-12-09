package com.mcal.apkprotector.utils.dex;

import com.android.dex.Dex;
import com.android.dx.command.dexer.DxContext;
import com.android.dx.merge.CollisionPolicy;
import com.android.dx.merge.DexMerger;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class DexedClasses {

    public static boolean dexLibs(@NotNull File inputJar, @NotNull File outputDex) {
        try {
            String[] args = {"--verbose",
                    "--no-strict",
                    "--no-files",
                    "--output=" + outputDex.getAbsolutePath(), //output
                    inputJar.getAbsolutePath() //input
            };
            com.android.dx.command.dexer.Main.main(args);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Merge all classed has been build to a single file .dex
     */
    public static boolean dexBuildClasses(@NotNull File inputJar, @NotNull File outputDex) {
        try {
            String[] args = new String[]{
                    "--verbose", "--no-strict",
                    "--output=" + outputDex.getAbsolutePath(), //output dex file
                    inputJar.getAbsolutePath() //input files
            };
            com.android.dx.command.dexer.Main.main(args);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

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
}