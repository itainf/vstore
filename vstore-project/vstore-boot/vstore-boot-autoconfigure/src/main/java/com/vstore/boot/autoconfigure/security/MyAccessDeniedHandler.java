package com.vstore.boot.autoconfigure.security;

import com.vstore.framework.base.util.JsonUtils;
import com.vstore.framework.base.util.Response;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author vstore
 */
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/javascript;charset=utf-8");
        Response<String> responseMsg=new Response<>();
        responseMsg.setResult("认证失败");
        responseMsg.setResCode(403);
        response.getWriter().print(JsonUtils.convert2String(responseMsg)   );
    }
}
