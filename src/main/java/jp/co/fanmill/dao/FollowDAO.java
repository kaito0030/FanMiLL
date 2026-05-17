package jp.co.fanmill.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.fanmill.dto.UserDTO;
import jp.co.fanmill.util.DBUtil;

public class FollowDAO {

    /**
     * フォロー登録
     */
    public boolean insert(
            String followerId,
            String followeeId
    ) {

        String sql =
                "INSERT INTO follows ( " +
                "follower_id, " +
                "followee_id, " +
                "created_at " +
                ") VALUES ( " +
                "?, ?, CURRENT_TIMESTAMP " +
                ")";

        try (
                Connection con =
                        DBUtil.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, followerId);

            ps.setString(2, followeeId);

            int result =
                    ps.executeUpdate();

            return result > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    /**
     * フォロー解除
     */
    public boolean delete(
            String followerId,
            String followeeId
    ) {

        String sql =
                "DELETE FROM follows " +
                "WHERE follower_id = ? " +
                "AND followee_id = ?";

        try (
                Connection con =
                        DBUtil.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, followerId);

            ps.setString(2, followeeId);

            int result =
                    ps.executeUpdate();

            return result > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    /**
     * フォロー済み確認
     */
    public boolean exists(
            String followerId,
            String followeeId
    ) {

        String sql =
                "SELECT COUNT(*) " +
                "FROM follows " +
                "WHERE follower_id = ? " +
                "AND followee_id = ?";

        try (
                Connection con =
                        DBUtil.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, followerId);

            ps.setString(2, followeeId);

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                if (rs.next()) {

                    return rs.getInt(1) > 0;

                }
            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    /**
     * type に応じて一覧取得
     * follow / follower / friend
     */
    public List<UserDTO> findByType(
            String userId,
            String type,
            int limit,
            int offset
    ) {

        List<UserDTO> userList =
                new ArrayList<>();

        String sql;

        if ("follower".equals(type)) {

            sql =
                    "SELECT " +
                    "u.user_id, " +
                    "u.user_name, " +
                    "u.profile_text, " +
                    "u.mill_point, " +
                    "u.created_at, " +
                    "u.updated_at " +
                    "FROM follows f " +
                    "INNER JOIN users u " +
                    "ON f.follower_id = u.user_id " +
                    "WHERE f.followee_id = ? " +
                    "ORDER BY f.created_at DESC " +
                    "LIMIT ? OFFSET ?";

        } else if ("friend".equals(type)) {

            sql =
                    "SELECT " +
                    "u.user_id, " +
                    "u.user_name, " +
                    "u.profile_text, " +
                    "u.mill_point, " +
                    "u.created_at, " +
                    "u.updated_at " +
                    "FROM follows f1 " +
                    "INNER JOIN follows f2 " +
                    "ON f1.followee_id = f2.follower_id " +
                    "AND f1.follower_id = f2.followee_id " +
                    "INNER JOIN users u " +
                    "ON f1.followee_id = u.user_id " +
                    "WHERE f1.follower_id = ? " +
                    "ORDER BY f1.created_at DESC " +
                    "LIMIT ? OFFSET ?";

        } else {

            sql =
                    "SELECT " +
                    "u.user_id, " +
                    "u.user_name, " +
                    "u.profile_text, " +
                    "u.mill_point, " +
                    "u.created_at, " +
                    "u.updated_at " +
                    "FROM follows f " +
                    "INNER JOIN users u " +
                    "ON f.followee_id = u.user_id " +
                    "WHERE f.follower_id = ? " +
                    "ORDER BY f.created_at DESC " +
                    "LIMIT ? OFFSET ?";
        }

        try (
                Connection con =
                        DBUtil.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, userId);

            ps.setInt(2, limit);

            ps.setInt(3, offset);

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                while (rs.next()) {

                    UserDTO user =
                            new UserDTO();

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

}