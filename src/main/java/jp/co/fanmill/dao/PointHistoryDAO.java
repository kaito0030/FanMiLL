package jp.co.fanmill.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.fanmill.dto.PointHistoryDTO;
import jp.co.fanmill.util.DBUtil;

public class PointHistoryDAO {

    public boolean exists(
            String contentType,
            int contentId,
            String fromUserId,
            String reason
    ) {
        String sql =
                "SELECT COUNT(*) " +
                "FROM point_history " +
                "WHERE content_type = ? " +
                "AND content_id = ? " +
                "AND from_user_id = ? " +
                "AND reason = ?";

        try (
                Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, contentType);
            ps.setInt(2, contentId);
            ps.setString(3, fromUserId);
            ps.setString(4, reason);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean insert(
            PointHistoryDTO history
    ) {
        String sql =
                "INSERT INTO point_history ( " +
                "user_id, " +
                "content_type, " +
                "content_id, " +
                "from_user_id, " +
                "point, " +
                "reason, " +
                "created_at " +
                ") VALUES ( " +
                "?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP " +
                ")";

        try (
                Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, history.getUserId());
            ps.setString(2, history.getContentType());
            ps.setInt(3, history.getContentId());
            ps.setString(4, history.getFromUserId());
            ps.setInt(5, history.getPoint());
            ps.setString(6, history.getReason());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}