package org.kd.buggyservice.common;

import org.kd.buggyservice.common.repo.BWLoginRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic() //allow for auth with login and password - necessary for Postman to login
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/stop").permitAll()
                .antMatchers(HttpMethod.GET, "/**").authenticated()
                .antMatchers(HttpMethod.POST, "/create*").hasRole(ADMIN.name())
                .antMatchers(HttpMethod.PUT, "/**").hasRole(ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/**").hasRole(ADMIN.name())
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable();

    }
}
