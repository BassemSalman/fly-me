//package com.kobi.flyme.auth;
//
//import com.kobi.flyme.model.User;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/auth")
//@RequiredArgsConstructor
//public class AuthController {
//
//    private final AuthService service;
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody User user){
//        return service.logIn(user) != null ? ResponseEntity.status(HttpStatus.ACCEPTED).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    }
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody User user) {
//        String token = service.register(user);
//        return token != null ? ResponseEntity.status(HttpStatus.ACCEPTED).body(token): ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    }
//}
