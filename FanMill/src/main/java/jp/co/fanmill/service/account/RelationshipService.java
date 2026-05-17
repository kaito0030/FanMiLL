package jp.co.fanmill.service.account;

import java.util.List;

import jp.co.fanmill.dao.FollowDAO;
import jp.co.fanmill.dto.UserDTO;

public class RelationshipService {

    private static final int DEFAULT_LIMIT = 100;

    private final FollowDAO followDAO =
            new FollowDAO();

    public List<UserDTO> getRelationshipList(
            String userId,
            String type,
            int page
    ) {
        if (userId == null || userId.isBlank()) {
            return List.of();
        }

        if (type == null || type.isBlank()) {
            type = "follow";
        }

        if (page < 1) {
            page = 1;
        }

        int offset =
                (page - 1) * DEFAULT_LIMIT;

        return followDAO.findByType(
                userId,
                type,
                DEFAULT_LIMIT,
                offset
        );
    }

    public boolean isFollowing(
            String followerId,
            String followeeId
    ) {
        if (followerId == null || followerId.isBlank()
                || followeeId == null || followeeId.isBlank()) {
            return false;
        }

        return followDAO.exists(
                followerId,
                followeeId
        );
    }

}