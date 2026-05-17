package jp.co.fanmill.util;

public class ValidationUtil {

    private ValidationUtil() {
    }

    /*
     * null または 空文字チェック
     */
    public static boolean isEmpty(String value) {

        return value == null
                || value.trim().isEmpty();
    }

    /*
     * 最大文字数超過チェック
     */
    public static boolean isOverLength(
            String value,
            int maxLength
    ) {

        if (value == null) {
            return false;
        }

        return value.length() > maxLength;
    }

    /*
     * パスワード一致チェック
     */
    public static boolean isPasswordConfirmed(
            String password,
            String confirmPassword
    ) {

        if (password == null
                || confirmPassword == null) {

            return false;
        }

        return password.equals(confirmPassword);
    }

    /*
     * 投稿文字数チェック
     */
    public static boolean isValidPostContent(
            String content
    ) {

        if (isEmpty(content)) {
            return false;
        }

        return !isOverLength(content, 200);
    }

    /*
     * userIdチェック
     */
    public static boolean isValidUserId(
            String userId
    ) {

        if (isEmpty(userId)) {
            return false;
        }

        return !isOverLength(userId, 50);
    }

    /*
     * パスワードチェック
     */
    public static boolean isValidPassword(
            String password
    ) {

        if (isEmpty(password)) {
            return false;
        }

        return !isOverLength(password, 100);
    }

    /*
     * 表示名チェック
     */
    public static boolean isValidUserName(
            String userName
    ) {

        if (isEmpty(userName)) {
            return false;
        }

        return !isOverLength(userName, 50);
    }

    /*
     * プロフィールチェック
     */
    public static boolean isValidProfileText(
            String profileText
    ) {

        if (profileText == null) {
            return true;
        }

        return !isOverLength(profileText, 1000);
    }

    /*
     * 秘密の質問チェック
     */
    public static boolean isValidSecretQuestion(
            String question
    ) {

        if (isEmpty(question)) {
            return false;
        }

        return !isOverLength(question, 255);
    }

    /*
     * 秘密の回答チェック
     */
    public static boolean isValidSecretAnswer(
            String answer
    ) {

        if (isEmpty(answer)) {
            return false;
        }

        return !isOverLength(answer, 255);
    }


}