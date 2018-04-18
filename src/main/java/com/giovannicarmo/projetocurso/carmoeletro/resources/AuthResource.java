package com.giovannicarmo.projetocurso.carmoeletro.resources;

import com.giovannicarmo.projetocurso.carmoeletro.security.JWTUtil;
import com.giovannicarmo.projetocurso.carmoeletro.security.UserSS;
import com.giovannicarmo.projetocurso.carmoeletro.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UserSS userSS = UserService.authenticated();
        String token = jwtUtil.generateToken(userSS.getUsername());
        response.addHeader("Authorization", "Bearer "+ token);
        return ResponseEntity.noContent().build();
    }
}
