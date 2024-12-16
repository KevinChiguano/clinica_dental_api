package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.security.JwtGenerator;
import com.example.demo.service.IGestorUsuarioRolService;
import com.example.demo.service.IRolService;
import com.example.demo.service.IUsuarioService;
import com.example.demo.service.dto.UsuarioDTO;
import com.example.demo.service.dto.security.AuthRespuestaDTO;
import com.example.demo.service.dto.security.LoginDTO;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IRolService rolService;
    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private IGestorUsuarioRolService gestorUsuarioRolService;
    @Autowired
    private JwtGenerator jwtGenerator;

    @PostMapping("/registro")
    public ResponseEntity<String> registrar(@RequestBody UsuarioDTO dto) {

        if (usuarioService.buscarPorCedula(dto.getCedula()).isPresent()) {
            return new ResponseEntity<>("Usuario ya existe intenta con otro", HttpStatus.BAD_REQUEST);
        }

        String password = this.passwordEncoder.encode(dto.getPassword());
        dto.setPassword(password);
        this.gestorUsuarioRolService.insertarUsuarioConRol(dto);

        return new ResponseEntity<>("Registro de usuario exitoso", HttpStatus.OK);

    }

    @PostMapping("/login")
    public ResponseEntity<AuthRespuestaDTO> login(@RequestBody LoginDTO dto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            dto.getUsername(), dto.getPassword()        
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generarToken(authentication);

        return new ResponseEntity<>(new AuthRespuestaDTO(token), HttpStatus.OK);

    }

}
