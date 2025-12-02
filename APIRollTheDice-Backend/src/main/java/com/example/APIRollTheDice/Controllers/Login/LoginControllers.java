package com.example.APIRollTheDice.Controllers.Login;

import com.example.APIRollTheDice.Enum.RoleUser;
import com.example.APIRollTheDice.Model.DTO.Login.JwtReponse;
import com.example.APIRollTheDice.Model.DTO.Login.LoginRequest;
import com.example.APIRollTheDice.Model.DTO.Login.RegisterRequest;
import com.example.APIRollTheDice.Model.Obj.User.User;
import com.example.APIRollTheDice.Security.Service.CustomUserDetailsService;
import com.example.APIRollTheDice.Security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/")
public class LoginControllers {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CustomUserDetailsService userDetailsService;


    @Autowired
    private PasswordEncoder passwordEncoder;


   @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
       try {

           Authentication authentication = authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(
                           loginRequest.getUsername(),
                           loginRequest.getPassword()
                   )
           );

           SecurityContextHolder.getContext().setAuthentication(authentication);

           UserDetails user = (UserDetails)  authentication.getPrincipal();

           String role = user.getAuthorities().iterator().next().toString();
           RoleUser roleUser = RoleUser.valueOf(role.replace("ROLE_",""));
           Long userId = userDetailsService.getUserIdByUsername(authentication.getName());

           String jwt = jwtUtils.generateToken(authentication.getName(),roleUser.name(),userId);

           return ResponseEntity.ok(new JwtReponse(jwt,roleUser,userId));
       } catch (Exception e) {
           return ResponseEntity.status(400).body("Invalid username or password");
       }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest signUpRequest) {

       if(userDetailsService.existsByUsername(signUpRequest.getUsername())) {
           return ResponseEntity
                   .badRequest()
                   .body("Error: Username is already taken!");
       }

       if(userDetailsService.existsByEmail(signUpRequest.getEmail())) {
           return ResponseEntity
                   .badRequest()
                   .body("Error: Email is already in use!");
         }

        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        userDetailsService.SaveUser(user);

        return ResponseEntity.ok("User registered successfully!");



   }


}
