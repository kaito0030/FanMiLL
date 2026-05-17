package jp.co.fanmill.service.auth;

import jp.co.fanmill.dao.UserDAO;
import jp.co.fanmill.dto.UserDTO;
import jp.co.fanmill.util.PasswordUtil;
import jp.co.fanmill.util.ValidationUtil;

public class AuthService {

    private final UserDAO userDAO = new UserDAO();

    public UserDTO login(String userId, String password) {

        if (!ValidationUtil.isValidUserId(userId)
                || !ValidationUtil.isValidPassword(password)) {

            return null;
        }

        UserDTO user = userDAO.findByUserId(userId);

        if (user == null) {
            return null;
        }

        boolean passwordOk =
                PasswordUtil.matches(
                        password,
                        user.getPasswordHash()
                );

        if (!passwordOk) {
            return null;
        }

        return user;
    }
}