package jp.co.fanmill.service.auth;

import jp.co.fanmill.dao.UserDAO;
import jp.co.fanmill.dto.UserDTO;
import jp.co.fanmill.util.PasswordUtil;
import jp.co.fanmill.util.ValidationUtil;

public class RegisterService {

    private final UserDAO userDAO = new UserDAO();

    public boolean register(
            String userId,
            String password,
            String passwordConfirm,
            String userName,
            String secretQuestion,
            String secretAnswer
    ) {

        if (!ValidationUtil.isValidUserId(userId)
                || !ValidationUtil.isValidPassword(password)
                || !ValidationUtil.isPasswordConfirmed(password, passwordConfirm)
                || !ValidationUtil.isValidUserName(userName)
                || !ValidationUtil.isValidSecretQuestion(secretQuestion)
                || !ValidationUtil.isValidSecretAnswer(secretAnswer)) {

            return false;
        }

        if (userDAO.existsUserId(userId)) {
            return false;
        }

        UserDTO user = new UserDTO();

        user.setUserId(userId);
        user.setPasswordHash(PasswordUtil.hash(password));
        user.setUserName(userName);
        user.setProfileText("");
        user.setMillPoint(0);
        user.setSecretQuestion(secretQuestion);
        user.setSecretAnswerHash(PasswordUtil.hash(secretAnswer.trim()));

        return userDAO.insert(user);
    }
}