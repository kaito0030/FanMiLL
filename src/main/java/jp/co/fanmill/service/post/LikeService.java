package jp.co.fanmill.service.post;

import jp.co.fanmill.dao.LikeDAO;
import jp.co.fanmill.dao.PostDAO;
import jp.co.fanmill.dto.PostDTO;
import jp.co.fanmill.service.point.PointService;

public class LikeService {

    private final LikeDAO likeDAO = new LikeDAO();

    private final PostDAO postDAO = new PostDAO();

    private final PointService pointService = new PointService();

    /**
     * いいね切替
     * 
     * いいね済み   → 解除
     * 未いいね     → いいね登録
     */
    public boolean toggleLike(
            int postId,
            String loginUserId
    ) {
        if (postId <= 0
                || loginUserId == null
                || loginUserId.isBlank()) {

            return false;
        }

        boolean liked =
                likeDAO.exists(
                        postId,
                        loginUserId
                );

        if (liked) {

            boolean deleted =
                    likeDAO.delete(
                            postId,
                            loginUserId
                    );

            if (!deleted) {
                return false;
            }

            return postDAO.decrementLikeCount(
                    postId
            );
        }

        boolean inserted =
                likeDAO.insert(
                        postId,
                        loginUserId
                );

        if (!inserted) {
            return false;
        }

        boolean countUpdated =
                postDAO.incrementLikeCount(
                        postId
                );

        if (!countUpdated) {
            return false;
        }

        PostDTO post =
                postDAO.findByPostId(
                        postId
                );

        if (post == null) {
            return true;
        }

        // 自分の投稿にいいねしてもポイント付与しない
        if (loginUserId.equals(post.getUserId())) {
            return true;
        }

        pointService.grantPostLikePoint(
                postId,
                post.getUserId(),
                loginUserId
        );

        return true;
    }

    public boolean isLiked(
            int postId,
            String userId
    ) {
        if (postId <= 0
                || userId == null
                || userId.isBlank()) {

            return false;
        }

        return likeDAO.exists(
                postId,
                userId
        );
    }

}