package br.com.adam.studyingspringboot.controllers;

import br.com.adam.studyingspringboot.dtos.AuthenticationDTO;
import br.com.adam.studyingspringboot.dtos.LoginResponseDTO;
import br.com.adam.studyingspringboot.dtos.RegisterDTO;
import br.com.adam.studyingspringboot.infra.security.TokenService;
import br.com.adam.studyingspringboot.repositories.UserRepository;
import br.com.adam.studyingspringboot.model.User;
import br.com.adam.studyingspringboot.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "http://localhost:5175")
public class AuthenticationController {
    @Autowired
    private UserServices userServices;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok().body(new LoginResponseDTO(token));
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data){
        if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        this.repository.save(newUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok().body(userServices.findAll());
    }
}


