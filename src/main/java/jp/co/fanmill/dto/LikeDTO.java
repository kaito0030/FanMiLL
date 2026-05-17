package jp.co.fanmill.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class LikeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int postId;

    private String userId;
    private String userName;

    private Timestamp createdAt;

    public LikeDTO() {

    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

}