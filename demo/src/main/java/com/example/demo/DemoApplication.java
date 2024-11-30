package com.example.demo;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.repository.model.Inventario;
import com.example.demo.repository.model.Usuario;
import com.example.demo.service.IGestorUsuarioInventario;
import com.example.demo.service.IGestorUsuarioRolService;
import com.example.demo.service.IUsuarioService;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

	@Autowired
	private IGestorUsuarioRolService usuarioRolService;

	@Autowired
	private IGestorUsuarioInventario usuarioInventario;

	@Autowired
	private IUsuarioService usuarioService;
	

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}


	@Override
	public void run(String... args) throws Exception {
		
		Usuario usuario = new Usuario();
		usuario.setCedula("1714584396");
		usuario.setEmail("paulcc@gmail.com");
		usuario.setFechaCreacion(LocalDateTime.now());
		usuario.setNombre("Paul");
		usuario.setPassword("0987654def");
		usuario.setTelefono("0981742530");

		Map<String, Object> atributos = new HashMap<>();
		atributos.put("codigo", "A123B");


		//usuarioRolService.insertarUsuarioConRol(usuario, "administrador", atributos);

		Usuario usuarioAdmin = this.usuarioService.buscarPorCedula("1714584396").get();

		Inventario inventario = new Inventario();
		inventario.setCantidad(30);
		inventario.setMaterial("Mascarillas");
		inventario.setProveedor("Mascarilla.SA");
		inventario.setUmbralMinimo(10);



		//usuarioInventario.insertarUsuarioInventario(usuarioAdmin.getCedula(), inventario);


	}

}
