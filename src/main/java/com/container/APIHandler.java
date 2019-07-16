package com.container;

import com.studentAdmin.api.base.IComponent;
import org.apache.commons.lang.ArrayUtils;

/**
 * @author: liu.yucheng
 * @Date: 2019-7-15  9:42
 * @version: 1.0
 */
public abstract class APIHandler<T extends IComponent> {

    private APIHandler postHandler;

    protected abstract T component(String id);

    protected abstract T getSupportedCom();

    public final void handle() {
        if (this.getPostHandler() != null) {
            getPostHandler().handle();
        }
        postHandle();
    }

    public APIHandler getPostHandler() {
        return postHandler;
    }

    protected abstract void postHandle();

    public void prepend(APIHandler... nodes) {
        postHandler = nodes[0];
        if (nodes.length > 1) {
            postHandler.prepend((APIHandler[]) ArrayUtils.subarray(nodes, 1, nodes.length));
        }
    }


}
