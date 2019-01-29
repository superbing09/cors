package com.eqxiu.cors.configuration;

import com.eqxiu.cors.filter.MyCorsFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * @author xubing
 * @description //TODO 设计说明
 * @date 19-1-29
 * @copyright 中网易企秀
 */

@ConditionalOnProperty(prefix = AutoCorsConfiguration.MyCorsProperties.PREFIX, name = "allowOrigins")
@EnableConfigurationProperties(value = AutoCorsConfiguration.MyCorsProperties.class)
public class AutoCorsConfiguration {

    @Bean
    public FilterRegistrationBean corsFilter(MyCorsProperties properties) {
        FilterRegistrationBean bean = new FilterRegistrationBean(new MyCorsFilter(properties));
        bean.addUrlPatterns("/*");
        bean.setOrder(0);
        return bean;
    }
    @ConditionalOnBean(value = AutoCorsConfiguration.class)
    @ConfigurationProperties(MyCorsProperties.PREFIX)
    public class MyCorsProperties {
        public static final String PREFIX = "web.cors";

        private String allowSubdomains = "false";

        private String allowOrigins = "*";

        private String allowCredentials = "true";

        public String getAllowSubdomains() {
            return allowSubdomains;
        }

        public void setAllowSubdomains(String allowSubdomains) {
            this.allowSubdomains = allowSubdomains;
        }

        public String getAllowOrigins() {
            return allowOrigins;
        }

        public void setAllowOrigins(String allowOrigins) {
            this.allowOrigins = allowOrigins;
        }

        public String getAllowCredentials() {
            return allowCredentials;
        }

        public void setAllowCredentials(String allowCredentials) {
            this.allowCredentials = allowCredentials;
        }
    }

}




