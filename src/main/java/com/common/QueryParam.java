package com.common;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 适配前端分页插件 便于分页查询
 *
 * @author:liu.yucheng
 * @Data:2019-3-16 16:31
 * @version:1.0
 */
public class QueryParam extends LinkedHashMap<String, Object> {
    //当前页码
    private int page;
    //每页条数
    private int limit;

    public QueryParam(Map<String,Object> params){
        this.putAll(params);
        this.page = Integer.parseInt(params.get("page").toString());
        this.limit = Integer.parseInt(params.get("limit").toString());
        this.put("offset",(page-1)*limit);
        this.put("limit",limit);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
