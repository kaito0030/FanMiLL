package jp.co.fanmill.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private String userId;

    private String passwordHash;

    private String userName;

    private String profileText;

    private int millPoint;

    private String secretQuestion;

    private String secretAnswerHash;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    /*
     * Constructor
     */
    public UserDTO() {

    }

    /*
     * Getter / Setter
     */

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfileText() {
        return profileText;
    }

    public void setProfileText(String profileText) {
        this.profileText = profileText;
    }

    public int getMillPoint() {
        return millPoint;
    }

    public void setMillPoint(int millPoint) {
        this.millPoint = millPoint;
    }

    public String getSecretQuestion() {
        return secretQuestion;
    }

    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }

    public String getSecretAnswerHash() {
        return secretAnswerHash;
    }

    public void setSecretAnswerHash(String secretAnswerHash) {
        this.secretAnswerHash = secretAnswerHash;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

}