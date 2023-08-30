package com.registration.course.clientapp.configs;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // privilage
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/js/**", "/css/**", "/img/**", "/assets/**").permitAll()
        .antMatchers("/login").permitAll()
        .antMatchers("/register").permitAll()
        .antMatchers("/", "/courses/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .successForwardUrl("/dashboard")
        .failureForwardUrl("/login?error=true")
        .permitAll()
        .and()
        .logout()
        .logoutUrl("/logout")
        .permitAll();
    // .and()
    // .exceptionHandling() // Menambahkan konfigurasi untuk exception handling
    // .accessDeniedPage("/error/404"); // Pengalihan ke halaman not found (404)
  }

}
