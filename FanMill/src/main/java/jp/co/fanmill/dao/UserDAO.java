package jp.co.fanmill.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.fanmill.dto.UserDTO;
import jp.co.fanmill.util.DBUtil;

public class UserDAO {

    /*
     * userIdからユーザ取得
     */
    public UserDTO findByUserId(String userId) {

        String sql =
                "SELECT * " +
                "FROM users " +
                "WHERE user_id = ?";

        UserDTO user = null;

        try (
                Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, userId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    user = new UserDTO();

                    user.setUserId(
                            rs.getString("user_id")
                    );

                    user.setPasswordHash(
                            rs.getString("password_hash")
                    );

                    user.setUserName(
                            rs.getString("user_name")
                    );

                    user.setProfileText(
                            rs.getString("profile_text")
                    );

                    user.setMillPoint(
                            rs.getInt("mill_point")
                    );

                    user.setSecretQuestion(
                            rs.getString("secret_question")
                    );

                    user.setSecretAnswerHash(
                            rs.getString("secret_answer_hash")
                    );

                    user.setCreatedAt(
                            rs.getTimestamp("created_at")
                    );

                    user.setUpdatedAt(
                            rs.getTimestamp("updated_at")
                    );
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return user;
    }

    /*
     * userId重複確認
     */
    public boolean existsUserId(String userId) {

        String sql =
                "SELECT COUNT(*) " +
                "FROM users " +
                "WHERE user_id = ?";

        boolean exists = false;

        try (
                Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, userId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    exists = rs.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return exists;
    }

    /*
     * ユーザ登録
     */
    public boolean insert(UserDTO user) {

        String sql =
                "INSERT INTO users (" +
                "user_id, " +
                "password_hash, " +
                "user_name, " +
                "profile_text, " +
                "mill_point, " +
                "secret_question, " +
                "secret_answer_hash, " +
                "created_at, " +
                "updated_at" +
                ") VALUES (" +
                "?, ?, ?, ?, ?, ?, ?, NOW(), NOW()" +
                ")";

        int result = 0;

        try (
                Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, user.getUserId());

            ps.setString(2, user.getPasswordHash());

            ps.setString(3, user.getUserName());

            ps.setString(4, user.getProfileText());

            ps.setInt(5, user.getMillPoint());

            ps.setString(6, user.getSecretQuestion());

            ps.setString(7, user.getSecretAnswerHash());

            result = ps.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return result > 0;
    }

    /*
     * プロフィール更新
     */
    public boolean updateProfile(
            String userId,
            String userName,
            String profileText
    ) {

        String sql =
                "UPDATE users " +
                "SET user_name = ?, " +
                "profile_text = ?, " +
                "updated_at = NOW() " +
                "WHERE user_id = ?";

        int result = 0;

        try (
                Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, userName);

            ps.setString(2, profileText);

            ps.setString(3, userId);

            result = ps.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return result > 0;
    }

    /*
     * パスワード更新
     */
    public boolean updatePassword(
            String userId,
            String passwordHash
    ) {

        String sql =
                "UPDATE users " +
                "SET password_hash = ?, " +
                "updated_at = NOW() " +
                "WHERE user_id = ?";

        int result = 0;

        try (
                Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, passwordHash);

            ps.setString(2, userId);

            result = ps.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return result > 0;
    }
    
    public List<UserDTO> search(
            String keyword,
            int limit,
            int offset
    ) {
        List<UserDTO> userList = new ArrayList<>();

        if (keyword == null) {
            keyword = "";
        }

        boolean isUserIdSearch =
                keyword.startsWith("@");

        if (isUserIdSearch) {
            keyword = keyword.substring(1);
        }

        String sql;

        if (isUserIdSearch) {

            sql =
                    "SELECT " +
                    "user_id, " +
                    "user_name, " +
                    "profile_text, " +
                    "mill_point, " +
                    "created_at, " +
                    "updated_at " +
                    "FROM users " +
                    "WHERE user_id LIKE ? " +
                    "ORDER BY " +
                    "CASE " +
                    "WHEN user_id = ? THEN 1 " +
                    "WHEN user_id LIKE ? THEN 2 " +
                    "ELSE 3 " +
                    "END, " +
                    "LENGTH(user_id) ASC, " +
                    "user_id ASC " +
                    "LIMIT ? OFFSET ?";

        } else {

            sql =
                    "SELECT " +
                    "user_id, " +
                    "user_name, " +
                    "profile_text, " +
                    "mill_point, " +
                    "created_at, " +
                    "updated_at " +
                    "FROM users " +
                    "WHERE user_name LIKE ? " +
                    "ORDER BY " +
                    "CASE " +
                    "WHEN user_name = ? THEN 1 " +
                    "WHEN user_name LIKE ? THEN 2 " +
                    "ELSE 3 " +
                    "END, " +
                    "LENGTH(user_name) ASC, " +
                    "user_name ASC " +
                    "LIMIT ? OFFSET ?";
        }

        try (
                Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            String likeKeyword =
                    "%" + keyword + "%";

            String prefixKeyword =
                    keyword + "%";

            ps.setString(1, likeKeyword);
            ps.setString(2, keyword);
            ps.setString(3, prefixKeyword);
            ps.setInt(4, limit);
            ps.setInt(5, offset);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    UserDTO user = new UserDTO();

                    user.setUserId(
                            rs.getString("user_id")
                    );

                    user.setUserName(
                            rs.getString("user_name")
                    );

                    user.setProfileText(
                            rs.getString("profile_text")
                    );

                    user.setMillPoint(
                            rs.getInt("mill_point")
                    );

                    user.setCreatedAt(
                            rs.getTimestamp("created_at")
                    );

                    user.setUpdatedAt(
                            rs.getTimestamp("updated_at")
                    );

                    userList.add(user);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return userList;
    }
    
    /**
     * MILLポイント加算
     */
    public boolean addMillPoint(
            String userId,
            int point
    ) {

        String sql =
                "UPDATE users " +
                "SET mill_point = mill_point + ?, " +
                "updated_at = CURRENT_TIMESTAMP " +
                "WHERE user_id = ?";

        try (
                Connection con =
                        DBUtil.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, point);

            ps.setString(2, userId);

            int result =
                    ps.executeUpdate();

            return result > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

}