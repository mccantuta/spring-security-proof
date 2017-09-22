package com.mccl.springsecurity.learning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/public/**").anonymous()
                .antMatchers("/resource").hasRole("USER_RESOURCE")
                .anyRequest().authenticated().and()
                //.formLogin().loginPage("/login.html").successForwardUrl("/resource").permitAll().and()
                .formLogin().permitAll().and()
                .csrf().disable();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        /*manager.createUser(User.withUsername("user").password("password").roles("USER").build());
        manager.createUser(User.withUsername("resource").password("password").roles("USER_RESOURCE").build());*/
        CompanyUser companyUser = new CompanyUser();
        companyUser.setUserName("user");
        companyUser.setPassword("password");
        companyUser.addRole("USER");
        manager.createUser(companyUser);
        CompanyUser resourceUser = new CompanyUser();
        resourceUser.setUserName("resource");
        resourceUser.setPassword("password");
        resourceUser.addRole("ROLE_USER_RESOURCE");
        manager.createUser(resourceUser);

        return manager;
    }


}
