package pe.vptd.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.vptd.model.InicioSesionDTO;
import pe.vptd.model.RegistrarDTO;
import pe.vptd.model.Usuario;
import pe.vptd.service.IUsuarioService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	
	@PostMapping("/login")
	public ResponseEntity<Map<String,Object>> InicioSesion(@RequestBody InicioSesionDTO usuario){
		return usuarioService.InicioSesion(usuario);
	}
	
	@PostMapping("/signup")
	public  ResponseEntity<Map<String,Object>> registrarUsuario(@RequestBody RegistrarDTO usuario){
		return usuarioService.registrarUsuario(usuario);		
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<Map<String,Object>> actualizarUsuario(@RequestBody Usuario usuario,@PathVariable int id){
		return usuarioService.actualizarUsuario(usuario, id);
	}
	
	@PutMapping("/delete/{id}")
	public ResponseEntity<Map<String,Object>> eliminarUsuario(@PathVariable int id){
		return usuarioService.eliminarUsuario( id);
	}
	
	
}
