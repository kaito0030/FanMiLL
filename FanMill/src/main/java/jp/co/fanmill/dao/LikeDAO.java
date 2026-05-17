package jp.co.fanmill.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.fanmill.util.DBUtil;

public class LikeDAO {
	public boolean insert(int postId,String userId) {
        String sql =
                "INSERT INTO likes (" +
                "post_id, " +
                "created_at, " +
                ") VALUES (" +
                "?, ?,NOW()" +
                ")";

        int result = 0;

        try (
                Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
        	ps.setInt(1,postId);
        	ps.setString(2,userId);
        result=ps.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return result > 0;
	}
	
	public boolean delete(int postId,String userId) {
        String sql = """
                DELETE FROM likes
                WHERE post_id = ?
                AND user_id = ?
                """;

        try (
                Connection conn =
                        DBUtil.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setInt(1, postId);
            ps.setString(2, userId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
	}
	
	public boolean exists(int postId,String userId) {
        String sql =
                "SELECT COUNT(*) " +
                "FROM likes " +
                "WHERE user_id = ?"+
                "AND post_id= ?";

        boolean exists = false;

        try (
                Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, userId);
            ps.setInt(2,postId);

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
}
