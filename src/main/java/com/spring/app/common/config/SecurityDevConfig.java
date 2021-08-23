package com.spring.app.common.config;

import com.spring.app.common.condition.DevCondition;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Conditional(DevCondition.class)
@Configuration
public class SecurityDevConfig extends WebSecurityConfigurerAdapter {
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**");
    }
}
