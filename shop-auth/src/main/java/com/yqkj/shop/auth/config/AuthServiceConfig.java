package com.yqkj.shop.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthServiceConfig extends AuthorizationServerConfigurerAdapter {


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private YqkjOAuth2AuthenticationEntryPoint yqkjOAuth2AuthenticationEntryPoint;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)
            throws Exception {
        endpoints.exceptionTranslator(yqkjOAuth2AuthenticationEntryPoint);
        endpoints.authenticationManager(authenticationManager).tokenStore(redisTokenStore());
    }

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");

    }
    @Bean
    public StringRedisTokenStore redisTokenStore() {
        StringRedisTokenStore tokenStore = new StringRedisTokenStore(redisConnectionFactory);
        tokenStore.setPrefix("com:yqkj:shop:auth");
        return tokenStore;
    }
    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("yc")
                // secret密码配置从 Spring Security 5.0开始必须以 {加密方式}+加密后的密码 这种格式填写
                /*
                 *   当前版本5新增支持加密方式：
                 *   bcrypt - BCryptPasswordEncoder (Also used for encoding)
                 *   ldap - LdapShaPasswordEncoder
                 *   MD4 - Md4PasswordEncoder
                 *   MD5 - new MessageDigestPasswordEncoder("MD5")
                 *   noop - NoOpPasswordEncoder
                 *   pbkdf2 - Pbkdf2PasswordEncoder
                 *   scrypt - SCryptPasswordEncoder
                 *   SHA-1 - new MessageDigestPasswordEncoder("SHA-1")
                 *   SHA-256 - new MessageDigestPasswordEncoder("SHA-256")
                 *   sha256 - StandardPasswordEncoder
                 */
                .secret(passwordEncoder.encode("yc"))
                .scopes("server")
                .authorizedGrantTypes("authorization_code", "password", "refresh_token")
                .autoApprove(true);
    }

}
