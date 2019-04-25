package com.studentAdmin.domain.VOs;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author: liu.yucheng
 * @Date: 2019-4-25  13:56
 * @version: 1.0
 */

public class UserFollowVO extends UserVO {
    Date createDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
