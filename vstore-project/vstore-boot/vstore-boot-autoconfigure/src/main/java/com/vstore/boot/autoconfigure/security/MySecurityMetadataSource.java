package com.vstore.boot.autoconfigure.security;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	private static final Logger logger = LoggerFactory.getLogger(MySecurityMetadataSource.class);

	private DataSource dataSource;
	private String resourceQuery;
	private AntPathMatcher antPathMatcher=new AntPathMatcher();

	private static Map<String, Collection<ConfigAttribute>> resourceMap = new LinkedHashMap<String, Collection<ConfigAttribute>>();

	public MySecurityMetadataSource(DataSource dataSource, String resourceQuery) {
		this.dataSource = dataSource;
		this.resourceQuery = resourceQuery;
		loadResourceDefine();
	}

	private void loadResourceDefine() {
		if (logger.isDebugEnabled()) {
			logger.debug("load resource defination begin ......");
		}

		List<Resource> resources = new ResourceQuery(dataSource,resourceQuery).execute();

		for (Resource resource : resources) {
			String us=resource.getUrls();
			if(StringUtils.isBlank(us)){
				continue;
			}

			us=us.replaceAll("\n", "");
			String[] urls = us.split(";");
			String code=resource.getCode();

			if(StringUtils.isNotBlank(code)   && !ArrayUtils.isEmpty(urls)){
				for(String url:urls){
					if(StringUtils.isBlank(url)){
						continue;
					}

					if(!url.contains("@")){
						url="ALL@"+url;
					}

					Collection<ConfigAttribute> configAttrs = resourceMap.get(url);
					if(configAttrs==null){
						configAttrs=new ArrayList<ConfigAttribute>();
						resourceMap.put(url, configAttrs);
					}
					configAttrs.add(new SecurityConfig(code));
				}
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("load resource defination complated ......");
		}
	}



	@Override
    public Collection<ConfigAttribute> getAttributes(Object object)	throws IllegalArgumentException {

		String reqUrl = ((FilterInvocation) object).getRequest().getRequestURI();
		String method=((FilterInvocation) object).getRequest().getMethod();

		Iterator<String> ite = resourceMap.keySet().iterator();
		Collection<ConfigAttribute> ret= new ArrayList<ConfigAttribute>();

		while (ite.hasNext()) {
			String resUrl = ite.next();
			String resPath=resUrl;
			String url=reqUrl;
			if(resPath.startsWith("ALL@")){
				resPath=resPath.substring(4);
			}else{
				url=method+"@"+url;
			}

			if (antPathMatcher.match(resPath, url)) {
				 Collection<ConfigAttribute> returnCollection = resourceMap.get(resUrl);
				 ret.addAll(returnCollection);
			}

		}
		return ret;
	}

	@Override
    public boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	public void reloadResource(){
		resourceMap.clear();
		loadResourceDefine();
	}

	private class Resource {
		private String urls;
		private String code;
		public Resource(String urls, String code) {
			this.urls = urls;
			this.code = code;
		}

		public String getUrls() {
			return urls;
		}

		public String getCode() {
			return code;
		}

	}

	private class ResourceInsert extends SqlUpdate {
		protected ResourceInsert(DataSource dataSource,String sql){
			super(dataSource,sql);
			compile();
		}
	}

	private class ResourceQuery extends MappingSqlQuery<Resource> {
		protected ResourceQuery(DataSource dataSource, String resourceQuery) {
			super(dataSource, resourceQuery);
			compile();
		}

		@Override
        protected Resource mapRow(ResultSet rs, int rownum) throws SQLException {
			String urls = rs.getString("urls");
			String code = StringUtils.defaultString(rs.getString("code"));
			Resource resource = new Resource(urls,code);

			return resource;
		}
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String getResourceQuery() {
		return resourceQuery;
	}

	public void setResourceQuery(String resourceQuery) {
		this.resourceQuery = resourceQuery;
	}

}
