package spdu2022.java.project.beutysalon.security.controllers;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import spdu2022.java.project.beutysalon.security.models.SecurityUser;
import spdu2022.java.project.beutysalon.security.persistence.reposetories.UserSecurityRepository;
import spdu2022.java.project.beutysalon.security.services.jwt_token_services.JwtTokenProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/jwt_auth")
@Profile("security_jwt")
public class AuthJwtController {
    private final AuthenticationManager authenticationManager;
    private final UserSecurityRepository repository;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthJwtController(AuthenticationManager authenticationManager, UserSecurityRepository repository, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthDTO authDTO) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDTO.getLogin(), authDTO.getPassword()));
            SecurityUser securityUser = repository.findUserByLogin(authDTO.getLogin());
            String token = jwtTokenProvider.createToken(authDTO.getLogin(), securityUser.getRole().name());
            Map<Object, Object> response = new HashMap<>();
            response.put("login" , authDTO.getLogin());
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Invalid login/password combination", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request,response, null);
    }

}