package jp.co.fanmill.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class PostDTO implements Serializable {

    private static final long serialVersionUID = 2L;

    private int postId;

    private Integer parentPostId;/*add on phase2*/
    
    private String userId;

    private String userName;

    private String content;
    
    private int numLike;/*add on phase2*/
    
    private int numReply;/*add on phase2*/

    private Timestamp createdAt;

    private Timestamp updatedAt;

    /*
     * Constructor
     */
    public PostDTO() {

    }

    /*
     * Getter / Setter
     */
    /*add on phase2*/
    public Integer  getParentPostId() {
    	     return this.parentPostId;
    }
    /*add on phase2*/
    public void setParentPostId(Integer parentPostId) {
    	     this.parentPostId=parentPostId;
    }
    /*add on phase2*/
   public int getNumLike() {
	   return this.numLike;
   }
   /*add on phase2*/
   public void setNumLike(int numLike) {
	   this.numLike=numLike;
   }
   /*add on phase2*/
   public int getNumReply() {
	   return this.numReply;
   }
   /*add on phase2*/
   public void setNumReply(int numReply) {
	   this.numReply=numReply;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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