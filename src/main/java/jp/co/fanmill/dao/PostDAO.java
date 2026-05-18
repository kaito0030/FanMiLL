package jp.co.fanmill.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.fanmill.dto.PostDTO;
import jp.co.fanmill.util.DBUtil;

public class PostDAO {
    
	/*
     * ポスト存在確認
     * */
	
	public boolean existsPost(int postId) {
        if(postId<=0) {
        	    return false;
        }
	    String sql =
	            "SELECT 1 " +
	            "FROM posts " +
	            "WHERE post_id = ?";

	    try (
	            Connection con = DBUtil.getConnection();
	            PreparedStatement ps = con.prepareStatement(sql)
	    ) {
	        ps.setInt(1, postId);

	        try (ResultSet rs = ps.executeQuery()) {
	            return rs.next();
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return false;
	}
	
    /*
     * 投稿登録
     */
    public boolean insert(PostDTO post) {

        String sql =
                "INSERT INTO posts (" +
                "parent_user_id"+
                "user_id, " +
                "content, " +
                "num_like,"+
                "num_reply"+
                "created_at, " +
                "updated_at" +
                ") VALUES (" +
                "?,?, ?,0,0, NOW(), NOW()" +
                ")";

        int result = 0;

        try (
                Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
        	   ps.setInt(1,post.getParentPostId());

            ps.setString(2, post.getUserId());

            ps.setString(3, post.getContent());

            result = ps.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return result > 0;
    }
    
    public boolean delete(
            int postId,
            String userId
    ) {

        String sql = """
                DELETE FROM posts
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
    /*
     * フォローユーザの投稿取得
     */
    public List<PostDTO> findFollowPost(String userId) {

        String sql =
                "SELECT " +
                "p.post_id, " +
                "p.user_id, " +
                "u.user_name, " +
                "p.content, " +
                "p.num_like"+/*add on phase2*/
                "p.num_reply"+/*add on phase2*/
                "p.created_at, " +
                "p.updated_at " +
                "FROM posts p " +
                "INNER JOIN follows f"+
                "ON p.user_id=f.followee_id"+
                "INNER JOIN users u " +
                "ON p.user_id = u.user_id " +
                "WHERE f.follower=?"+
                "AND p.parent IS NULL"+
                "ORDER BY p.updated_at DESC";


        List<PostDTO> postList =
                new ArrayList<>();

        try (
                Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                PostDTO post = new PostDTO();

                post.setPostId(
                        rs.getInt("post_id")
                );

                post.setUserId(
                        rs.getString("user_id")
                );

                post.setUserName(
                        rs.getString("user_name")
                );

                post.setContent(
                        rs.getString("content")
                );
                post.setNumLike(
                        rs.getInt("num_like")	
                );
                
                post.setNumReply(
                	     rs.getInt("num_reply")	
                	);

                post.setCreatedAt(
                        rs.getTimestamp("created_at")
                );

                post.setUpdatedAt(
                        rs.getTimestamp("updated_at")
                );

                postList.add(post);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return postList;
    }

    /*
     * userIdごとの投稿取得
     */
    public List<PostDTO> findByUserId(String userId) {

        String sql =
                "SELECT " +
                "p.post_id, " +
                "p.user_id, " +
                "u.user_name, " +
                "p.content, " +
                "p.num_like"+/*add on phase2*/
                "p.num_reply"+/*add on phase2*/
                "p.created_at, " +
                "p.updated_at " +
                "FROM posts p " +
                "INNER JOIN users u " +
                "ON p.user_id = u.user_id " +
                "WHERE p.user_id = ? " +
                "ORDER BY p.updated_at DESC";

        List<PostDTO> postList =
                new ArrayList<>();

        try (
                Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, userId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    PostDTO post = new PostDTO();

                    post.setPostId(
                            rs.getInt("post_id")
                    );

                    post.setUserId(
                            rs.getString("user_id")
                    );

                    post.setUserName(
                            rs.getString("user_name")
                    );

                    post.setContent(
                            rs.getString("content")
                    );
                    post.setNumLike(
                            rs.getInt("num_like")	
                    );
                    
                    post.setNumReply(
                    	     rs.getInt("num_reply")	
                    	);
                    post.setCreatedAt(
                            rs.getTimestamp("created_at")
                    );

                    post.setUpdatedAt(
                            rs.getTimestamp("updated_at")
                    );

                    postList.add(post);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return postList;
    }
    
   
    /*
     *親ポストへのリプライ一覧を取得 
     */
    public List<PostDTO> findByParentPostId(Integer parentPostId){
        String sql =
                "SELECT " +
                "p.post_id, " +
                "p.user_id, " +
                "u.user_name, " +
                "p.content, " +
                "p.num_like"+/*add on phase2*/
                "p.num_reply"+/*add on phase2*/
                "p.created_at, " +
                "p.updated_at " +
                "FROM posts p " +
                "INNER JOIN users u " +
                "ON p.user_id = u.user_id " +
                "WHERE p.parent_post_id = ? " +
                "ORDER BY p.updated_at DESC";

        List<PostDTO> postList =
                new ArrayList<>();

        try (
                Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1,parentPostId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    PostDTO post = new PostDTO();

                    post.setPostId(
                            rs.getInt("post_id")
                    );

                    post.setUserId(
                            rs.getString("user_id")
                    );

                    post.setUserName(
                            rs.getString("user_name")
                    );

                    post.setContent(
                            rs.getString("content")
                    );
                    post.setNumLike(
                            rs.getInt("num_like")	
                    );
                    
                    post.setNumReply(
                    	     rs.getInt("num_reply")	
                    	);
                    post.setCreatedAt(
                            rs.getTimestamp("created_at")
                    );

                    post.setUpdatedAt(
                            rs.getTimestamp("updated_at")
                    );

                    postList.add(post);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return postList;
    }
    
    /**
     * post_id で投稿取得
     */
    public PostDTO findByPostId(
            int postId
    ) {

        String sql =
                "SELECT " +
                "p.post_id, " +
                "p.user_id, " +
                "u.user_name, " +
                "p.parent_post_id, " +
                "p.content, " +
                "p.like_count, " +
                "p.reply_count, " +
                "p.created_at, " +
                "p.updated_at, " +
                "FROM posts p " +
                "INNER JOIN users u " +
                "ON p.user_id = u.user_id " +
                "WHERE p.post_id = ?";

        try (
                Connection con =
                        DBUtil.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, postId);

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                if (rs.next()) {

                    PostDTO post =
                            new PostDTO();

                    post.setPostId(
                            rs.getInt("post_id")
                    );

                    post.setUserId(
                            rs.getString("user_id")
                    );

                    post.setUserName(
                            rs.getString("user_name")
                    );

                    post.setParentPostId(
                    	 (Integer)rs.getInt("parent_post_id")
                    );

                    post.setContent(
                            rs.getString("content")
                    );

                    post.setNumLike(
                            rs.getInt("like_count")
                    );

                    post.setNumReply(
                            rs.getInt("reply_count")
                    );

                    post.setCreatedAt(
                            rs.getTimestamp("created_at")
                    );

                    post.setUpdatedAt(
                            rs.getTimestamp("updated_at")
                    );

                    return post;
                }

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;
    }
    
    /*
     *検索キーワードに一致する投稿取得 
     */
   public List<PostDTO> search(
        String keyword,
        String sort,
        int periodDays,
        int limit,
        int offset
) {
    List<PostDTO> postList = new ArrayList<>();

    if (keyword == null) {
        keyword = "";
    }

    if (sort == null) {
        sort = "new";
    }

    if (periodDays <= 0) {
        periodDays = 1;
    }

    boolean isUserIdSearch =
            keyword.startsWith("@");

    if (isUserIdSearch) {
        keyword = keyword.substring(1);
    }

    String selectScore = "";
    String joinLikeForScore = "";
    String groupBy = "";
    String orderBy;

    if ("popular".equals(sort)) {

        selectScore =
                ", COALESCE(SUM(" +
                "EXP(- EXTRACT(EPOCH FROM (CURRENT_TIMESTAMP - l.created_at)) / 3600)" +
                "), 0) AS popular_score ";

        joinLikeForScore =
                "LEFT JOIN likes l " +
                "ON p.post_id = l.post_id " +
                "AND l.created_at >= CURRENT_TIMESTAMP - (? * INTERVAL '1 day') ";

        groupBy =
                "GROUP BY " +
                "p.post_id, " +
                "p.user_id, " +
                "u.user_name, " +
                "p.parent_post_id, " +
                "p.content, " +
                "p.num_like, " +
                "p.num_reply, " +
                "p.created_at, " +
                "p.updated_at ";

        orderBy =
                "ORDER BY popular_score DESC, p.updated_at DESC ";

    } else {

        orderBy =
                "ORDER BY p.updated_at DESC ";
    }

    String whereCondition;

    if (isUserIdSearch) {

        whereCondition =
                "AND p.user_id LIKE ? ";

    } else {

        whereCondition =
                "AND ( " +
                "p.content LIKE ? " +
                "OR u.user_name LIKE ? " +
                ") ";
    }

    String sql =
            "SELECT " +
            "p.post_id, " +
            "p.user_id, " +
            "u.user_name, " +
            "p.parent_post_id, " +
            "p.content, " +
            "p.num_like, " +
            "p.num_reply, " +
            "p.created_at, " +
            "p.updated_at " +
            selectScore +
            "FROM posts p " +
            "INNER JOIN users u " +
            "ON p.user_id = u.user_id " +
            joinLikeForScore +
            "WHERE p.parent_post_id IS NULL " +
            "AND p.updated_at >= CURRENT_TIMESTAMP - INTERVAL '30 days'"+
            whereCondition +
            groupBy +
            orderBy +
            "LIMIT ? OFFSET ?";

    try (
            Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
    ) {
        String likeKeyword =
                "%" + keyword + "%";

        int index = 1;

        if ("popular".equals(sort)) {
            ps.setInt(index++, periodDays);
        }

        if (isUserIdSearch) {

            ps.setString(index++, likeKeyword);

        } else {

            ps.setString(index++, likeKeyword);
            ps.setString(index++, likeKeyword);
        }

        ps.setInt(index++, limit);
        ps.setInt(index, offset);

        try (ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                PostDTO post = new PostDTO();

                post.setPostId(rs.getInt("post_id"));
                post.setUserId(rs.getString("user_id"));
                post.setUserName(rs.getString("user_name"));
                post.setParentPostId(
                        (Integer) rs.getObject("parent_post_id")
                );
                post.setContent(rs.getString("content"));
                post.setNumLike(rs.getInt("num_like"));
                post.setNumReply(rs.getInt("num_reply"));
                post.setCreatedAt(rs.getTimestamp("created_at"));
                post.setUpdatedAt(rs.getTimestamp("updated_at"));

                postList.add(post);
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return postList;
}

    public boolean incrementLikeCount(int postId) {
        String sql =
                "UPDATE " +
                "posts"+
                	"SET num_like=num_like+1"+
                "WHERE post_id=?"
                ;
        try (
                Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
        	ps.setInt(1,postId);
            int result=ps.executeUpdate();
            		
            return result>0;

          

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;

    }
    
    public boolean decrementLikeCount(int postId) {
        String sql =
                "UPDATE " +
                "posts"+
                	"SET num_like=num_like-1"+
                "WHERE post_id=?"
                ;
        try (
                Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
        	ps.setInt(1,postId);
            int result=ps.executeUpdate();
            		
            return result>0;

          

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;

    }
    
    public boolean incrementReplyCount(int postId) {
        String sql =
                "UPDATE " +
                "posts"+
                	"SET num_reply=num_reply+1"+
                "WHERE post_id=?"
                ;
        try (
                Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
        	ps.setInt(1,postId);
            int result=ps.executeUpdate();
            		
            return result>0;

          

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;

    }
    
    public boolean decrementReplyCount(int postId) {
        String sql =
                "UPDATE " +
                "posts"+
                	"SET num_reply=num_reply-1"+
                "WHERE post_id=?"
                ;
        try (
                Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
        	ps.setInt(1,postId);
            int result=ps.executeUpdate();
            		
            return result>0;

          

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;

    }
    


}