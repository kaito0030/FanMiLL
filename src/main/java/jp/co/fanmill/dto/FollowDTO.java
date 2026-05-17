package jp.co.fanmill.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class FollowDTO
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private String followerId;

    private String followerName;

    private String followeeId;

    private String followeeName;

    private Timestamp createdAt;

    public FollowDTO() {

    }

    public String getFollowerId() {

        return followerId;

    }

    public void setFollowerId(
            String followerId
    ) {

        this.followerId = followerId;

    }

    public String getFollowerName() {

        return followerName;

    }

    public void setFollowerName(
            String followerName
    ) {

        this.followerName = followerName;

    }

    public String getFolloweeId() {

        return followeeId;

    }

    public void setFolloweeId(
            String followeeId
    ) {

        this.followeeId = followeeId;

    }

    public String getFolloweeName() {

        return followeeName;

    }

    public void setFolloweeName(
            String followeeName
    ) {

        this.followeeName = followeeName;

    }

    public Timestamp getCreatedAt() {

        return createdAt;

    }

    public void setCreatedAt(
            Timestamp createdAt
    ) {

        this.createdAt = createdAt;

    }

}