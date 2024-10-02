package udpm.hn.metu.utils;

import org.springframework.beans.factory.annotation.Value;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class AESPasswordCryptoUtil {

    @Value("${jwt.secret}")
    private static String tokenSecret;

    public static SecretKeySpec getSecretKey(String myKey) throws Exception {
        byte[] key;
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        key = sha.digest(myKey.getBytes(StandardCharsets.UTF_8));
        return new SecretKeySpec(key, "AES");
    }

    public static String encrypt(String data) throws Exception {
        SecretKeySpec secretKey = getSecretKey(tokenSecret);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedData = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public static String decrypt(String encryptedData) throws Exception {
        SecretKeySpec secretKey = getSecretKey(tokenSecret);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedData = cipher.doFinal(decodedData);
        return new String(decryptedData, StandardCharsets.UTF_8);
    }

}
