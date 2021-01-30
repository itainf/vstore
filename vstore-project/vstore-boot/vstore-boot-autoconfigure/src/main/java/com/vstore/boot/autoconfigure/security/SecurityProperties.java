package com.vstore.boot.autoconfigure.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @author vstore
 */
@Component
@ConfigurationProperties(prefix = "spring.security")
public class SecurityProperties {

    private String ssoServer;
    private String clientServer;
    private String clientId;
    private String clientSecret;
    /**加载所有资源**/
    private String loadAllResourceQuery;

    /**通过用户名加载用户**/
    private String usersByUsernameQuery;

    /**通过用户名加载权限*/
    private String authoritiesByUsernameQuery;

    private String ignoreMatchers;


    public String getSsoServer() {
        return ssoServer;
    }

    public void setSsoServer(String ssoServer) {
        this.ssoServer = ssoServer;
    }

    public String getClientServer() {
        return clientServer;
    }

    public void setClientServer(String clientServer) {
        this.clientServer = clientServer;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getLoadAllResourceQuery() {
        return loadAllResourceQuery;
    }

    public void setLoadAllResourceQuery(String loadAllResourceQuery) {
        this.loadAllResourceQuery = loadAllResourceQuery;
    }



    public String getIgnoreMatchers() {
        return ignoreMatchers;
    }

    public void setIgnoreMatchers(String ignoreMatchers) {
        this.ignoreMatchers = ignoreMatchers;
    }

    public String getUsersByUsernameQuery() {
        return usersByUsernameQuery;
    }

    public void setUsersByUsernameQuery(String usersByUsernameQuery) {
        this.usersByUsernameQuery = usersByUsernameQuery;
    }

    public String getAuthoritiesByUsernameQuery() {
        return authoritiesByUsernameQuery;
    }

    public void setAuthoritiesByUsernameQuery(String authoritiesByUsernameQuery) {
        this.authoritiesByUsernameQuery = authoritiesByUsernameQuery;
    }
}
