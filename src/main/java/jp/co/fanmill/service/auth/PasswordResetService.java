package jp.co.fanmill.service.auth;

import jp.co.fanmill.dao.UserDAO;
import jp.co.fanmill.dto.UserDTO;
import jp.co.fanmill.util.PasswordUtil;
import jp.co.fanmill.util.ValidationUtil;

public class PasswordResetService {

    private final UserDAO userDAO = new UserDAO();

    /*
     * 秘密の質問を取得
     */
    public String getSecretQuestion(String userId) {

        if (!ValidationUtil.isValidUserId(userId)) {
            return null;
        }

        UserDTO user = userDAO.findByUserId(userId);

        if (user == null) {
            return null;
        }

        return user.getSecretQuestion();
    }

    /*
     * 秘密の質問の回答確認
     */
    public boolean checkSecretAnswer(
            String userId,
            String secretAnswer
    ) {

        if (!ValidationUtil.isValidUserId(userId)
                || !ValidationUtil.isValidSecretAnswer(secretAnswer)) {

            return false;
        }

        UserDTO user = userDAO.findByUserId(userId);

        if (user == null) {
            return false;
        }

        return PasswordUtil.matches(
                secretAnswer.trim(),
                user.getSecretAnswerHash()
        );
    }

    /*
     * パスワード再設定
     */
    public boolean resetPassword(
            String userId,
            String newPassword,
            String newPasswordConfirm
    ) {

        if (!ValidationUtil.isValidUserId(userId)
                || !ValidationUtil.isValidPassword(newPassword)
                || !ValidationUtil.isPasswordConfirmed(
                        newPassword,
                        newPasswordConfirm
                )) {

            return false;
        }

        String passwordHash =
                PasswordUtil.hash(newPassword);

        return userDAO.updatePassword(
                userId,
                passwordHash
        );
    }
}