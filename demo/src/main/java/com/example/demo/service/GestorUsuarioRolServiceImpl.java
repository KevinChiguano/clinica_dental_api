package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.factory.UsuarioRolFactory;
import com.example.demo.repository.model.Administrador;
import com.example.demo.repository.model.Dentista;
import com.example.demo.repository.model.Paciente;
import com.example.demo.repository.model.Rol;
import com.example.demo.repository.model.Usuario;
import com.example.demo.service.dto.UsuarioDTO;

import jakarta.transaction.Transactional;

@Service
public class GestorUsuarioRolServiceImpl implements IGestorUsuarioRolService {

    @Autowired
    private IAdministradorService administradorService;

    @Autowired
    private IDentistaService dentistaService;

    @Autowired
    private IPacienteService pacienteService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    IRolService rolService;

    @Autowired
    private UsuarioRolFactory usuarioRolFactory;

    @Transactional
    @Override
    public Usuario insertarUsuarioConRol(UsuarioDTO usuarioDTO) {

        Usuario usuario = this.convertirUsuario(usuarioDTO);

        Rol rol = this.rolService.buscarPorNombre(usuarioDTO.getRol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        usuario.setFechaCreacion(LocalDateTime.now());
        usuario.setActivo(true);
        usuario.setRol(rol);



        Usuario usuarioCreado = this.usuarioService.insertar(usuario);

        Object usuarioFactory = this.usuarioRolFactory.crearEntidadPorRol(usuarioDTO.getRol(), usuarioCreado,
                usuarioDTO.getAtributos());

        if (usuarioFactory instanceof Administrador) {

            this.administradorService.insertar((Administrador) usuarioFactory);

        } else if (usuarioFactory instanceof Dentista) {

            this.dentistaService.insertar((Dentista) usuarioFactory);

        } else if (usuarioFactory instanceof Paciente) {

            this.pacienteService.insertar((Paciente) usuarioFactory);

        } else {

            throw new RuntimeException("Usuario Factory desconocido para el rol");

        }

        return usuarioCreado;

    }

    @Transactional
    @Override
    public UsuarioDTO actualizarUsuarioRol(UsuarioDTO usuarioDTO) {
        // Validar y actualizar la entidad específica según el rol

        Usuario usuarioTmp = this.usuarioService.buscarPorCedula(usuarioDTO.getCedula())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        switch (usuarioDTO.getRol().toLowerCase()) {
            case "administrador":
                Administrador administrador = this.administradorService.buscarPorUsuarioId(usuarioTmp.getId())
                        .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));
                administrador.setCodigo((String) usuarioDTO.getAtributos().get("codigo"));
                this.administradorService.actualizar(administrador);
                break;

            case "dentista":
                Dentista dentista = this.dentistaService.buscarPorUsuarioId(usuarioTmp.getId())
                        .orElseThrow(() -> new RuntimeException("Dentista no encontrado"));
                dentista.setEspecialidad((String) usuarioDTO.getAtributos().get("especialidad"));
                this.dentistaService.actualizar(dentista);
                break;

            case "paciente":
                Paciente paciente = this.pacienteService.buscarPorUsuarioId(usuarioTmp.getId())
                        .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
                paciente.setEdad((Integer) usuarioDTO.getAtributos().get("edad"));
                this.pacienteService.actualizar(paciente);
                break;

            default:
                throw new RuntimeException("Rol desconocido: " + usuarioDTO.getRol());
        }

        System.out.println(usuarioDTO.getNombre());

        Usuario u = this.convertirUsuario(usuarioDTO);

        System.out.println(u.toString());

        u.setRol(usuarioTmp.getRol());

        Usuario usuarioActualizado = this.usuarioService.actualizar(u);

        // Crear y retornar UsuarioDTO

        UsuarioDTO usuarioDTOTmp = convertirUsuarioDTO(usuarioActualizado);

        // Solo incluir los atributos específicos del rol en el DTO
        switch (usuarioDTO.getRol().toLowerCase()) {
            case "administrador":
                usuarioDTOTmp.setAtributos(Map.of("codigo", usuarioDTO.getAtributos().get("codigo")));
                break;
            case "dentista":
                usuarioDTOTmp.setAtributos(Map.of("especialidad", usuarioDTO.getAtributos().get("especialidad")));
                break;
            case "paciente":
                usuarioDTOTmp.setAtributos(Map.of("edad", usuarioDTO.getAtributos().get("edad")));
                break;
        }

        return usuarioDTOTmp;
    }

    private Usuario convertirUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuarioTmp = new Usuario();
        usuarioTmp.setId(usuarioDTO.getId());
        usuarioTmp.setActivo(usuarioDTO.getActivo());
        usuarioTmp.setCedula(usuarioDTO.getCedula());
        usuarioTmp.setEmail(usuarioDTO.getEmail());
        usuarioTmp.setFechaCreacion(usuarioDTO.getFechaCreacion());
        usuarioTmp.setNombre(usuarioDTO.getNombre());
        usuarioTmp.setPassword(usuarioDTO.getPassword());
        usuarioTmp.setTelefono(usuarioDTO.getTelefono());

        return usuarioTmp;
    }

    private UsuarioDTO convertirUsuarioDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setActivo(usuario.getActivo());
        usuarioDTO.setCedula(usuario.getCedula());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setFechaCreacion(usuario.getFechaCreacion());
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setTelefono(usuario.getTelefono());
        usuarioDTO.setPassword(usuario.getPassword());
        usuarioDTO.setRol(usuario.getRol().getNombre());
        return usuarioDTO;
    }

}
