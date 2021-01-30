package com.vstore.boot.autoconfigure.security;

import com.vstore.framework.base.util.JsonUtils;
import com.vstore.framework.base.util.Response;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;


/**
 * @author vstore
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Resource
	protected DataSource dataSource;

	@Resource
	private SecurityProperties securityProperties;


	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// 允许 "/setAuth", "/api/**" 不需要权限访问
		http.authorizeRequests().antMatchers("/setAuth", "/api/**").permitAll().and()
				.authorizeRequests().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
			@Override
			public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
				//配置自定义访问决策管理
				fsi.setAccessDecisionManager(accessDecisionManager());
				//配置自定义得权限原始数据
				fsi.setSecurityMetadataSource(mySecurityMetadataSource());
				fsi.setRejectPublicInvocations(true);
				return fsi;
			}
		}).and().formLogin().and().httpBasic() ;

		// 禁用CSRF
		http.csrf().disable();


		//配置自定义的登录处理入口
		http.exceptionHandling().authenticationEntryPoint( new MyLoginUrlAuthenticationEntryPoint( ))
				//访问拒绝时自定义处理
				 .accessDeniedHandler(new MyAccessDeniedHandler())
				//设置退出URL
				 .and().logout().logoutUrl("/logout")
				//设置退出成功的URL
				.logoutSuccessUrl("/loginAjax");

		// 设置登录成功处理
		http.formLogin().successHandler(authenticationSuccessHandler());
		// 设置登录失败处理
		http.formLogin().failureHandler(authenticationFailureHandler());
	}

	/**
	 * 配置基于什么样的方式进行身份认证
	 * @param auth 认证管理构建
	 * @throws Exception 异常
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}


	public AuthenticationProvider authenticationProvider(){
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		// 配置获取登录用户的方式
		authenticationProvider.setUserDetailsService(jdbcDaoImpl());
		// 配置密码编码格式
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	/**
	 * 认证成功处理
	 * @return AuthenticationSuccessHandler
	 */
	public   AuthenticationSuccessHandler authenticationSuccessHandler(){
		return new SavedRequestAwareAuthenticationSuccessHandler() {
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
												Authentication authentication) throws ServletException, IOException {
				try {
					UserDetails uds = (UserDetails)authentication.getPrincipal();
				} catch ( Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage(), e);
				}
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/javascript;charset=utf-8");
				Response<String> responseMsg=new Response<>();
				responseMsg.setResult("登录成功");
				responseMsg.setResCode(200);
				response.getWriter().print(JsonUtils.convert2String(responseMsg)   );

			}
		};

	}

	/**
	 * 认证成功处理
	 * @return AuthenticationFailureHandler
	 */
	public AuthenticationFailureHandler authenticationFailureHandler(){
		return new AuthenticationFailureHandler() {
			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/javascript;charset=utf-8");
				Response<String> responseMsg=new Response<>();
				responseMsg.setResult(exception.getMessage());
				responseMsg.setResCode(403);
				response.getWriter().print(JsonUtils.convert2String(responseMsg)   );
			}
		};

	}


	@Override
	public void configure(WebSecurity web) throws Exception {
		// 忽略静态资源
		web.ignoring().antMatchers( "/static/**" ,"/html/**" ,"/favicon.ico" );
	}

	public AccessDecisionManager accessDecisionManager() {
		return new AccessDecisionManager();

	}

	public MySecurityMetadataSource mySecurityMetadataSource() {
		return  new  MySecurityMetadataSource(dataSource,securityProperties.getLoadAllResourceQuery());

	}


	private JdbcDaoImpl jdbcDaoImpl() {
		JdbcDaoImpl jdbcDaoImpl =new JdbcDaoImpl();
		jdbcDaoImpl.setAuthoritiesByUsernameQuery(securityProperties.getAuthoritiesByUsernameQuery());
		jdbcDaoImpl.setUsersByUsernameQuery(securityProperties.getUsersByUsernameQuery());
		jdbcDaoImpl.setDataSource(dataSource);
		return jdbcDaoImpl;
	}


	@Bean
	public PasswordEncoder passwordEncoder(){
		return new PasswordEncoder() {
			@Override
			public String encode(CharSequence rawPassword) {
				return rawPassword.toString();
			}
			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return rawPassword.toString().equals(encodedPassword);
			}
		};
	}


}
