package jp.co.fanmill.util;

public class TextUtil {

    private TextUtil() {
    }

    public static String toHtml(String text) {

        if (text == null) {
            return "";
        }

        return text
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;")
                .replace("\r\n", "\n")
                .replace("\r", "\n")
                .replace("\n", "<br>");
    }
}