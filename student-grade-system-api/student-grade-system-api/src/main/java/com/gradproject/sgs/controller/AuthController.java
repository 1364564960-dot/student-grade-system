package com.gradproject.sgs.controller;

import com.gradproject.sgs.dto.LoginRequest;
import com.gradproject.sgs.dto.LoginResponse;
import com.gradproject.sgs.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        // 1. 使用 AuthenticationManager 进行用户认证
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password())
        );

        // 2. 如果认证成功，获取 UserDetails
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // 3. 生成 JWT
        String token = jwtUtil.generateToken(userDetails);

        // 4. 返回 JWT
        return new LoginResponse(token);
    }
}
