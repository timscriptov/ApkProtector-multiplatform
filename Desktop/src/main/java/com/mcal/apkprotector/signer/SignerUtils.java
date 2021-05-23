package com.mcal.apkprotector.signer;

import android.sun.security.pkcs.PKCS8Key;
import android.util.Pair;
import com.android.apksig.ApkSigner;
import com.android.apksig.ApkVerifier;
import kellinwood.security.zipsigner.optional.KeyStoreFileManager;
import org.jetbrains.annotations.NotNull;
import com.mcal.apkprotector.data.Constants;
import com.mcal.apkprotector.data.Preferences;
import com.mcal.apkprotector.utils.LoggerUtils;

import java.io.*;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.List;

public class SignerUtils {
    private static String msgId;
    private static File idsigFile;
    private final PrivateKey privateKey;
    private final X509Certificate certificate;

    private SignerUtils(PrivateKey privateKey, X509Certificate certificate) {
        this.privateKey = privateKey;
        this.certificate = certificate;
    }

    public static SignerUtils getInstance(File idSigFile) {
        idsigFile = idSigFile;
        msgId = null;
        Pair<PrivateKey, X509Certificate> signingKey;
        try {
            if (Preferences.isCustomSignature()) {
                String keyPath = Preferences.isSignaturePath();
                String certAlias = Preferences.isSignatureAlias();
                String storePass = Preferences.isCertPassword();
                String keyPass = Preferences.isSignaturePassword();
                try {
                    signingKey = loadKey(keyPath, keyPass.toCharArray(), certAlias, storePass.toCharArray());
                } catch (Exception e) {
                    throw new SignatureException("Unable to sign apk.", e);
                }
            } else {
                try {
                    signingKey = loadKey();
                } catch (Exception e) {
                    throw new SignatureException("Unable to sign apk.", e);
                }
            }
            return new SignerUtils(signingKey.first, signingKey.second);
        } catch (SignatureException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Pair<PrivateKey, X509Certificate> loadKey()
            throws IOException, InvalidKeyException, CertificateException {
        PrivateKey privateKey;
        X509Certificate certificate;
        try (InputStream key = new FileInputStream(Constants.PK8_PATH)) {
            PKCS8Key pkcs8Key = new PKCS8Key();
            pkcs8Key.decode(key);
            privateKey = pkcs8Key;
        }
        try (InputStream cert = new FileInputStream(Constants.PEM_PATH)) {
            certificate = (X509Certificate) CertificateFactory.getInstance("X.509")
                    .generateCertificate(cert);
        }
        return new Pair<>(privateKey, certificate);
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

    public boolean verify(File apk) {
        ApkVerifier.Builder builder = new ApkVerifier.Builder(apk);
        if (Preferences.getSignatureV4()) {
            if (idsigFile == null) {
                throw new RuntimeException("idsig file is mandatory for v4 signature scheme.");
            }
            builder.setV4SignatureFile(idsigFile);
        }
        ApkVerifier verifier = builder.build();
        try {
            ApkVerifier.Result result = verifier.verify();
            LoggerUtils.writeLog("SignUtils::verify: " + apk);
            boolean isVerify = result.isVerified();
            if (isVerify) {
                if (Preferences.getSignatureV1() && result.isVerifiedUsingV1Scheme())
                    LoggerUtils.writeLog("SignUtils.verify: V1 signature verification succeeded.");
                else LoggerUtils.writeLog("SignUtils.verify: V1 signature verification failed/disabled.");
                if (Preferences.getSignatureV2() && result.isVerifiedUsingV2Scheme())
                    LoggerUtils.writeLog("SignUtils.verify: V2 signature verification succeeded.");
                else LoggerUtils.writeLog("SignUtils.verify: V2 signature verification failed/disabled.");
                if (Preferences.getSignatureV3() && result.isVerifiedUsingV3Scheme())
                    LoggerUtils.writeLog("SignUtils.verify: V3 signature verification succeeded.");
                else LoggerUtils.writeLog("SignUtils.verify: V3 signature verification failed/disabled.");
                if (Preferences.getSignatureV4() && result.isVerifiedUsingV4Scheme())
                    LoggerUtils.writeLog("SignUtils.verify: V4 signature verification succeeded.");
                else LoggerUtils.writeLog("SignUtils.verify: V4 signature verification failed/disabled.");
                List<X509Certificate> signerCertificates = result.getSignerCertificates();
                LoggerUtils.writeLog("SignUtils.verify: Number of signatures: " + signerCertificates.size());
            }
            for (ApkVerifier.IssueWithParams warn : result.getWarnings()) {
                LoggerUtils.writeLog("SignUtils.verify: " + warn.toString());
            }
            for (ApkVerifier.IssueWithParams err : result.getErrors()) {
                LoggerUtils.writeLog("SignUtils.verify: " + err.toString());
            }
            if (Preferences.getSignatureV1()) {
                for (ApkVerifier.Result.V1SchemeSignerInfo signer : result.getV1SchemeIgnoredSigners()) {
                    String name = signer.getName();
                    for (ApkVerifier.IssueWithParams err : signer.getErrors()) {
                        LoggerUtils.writeLog("SignUtils.verify: " + name + ": " + err);
                    }
                    for (ApkVerifier.IssueWithParams err : signer.getWarnings()) {
                        LoggerUtils.writeLog("SignUtils.verify: " + name + ": " + err);
                    }
                }
            }
            return isVerify;
        } catch (Exception e) {
            LoggerUtils.writeLog("SignUtils.verify: Verification failed." + e);
            return false;
        }
    }

    public void sign(File in, File out, int minSdk) {
        if (msgId != null) LoggerUtils.writeLog(msgId);
        ApkSigner.SignerConfig signerConfig = new ApkSigner.SignerConfig.Builder("CERT",
                privateKey, Collections.singletonList(certificate)).build();
        ApkSigner.Builder builder = new ApkSigner.Builder(Collections.singletonList(signerConfig));
        builder.setInputApk(in);
        builder.setOutputApk(out);
        builder.setCreatedBy("ApkProtector");
        if (minSdk != -1) builder.setMinSdkVersion(minSdk);
        builder.setV1SigningEnabled(Preferences.getSignatureV1());
        builder.setV2SigningEnabled(Preferences.getSignatureV2());
        builder.setV3SigningEnabled(Preferences.getSignatureV3());
        if (Preferences.getSignatureV4()) {
            if (idsigFile == null) {
                throw new RuntimeException("idsig file is mandatory for v4 signature scheme.");
            }
            builder.setV4SigningEnabled(true);
            builder.setV4SignatureOutputFile(idsigFile);
        }
        ApkSigner signer = builder.build();
        LoggerUtils.writeLog("SignUtils.sign: " + in);
        try {
            signer.sign();
            LoggerUtils.writeLog("SignUtils.sign: The signature is complete and the output file is " + out);
        } catch (Exception e) {
            LoggerUtils.writeLog("SignUtils::sign: " + e);
        }
    }
}
