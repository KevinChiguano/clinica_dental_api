package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.model.Inventario;
import com.example.demo.repository.model.Usuario;
import com.example.demo.service.dto.InventarioDTO;

import jakarta.transaction.Transactional;

@Service
public class GestorUsuarioInventario implements IGestorUsuarioInventario {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IInventarioService inventarioService;

    @Transactional
    @Override
    public InventarioDTO insertarUsuarioInventario(InventarioDTO inventarioDTO) {

        Usuario usuario = this.usuarioService.buscarPorCedula(inventarioDTO.getCedula())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con la cedula: " + inventarioDTO.getCedula()));

        
        validarRolAdministrador(usuario);

        Inventario inventario = this.convertirInventario(inventarioDTO);

        inventario.setAdministrador(usuario.getAdministradores().getFirst());
        Inventario inventarioTmp = this.inventarioService.insertar(inventario);

        InventarioDTO inventarioCreado = this.convertirInventarioDTO(inventarioTmp, inventarioDTO.getCedula());
        

        return inventarioCreado;

    }

    private void validarRolAdministrador(Usuario usuario) {
        if (!"administrador".equals(usuario.getRol().getNombre())) {
            throw new IllegalArgumentException("El usuario no tiene permisos de administrador");
        }
    }

    public InventarioDTO convertirInventarioDTO(Inventario inventario, String cedula){

        InventarioDTO dto = new InventarioDTO();
        dto.setCantidad(inventario.getCantidad());
        dto.setCedula(cedula);
        dto.setId(inventario.getId());
        dto.setMaterial(inventario.getMaterial());
        dto.setProveedor(inventario.getProveedor());
        dto.setUmbralMinimo(inventario.getUmbralMinimo());

        return dto;

    }

    public Inventario convertirInventario(InventarioDTO dto){

        Inventario inventario = new Inventario();
        inventario.setCantidad(dto.getCantidad());
        inventario.setId(dto.getId());
        inventario.setMaterial(dto.getMaterial());
        inventario.setProveedor(dto.getProveedor());
        inventario.setUmbralMinimo(dto.getUmbralMinimo());

        return inventario;
    }

}
