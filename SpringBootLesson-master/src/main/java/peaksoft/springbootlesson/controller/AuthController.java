package peaksoft.springbootlesson.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.springbootlesson.dto.*;
import peaksoft.springbootlesson.entity.User;
import peaksoft.springbootlesson.repository.UserRepository;
import peaksoft.springbootlesson.security.jwt.JwtTokenFilter;
import peaksoft.springbootlesson.security.jwt.JwtTokenUtil;
import peaksoft.springbootlesson.service.UserService;

@RestController
@RequestMapping("/api/jwt")
@RequiredArgsConstructor
@Tag(name = "Auth api")
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final LoginMapper loginMapper;
    private final AuthenticationManager authenticationManager;
    @PostMapping("sign-up")
    @Operation(summary = "Sign up",description = "User can register")
    public UserResponse signUpa(@RequestBody UserRequest userRequest){
       return userService.create(userRequest);
    }
    @PostMapping("sign-in")
    @Operation(summary = "Sign in ",description = "User can Sign in")
    public LoginResponse signIn(@RequestBody LoginRequest loginRequest){
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword());
        authenticationManager.authenticate(token);
        User user = userRepository.findByUsername(token.getName()).get();
        return loginMapper.loginView(jwtTokenUtil.generateToken(user),"Successful",user);
    }
}
