//package com.kobi.flyme.auth;
//
//import com.kobi.flyme.config.JwtService;
//import com.kobi.flyme.model.User;
//import com.kobi.flyme.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//
//
//@Service
//public class AuthService {
//    @Autowired
//    private UserRepository repo;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private JwtService jwtService;
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    public String register(User user) {
//        user = repo.findById(user.getId()).orElse(null);
//        if(user == null) return null;
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setResetPass(false);
//        repo.save(user);
//        return jwtService.generateToken(user);
//    }
//
//    public String logIn(User user){
//        // does all job for me
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        user.getUsername(),
//                        user.getPassword()
//                )
//        );
//        User createdUser = repo.findByUsername(user.getUsername());
//        return jwtService.generateToken(createdUser);
//    }
//}
