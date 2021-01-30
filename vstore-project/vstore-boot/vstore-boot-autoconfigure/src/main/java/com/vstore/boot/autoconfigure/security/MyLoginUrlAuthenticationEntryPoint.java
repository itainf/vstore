package com.vstore.boot.autoconfigure.security;


import com.vstore.framework.base.util.JsonUtils;
import com.vstore.framework.base.util.Response;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author vstore
 */
public class MyLoginUrlAuthenticationEntryPoint    implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/javascript;charset=utf-8");
        Response<String> responseMsg=new Response<>();
        responseMsg.setResult("没有登录");
        responseMsg.setResCode(403);
        response.getWriter().print(JsonUtils.convert2String(responseMsg)   );
    }
}
