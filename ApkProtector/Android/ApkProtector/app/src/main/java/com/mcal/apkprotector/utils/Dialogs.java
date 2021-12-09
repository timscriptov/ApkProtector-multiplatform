package com.mcal.apkprotector.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatTextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.mcal.apkprotector.R;
import com.mcal.apkprotector.utils.text.HtmlUtils;

import ru.svolf.melissa.sheet.SweetContentDialog;
import ru.svolf.melissa.sheet.SweetViewDialog;

public class Dialogs {
    @SuppressLint("WrongConstant")
    public static void vending(Context context) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setPadding(40, 0, 40, 0);
        ll.setLayoutParams(layoutParams);
        final TextInputLayout til0 = new TextInputLayout(context);
        final AppCompatTextView message = new AppCompatTextView(context);
        message.setText(R.string.vending_message);
        til0.addView(message);
        ll.addView(til0);

        final SweetViewDialog dialog = new SweetViewDialog(context);
        dialog.setTitle(R.string.error_vending);
        dialog.setView(ll);
        dialog.setCancelable(false);
        dialog.setPositive(android.R.string.ok, v1 -> {
            dialog.cancel();
        });
        dialog.show();
    }

    @SuppressLint("WrongConstant")
    public static void dialog(Context context, String title, String mesage) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setPadding(40, 0, 40, 0);
        ll.setLayoutParams(layoutParams);

        AppCompatTextView msg = new AppCompatTextView(context);
        msg.setText(mesage);
        ll.addView(msg);

        SweetViewDialog dialog = new SweetViewDialog(context);
        dialog.setTitle(title);
        dialog.setView(ll);
        dialog.setPositive(android.R.string.ok, null);
        dialog.show();
    }

    @SuppressLint("WrongConstant")
    public static void about(Context context) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setPadding(40, 0, 40, 0);
        ll.setLayoutParams(layoutParams);
        AppCompatTextView msg1 = new AppCompatTextView(context);
        msg1.setText(HtmlUtils.fromHtml(context.getString(R.string.about_msg1)));

        AppCompatTextView copyright = new AppCompatTextView(context);
        copyright.setText(HtmlUtils.fromHtml(context.getString(R.string.about_copyright)));
        ll.addView(msg1);
        ll.addView(copyright);

        SweetViewDialog dialog = new SweetViewDialog(context);
        dialog.setTitle(R.string.about);
        dialog.setView(ll);
        dialog.setPositive(android.R.string.ok, null);
        dialog.setNegative(R.string.licenses, view -> {
            showOpenSourceLicenses(context);
        });
        dialog.show();
    }

    public static void showOpenSourceLicenses(Context context) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setPadding(40, 0, 40, 0);
        ll.setLayoutParams(layoutParams);

        final SweetContentDialog dialog = new SweetContentDialog(context);
        dialog.setTitle(context.getString(R.string.licenses));
        dialog.setView(ll);
        dialog.addAction(R.drawable.ic_link, "Axml", view -> {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Sable/axml")));
            dialog.cancel();
        });
        dialog.addAction(R.drawable.ic_link, "AndResGuard", view -> {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/shwenzhang/AndResGuard")));
            dialog.cancel();
        });
        dialog.addAction(R.drawable.ic_link, "Smali", view -> {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/JesusFreke/smali")));
            dialog.cancel();
        });
        dialog.addAction(R.drawable.ic_link, "DX", view -> {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://android.googlesource.com/platform/dalvik/+/master/dx")));
            dialog.cancel();
        });
        dialog.addAction(R.drawable.ic_link, "ApkSigner", view -> {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://android.googlesource.com/platform/tools/apksig/+/master/")));
            dialog.cancel();
        });
        dialog.addAction(R.drawable.ic_link, "ZipAligner", view -> {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://android.googlesource.com/platform/build/+/master/tools/zipalign")));
            dialog.cancel();
        });
        dialog.show();
    }

    public static void showCommunitySheet(Context context) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setPadding(40, 0, 40, 0);
        ll.setLayoutParams(layoutParams);

        final SweetContentDialog dialog = new SweetContentDialog(context);
        dialog.setTitle(R.string.community);
        dialog.setView(ll);
        dialog.addAction(R.drawable.ic_community_googleplay, "Google Play", view -> {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.mcal.dexprotect")));
            dialog.cancel();
        });
        dialog.addAction(R.drawable.ic_community_4pda, "4PDA", view -> {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://4pda.ru/forum/index.php?showtopic=971138")));
            dialog.cancel();
        });
        dialog.addAction(R.drawable.ic_community_gmail, "Gmail", view -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:timscriptov@gmail.com"));
            intent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name));
            try {
                context.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
                Snackbar.make(null, context.getString(R.string.about_not_found_email), Snackbar.LENGTH_SHORT).show();
            }
            dialog.cancel();
        });
        dialog.addAction(R.drawable.ic_community_telegram, "Telegram", view -> {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/dexprotect")));
            dialog.cancel();
        });
        dialog.show();
    }
}