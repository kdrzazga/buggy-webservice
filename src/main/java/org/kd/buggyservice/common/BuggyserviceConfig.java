package org.kd.buggyservice.common;

import org.kd.buggyservice.common.repo.BWLoginRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.client.RestTemplate;
import static org.kd.buggyservice.common.repo.Roles.ADMIN;

@Configuration
class BuggyserviceConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public RestUtility restUtility() {
        return new RestUtility();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var repo = new BWLoginRepo();
        return new InMemoryUserDetailsManager(repo.getAdmin(), repo.getUser());
    }
/*
    @Bean
    public AuthenticationManager authenticationManager(){
        new AuthenticationManagerBuilder().inMemoryAuthentication()
                .withUser("user").password("user").roles("USER")
                .and().withUser("admin").password("admin").roles("USER", "ADMIN")
                .and().withUser("user1@example.com").password("user1").roles("USER")
                .and().withUser("admin1@example.com").password("admin1").roles("USER", "ADMIN")
        ;
        return new AuthenticationManagerBuilder().build();
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic() //allow for auth with login and password - necessary for Postman to login
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers(HttpMethod.POST, "/stop").permitAll() //TODO: security error - no login required to stop the app
                .antMatchers(HttpMethod.GET, "/h2-console/**").permitAll() //TODO: security error - anyone can drop whole database
                .antMatchers(HttpMethod.GET, "/**").authenticated()
                .antMatchers(HttpMethod.POST, "/create*").hasRole(ADMIN.name())
                .antMatchers(HttpMethod.PUT, "/**").hasRole(ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/**").hasRole(ADMIN.name())
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                .and()
                .headers().frameOptions().disable() //necessary to allow for proper display of h2-console
                .and()
                .csrf().disable();
    }
}
