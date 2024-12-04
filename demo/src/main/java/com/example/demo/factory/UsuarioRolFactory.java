package com.example.demo.factory;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.demo.repository.model.Administrador;
import com.example.demo.repository.model.Dentista;
import com.example.demo.repository.model.Paciente;
import com.example.demo.repository.model.Usuario;

@Component
public class UsuarioRolFactory {

    public Object crearEntidadPorRol(String rol, Usuario usuario, Map<String, Object> atributos){

        switch (rol.toLowerCase()) {
            case "administrador":
                Administrador administrador = new Administrador();
                administrador.setCodigo((String) atributos.get("codigo"));
                administrador.setUsuario(usuario);
                return administrador;
        
            case "dentista":
                Dentista dentista = new Dentista();
                dentista.setEspecialidad((String) atributos.get("especialidad"));
                dentista.setUsuario(usuario);
                return dentista;

            case "paciente":
                Paciente paciente = new Paciente();
                paciente.setEdad((Integer) atributos.get("edad"));
                paciente.setUsuario(usuario);
                return paciente;

            default:
                throw new RuntimeException("Rol no válido: " + rol);
        }

    }
    
    
}
