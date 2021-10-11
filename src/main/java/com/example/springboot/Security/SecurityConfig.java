package com.example.springboot.Security;

import com.example.springboot.filters.CustomAuthenticationFilter;
import com.example.springboot.filters.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers("/api/login/**","/api/token/refresh/**","/forgot_password","/reset_password","/students/**").permitAll();
        http.authorizeRequests().antMatchers(GET,"/api/users/").hasAnyAuthority("Role_Admin");
        http.authorizeRequests().antMatchers(POST,"/api/user/save/**").hasAnyAuthority("Role_Admin");
        http.authorizeRequests().antMatchers(POST,"/api/role/save/**").hasAnyAuthority("Role_Admin");
        http.authorizeRequests().antMatchers(POST,"/api/role/addToUser/**").hasAnyAuthority("Role_Admin");
        http.authorizeRequests().antMatchers(POST,"/enrolled/**").hasAnyAuthority("Role_Admin");
        http.authorizeRequests().antMatchers(POST,"/enrolled/**").hasAnyAuthority("Role_User");
        http.authorizeRequests().antMatchers(GET,"/semester/**").hasAnyAuthority("Role_User");
        http.authorizeRequests().antMatchers(GET,"/semester/**").hasAnyAuthority("Role_Admin");
        http.authorizeRequests().antMatchers(POST,"/semester/**").hasAnyAuthority("Role_Admin");
        http.authorizeRequests().antMatchers(GET,"/topics/**").hasAnyAuthority("Role_User");
        http.authorizeRequests().antMatchers(GET,"/topics/**").hasAnyAuthority("Role_Admin");
        http.authorizeRequests().antMatchers(POST,"/topics/**").hasAnyAuthority("Role_Admin");
        http.authorizeRequests().antMatchers(GET,"/subjects/**").hasAnyAuthority("Role_User");
        http.authorizeRequests().antMatchers(GET,"/subjects/**").hasAnyAuthority("Role_Admin");
        http.authorizeRequests().antMatchers(POST,"/subjects/**").hasAnyAuthority("Role_Admin");
        //http.authorizeRequests().antMatchers(POST,"/students/**").hasAnyAuthority("Role_Admin");
       // http.authorizeRequests().antMatchers(GET,"/students/**").hasAnyAuthority("Role_User");
        //http.authorizeRequests().antMatchers(GET,"/students/**").hasAnyAuthority("Role_Admin");
        http.authorizeRequests().antMatchers(GET,"/subjectinsemester/**").hasAnyAuthority("Role_User");
        http.authorizeRequests().antMatchers(GET,"/subjectinsemester/**").hasAnyAuthority("Role_Admin");
        http.authorizeRequests().antMatchers(GET,"/gpa/").hasAnyAuthority("Role_User");
        http.authorizeRequests().antMatchers(GET,"/gpa/**").hasAnyAuthority("Role_Admin");
        http.authorizeRequests().antMatchers(GET,"/calculate/**").hasAnyAuthority("Role_User");
        http.authorizeRequests().antMatchers(GET,"/calculate/**").hasAnyAuthority("Role_Admin");
        http.authorizeRequests().antMatchers(GET,"/score/").hasAnyAuthority("Role_User");
        http.authorizeRequests().antMatchers(GET,"/score/**").hasAnyAuthority("Role_Admin");
        http.authorizeRequests().antMatchers(GET,"/latestResult/").hasAnyAuthority("Role_User");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
}
