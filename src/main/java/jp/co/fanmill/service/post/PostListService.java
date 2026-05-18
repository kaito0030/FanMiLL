package jp.co.fanmill.service.post;

import java.util.List;

import jp.co.fanmill.dao.PostDAO;
import jp.co.fanmill.dao.UserDAO;
import jp.co.fanmill.dto.PostDTO;
import jp.co.fanmill.dto.UserDTO;
import jp.co.fanmill.util.ValidationUtil;

public class PostListService {
	private static final int PERIOD_DAYS=1;

    private static final int DEFAULT_LIMIT = 100;

    private final PostDAO postDAO =
            new PostDAO();

    private final UserDAO userDAO =
            new UserDAO();

    /**
     * 投稿検索
     */
    public List<PostDTO> searchPosts(
            String keyword,
            String sort,
            int page
    ) {

        if (keyword == null) {
            return null;
        }

        if (sort == null || sort.isBlank()) {
            sort = "new";
        }

        if (page < 1) {
            page = 1;
        }

        int offset =
                (page - 1) * DEFAULT_LIMIT;

        return postDAO.search(
                keyword,
                sort,
                PERIOD_DAYS,
                DEFAULT_LIMIT,
                offset
        );
    }

    /**
     * ユーザ検索
     */
    public List<UserDTO> searchUsers(
            String keyword,
            int page
    ) {

        if (keyword == null) {
            keyword = "";
        }

        if (page < 1) {
            page = 1;
        }

        int offset =
                (page - 1) * DEFAULT_LIMIT;

        return userDAO.search(
                keyword,
                DEFAULT_LIMIT,
                offset
        );
    }
    
    /*
     * タイムライン取得
     */
    public List<PostDTO> getTimeline(String userId) {

        return postDAO.findFollowPost(userId);
    }

    /*
     * 指定ユーザの投稿一覧取得
     */
    public List<PostDTO> getUserPosts(String userId) {

        if (!ValidationUtil.isValidUserId(userId)) {
            return null;
        }

        return postDAO.findByUserId(userId);
    }
    /*
     *指定ポストのリプライ一覧を取得 
     */
    
    public List<PostDTO> getReplies(int parentPostId){
          return postDAO.findByParentPostId(parentPostId);
    }
    
    


}