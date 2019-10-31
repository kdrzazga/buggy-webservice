package org.kd.buggyservice.common;

import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Import(BuggyserviceConfig.class)
public class AuthorityUtils {

    //@Autowired
    private AuthenticationManager authenticationManager;

    public void loginDirectly(String email, String password) {
        var loginToken = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authenticatedUser = authenticationManager.authenticate(loginToken);
        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }
}
