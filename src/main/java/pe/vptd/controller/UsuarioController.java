package pe.vptd.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.vptd.model.InicioSesionDTO;
import pe.vptd.model.RegistrarDTO;
import pe.vptd.service.IUsuarioService;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	
}
