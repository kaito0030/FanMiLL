package jp.co.fanmill.service.account;

import jp.co.fanmill.dao.UserDAO;
import jp.co.fanmill.dto.UserDTO;
import jp.co.fanmill.util.ValidationUtil;

public class ProfileService {

    private final UserDAO userDAO = new UserDAO();

    /*
     * アカウント情報取得
     */
    public UserDTO getAccount(String userId) {

        if (!ValidationUtil.isValidUserId(userId)) {
            return null;
        }

        return userDAO.findByUserId(userId);
    }

    /*
     * プロフィール更新
     */
    public boolean updateProfile(
            String userId,
            String userName,
            String profileText
    ) {

        if (!ValidationUtil.isValidUserId(userId)
                || !ValidationUtil.isValidUserName(userName)
                || !ValidationUtil.isValidProfileText(profileText)) {

            return false;
        }

        return userDAO.updateProfile(
                userId,
                userName.trim(),
                profileText == null ? "" : profileText.trim()
        );
    }
}