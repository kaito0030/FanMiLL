package jp.co.fanmill.service.post;

import jp.co.fanmill.dao.PostDAO;
import jp.co.fanmill.dto.PostDTO;
import jp.co.fanmill.util.ValidationUtil;

public class PostService {

    private final PostDAO postDAO = new PostDAO();

    /*
     * 投稿作成
     */
    public boolean createPost(
    		Integer perentPostId,
            String userId,
            String content
    ) {

        if (!ValidationUtil.isValidUserId(userId)
                || !ValidationUtil.isValidPostContent(content)) {

            return false;
        }

        PostDTO post = new PostDTO();
        post.setParentPostId(perentPostId);
        post.setUserId(userId);
        post.setContent(content.trim());

        return postDAO.insert(post);
    }

    public boolean deletePost(
            int postId,
            String userId
    ) {

        if (postId <= 0
                || !ValidationUtil.isValidUserId(userId)) {

            return false;
        }

        return postDAO.delete(postId, userId);
    }
    
    public PostDTO getPostDetail(int postId) {

    	     if(postId<=0) {

    	    	     return null;    	         
    	     }

    	     return postDAO.findByPostId(postId);
    }
    

}

