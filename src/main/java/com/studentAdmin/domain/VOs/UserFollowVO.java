package com.studentAdmin.domain.VOs;
import java.util.Date;

/**
 * @author: liu.yucheng
 * @Date: 2019-4-25  13:56
 * @version: 1.0
 */

public class  UserFollowVO extends UserVO {
    private Date createDate;

    private  Long followId;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getFollowId() {
        return followId;
    }

    public void setFollowId(Long followId) {
        this.followId = followId;
    }
}
