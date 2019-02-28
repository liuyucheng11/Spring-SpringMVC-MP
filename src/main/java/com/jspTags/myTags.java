package com.jspTags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * @author:liu.yucheng
 * @Data:2019-2-22 9:11
 * @version:1.0
 */
public class myTags extends TagSupport {
    private String name;
    private PageContext pageContext;
    @Override
    public int doEndTag() throws JspException {
        try{
            this.pageContext.getOut().println("Hello"+name);
        }catch (Exception e){
            throw new JspException();
        }
        return EVAL_PAGE;
    }
    @Override
    public void setPageContext(PageContext pageContext) {
        this.pageContext=pageContext;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
