package com.ms3.demo.controller;

import com.ms3.demo.model.entities.AuthRequest;
import com.ms3.demo.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@AllArgsConstructor
@RestController
public class AuthenticationController {

    private JwtUtil jwtUtil;

    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {

        try {
            authenticationManager.authenticate(//this line validate the username and password, then we generate the token
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        } catch (Exception ex) {
            throw new Exception("invalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }

    @PostMapping("/logoutUser")
    public void goOut(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }
}
