package com.mcal.apkprotector.signer;

import android.util.Log;
import android.util.Pair;

import androidx.annotation.NonNull;

import com.android.apksig.ApkSigner;
import com.mcal.apkprotector.data.Preferences;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Collections;

import kellinwood.security.zipsigner.optional.KeyStoreFileManager;

public class ApkSignerCustom {
    @NonNull
    private final PrivateKey privateKey;
    @NonNull
    private final X509Certificate certificate;

    private ApkSignerCustom(@NonNull PrivateKey privateKey, @NonNull X509Certificate certificate) {
        this.privateKey = privateKey;
        this.certificate = certificate;
    }

    @NonNull
    public static ApkSignerCustom getInstance()
            throws SignatureException {
        Pair<PrivateKey, X509Certificate> signingKey;
        String keyPath = Preferences.getSignaturePath();
        String certAlias = Preferences.getSignatureAlias();
        String storePass = Preferences.getCertPassword();
        String keyPass = Preferences.getSignaturePassword();
        try {
            signingKey = loadKey(keyPath, keyPass.toCharArray(), certAlias, storePass.toCharArray());
        } catch (Exception e) {
            throw new SignatureException("Unable to sign apk.", e);
        }
        return new ApkSignerCustom(signingKey.first, signingKey.second);
    }

    public static @NotNull Pair<PrivateKey, X509Certificate> loadKey(String keystorePath, char[] keyPass, String certAlias, char[] storePass) throws Exception {
        if (!new File(keystorePath).exists())
            throw new FileNotFoundException(keystorePath + " not found.");
        KeyStore keystore = KeyStoreFileManager.loadKeyStore(keystorePath, keyPass);
        Certificate cert = keystore.getCertificate(certAlias);
        X509Certificate publicKey = (X509Certificate) cert;
        Key key = keystore.getKey(certAlias, storePass);
        PrivateKey privateKey = (PrivateKey) key;

        return new Pair<>(privateKey, publicKey);
    }

    public void sign(File in, File out) {
        ApkSigner.SignerConfig signerConfig = new ApkSigner.SignerConfig.Builder("CERT",
                privateKey, Collections.singletonList(certificate)).build();
        ApkSigner.Builder builder = new ApkSigner.Builder(Collections.singletonList(signerConfig));
        builder.setInputApk(in);
        builder.setOutputApk(out);

        ApkSigner signer = builder.build();
        Log.i("ApkSignerCustom::sign", String.format("SignApk: %s", in));
        try {
            signer.sign();
            Log.i("ApkSignerCustom::sign", "The signature is complete and the output file is " + out);
        } catch (Exception e) {
            Log.w("ApkSignerCustom::sign", e);
        }
    }
}