package com.smart.platform.server;

import com.smart.platform.auth.Userruninfo;
import com.smart.platform.communicate.ClientRequest;
import com.smart.platform.communicate.ServerResponse;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2007-3-26
 * Time: 16:40:58
 * 请求处理接口
 */
public interface RequestProcessIF {
    static int PROCESSED=0;
    static int NOTPROCESS=-1;
    int process(Userruninfo userinfo,ClientRequest req,ServerResponse resp)throws Exception;
    /**
     * 释放资源
     */
    void release();
}
