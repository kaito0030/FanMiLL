package jp.co.fanmill.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {

    private PasswordUtil() {
    }

    public static String hash(String plainText) {

        if (plainText == null) {
            return null;
        }

        try {
            MessageDigest digest =
                    MessageDigest.getInstance("SHA-256");

            byte[] hashBytes =
                    digest.digest(
                            plainText.getBytes(StandardCharsets.UTF_8)
                    );

            StringBuilder sb = new StringBuilder();

            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("ハッシュ化に失敗しました。", e);
        }
    }

    public static boolean matches(
            String plainText,
            String hashedText
    ) {

        if (plainText == null || hashedText == null) {
            return false;
        }

        String plainHash = hash(plainText);

        return plainHash.equals(hashedText);
    }
}