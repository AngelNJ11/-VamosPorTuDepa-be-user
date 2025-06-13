package pe.vptd.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import pe.vptd.model.InicioSesionDTO;
import pe.vptd.model.RegistrarDTO;
import pe.vptd.model.Usuario;


public interface IUsuarioService {
	public ResponseEntity<Map<String,Object>> InicioSesion(InicioSesionDTO usuario);
	public ResponseEntity<Map<String,Object>> registrarUsuario(RegistrarDTO usuario);
	public ResponseEntity<Map<String,Object>> actualizarUsuario(Usuario usuario,int id);
	public ResponseEntity<Map<String,Object>> eliminarUsuario(int id);
}
