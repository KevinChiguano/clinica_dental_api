package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.model.Inventario;
import com.example.demo.service.IGestorUsuarioInventario;
import com.example.demo.service.IInventarioService;
import com.example.demo.service.dto.InventarioDTO;

@RestController
@CrossOrigin
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    private IInventarioService inventarioService;

    @Autowired
    private IGestorUsuarioInventario usuarioInventario;

    @PostMapping
    public ResponseEntity<InventarioDTO> insertar(@RequestBody InventarioDTO inventarioDTO) {
        InventarioDTO inventario = this.usuarioInventario.insertarUsuarioInventario(inventarioDTO);
        return new ResponseEntity<>(inventario, null, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Inventario>> consultarPorId(@PathVariable Integer id) {

        Optional<Inventario> inventario = this.inventarioService.buscarPorId(id);

        return new ResponseEntity<>(inventario, null, HttpStatus.OK);

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Inventario>> consultarInventarios() {

        List<Inventario> inventarios = this.inventarioService.buscarTodoInventario();

        return new ResponseEntity<>(inventarios, null, HttpStatus.OK);

    }

    @PutMapping(produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
    public ResponseEntity<Inventario> actualizar(@RequestBody Inventario inventario) {
        return new ResponseEntity<>(this.inventarioService.actualizar(inventario), null, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> eliminarPorId(@PathVariable Integer id) {
        return new ResponseEntity<>(this.inventarioService.eliminar(id), null, HttpStatus.OK);
    }

}
