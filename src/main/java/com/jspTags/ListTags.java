package com.jspTags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.List;

/**
 * @author:liu.yucheng
 * @Data:2019-2-22 11:10
 * @version:1.0
 */
//处理泛型集合
public class  ListTags<T> extends TagSupport {
    private List<T>  list;
    private PageContext pageContext;
    @Override
     public void setPageContext(PageContext pageContext){
        this.pageContext = pageContext;
    }
    @Override
    public int  doEndTag() throws JspException {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < this.list.size() ; i++) {
            stringBuffer.append( list.get(i).toString()+"</br>");
        }
        try {
            this.pageContext.getOut().println(stringBuffer);
        }catch (Exception e){
            throw new JspException();
        }
        return EVAL_PAGE;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
