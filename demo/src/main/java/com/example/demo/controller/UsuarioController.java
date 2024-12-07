package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.model.Usuario;
import com.example.demo.service.IGestorUsuarioRolService;
import com.example.demo.service.IUsuarioService;
import com.example.demo.service.dto.UsuarioDTO;

@RestController
@CrossOrigin
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IGestorUsuarioRolService gestorUsuarioRolService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> insertar(@RequestBody UsuarioDTO usuarioDTO) {

        return new ResponseEntity<>(this.gestorUsuarioRolService.insertarUsuarioConRol(usuarioDTO), null,
                HttpStatus.OK);

    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Usuario>> consultarPorId(@PathVariable Integer id) {

        Optional<Usuario> usuario = this.usuarioService.buscarPorId(id);

        return new ResponseEntity<>(usuario, null, HttpStatus.OK);

    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Usuario>> consultarUsuarios() {

        List<Usuario> usuarios = this.usuarioService.buscarTodosLosUsuarios();

        return new ResponseEntity<>(usuarios, null, HttpStatus.OK);

    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDTO> actualizar(@RequestBody UsuarioDTO usuarioDTO) {

        UsuarioDTO usuarioTmp = this.gestorUsuarioRolService.actualizarUsuarioRol(usuarioDTO);

        return new ResponseEntity<>(usuarioTmp, null, HttpStatus.OK);

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> eliminarPorId(@PathVariable Integer id) {
        return new ResponseEntity<>(this.usuarioService.eliminar(id), null, HttpStatus.OK);
    }

}
