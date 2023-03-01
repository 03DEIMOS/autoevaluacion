package com.utb.autoevaluacion.saml.security;

import static org.springframework.http.HttpMethod.GET;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
@Order(2)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/admin/**").permitAll()
                .antMatchers("/home").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/font/**").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/media/**").permitAll()
                .antMatchers("/thumbnails/**").permitAll()
                .antMatchers("/sp/discovery/**").permitAll()
                .antMatchers(GET, "/public").permitAll()
                .antMatchers(GET, "/api/samlLink").permitAll()
                .antMatchers("/**").authenticated()
                .and()
                .formLogin().loginPage("/public");
        ;

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new CustomLogoutSuccessHandler())
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
        http.csrf().disable();
        http.headers()
                .frameOptions().disable()
                .cacheControl().disable();
    }
}
