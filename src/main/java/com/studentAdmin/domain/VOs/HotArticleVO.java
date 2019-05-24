package com.studentAdmin.domain.VOs;

import com.studentAdmin.domain.Article;

/**
 * 热门文章
 *
 * @author: liu.yucheng
 * @Date: 2019-5-21  16:14
 * @version: 1.0
 */
public class HotArticleVO extends Article {
    private long recentClick;
    private int rank;

    public long getRecentClick() {
        return recentClick;
    }

    public void setRecentClick(long recentClick) {
        this.recentClick = recentClick;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
