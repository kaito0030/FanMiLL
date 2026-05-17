package jp.co.fanmill.service.point;

import jp.co.fanmill.dao.PointHistoryDAO;
import jp.co.fanmill.dao.UserDAO;
import jp.co.fanmill.dto.PointHistoryDTO;

public class PointService {

    private static final int POST_LIKE_POINT = 1;

    private static final String CONTENT_TYPE_POST = "POST";

    private static final String REASON_POST_LIKE = "POST_LIKE";

    private final PointHistoryDAO pointHistoryDAO =
            new PointHistoryDAO();

    private final UserDAO userDAO =
            new UserDAO();

    /**
     * 投稿いいねによるポイント付与
     * 初回POST_LIKEのみ付与
     */
    public boolean grantPostLikePoint(
            int postId,
            String toUserId,
            String fromUserId
    ) {
        if (postId <= 0
                || toUserId == null
                || toUserId.isBlank()
                || fromUserId == null
                || fromUserId.isBlank()) {

            return false;
        }

        if (toUserId.equals(fromUserId)) {
            return false;
        }

        boolean alreadyGranted =
                pointHistoryDAO.exists(
                        CONTENT_TYPE_POST,
                        postId,
                        fromUserId,
                        REASON_POST_LIKE
                );

        if (alreadyGranted) {
            return false;
        }

        PointHistoryDTO history =
                new PointHistoryDTO();

        history.setUserId(toUserId);
        history.setContentType(CONTENT_TYPE_POST);
        history.setContentId(postId);
        history.setFromUserId(fromUserId);
        history.setPoint(POST_LIKE_POINT);
        history.setReason(REASON_POST_LIKE);

        boolean inserted =
                pointHistoryDAO.insert(history);

        if (!inserted) {
            return false;
        }

        return userDAO.addMillPoint(
                toUserId,
                POST_LIKE_POINT
        );
    }

}