package pe.vptd.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pe.vptd.model.InicioSesionDTO;
import pe.vptd.model.RegistrarDTO;
import pe.vptd.model.Usuario;
import pe.vptd.model.constante.Estado;
import pe.vptd.model.constante.Rol;
import pe.vptd.repository.IUsuarioRepository;
import pe.vptd.service.IUsuarioService;

@Service
public class IUsuarioServiceImpl implements IUsuarioService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private IUsuarioRepository usuarioRepo;

	@Override
	public ResponseEntity<Map<String, Object>> InicioSesion(InicioSesionDTO usuario) {
		Map<String, Object> res= new HashMap<>();
		Usuario usuarioDB = usuarioRepo.findByEmail(usuario.getEmail());
		
		if(usuarioDB.equals(null)) {
			res.put("mensaje", "No se encontro el Usuario con ese correo");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
		}if(usuarioDB.getEstado().equals(Estado.ELIMINADO)||usuarioDB.getEstado().equals(Estado.BLOQUEADO)) {
			res.put("mensaje", "User is Blockado o Eliminado");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
		}if(passwordEncoder.matches(usuario.getContrasena(), usuarioDB.getContrasena())) {
			res.put("mensaje", "Inicio Sesion");
			res.put("status",HttpStatus.OK);
			res.put("usuario", usuarioDB);			
			return ResponseEntity.status(HttpStatus.OK).body(res);
		}else {
			res.put("mensaje", "Contraseña Incorrecta");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
			
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> registrarUsuario(RegistrarDTO usuario) {
		
		String contrasenaEncriptado = passwordEncoder.encode(usuario.getContrasena1());
        System.out.println("Password encriptado: " + contrasenaEncriptado);
        
        Usuario usuarioExistente = usuarioRepo.findByEmailAndContrasena(usuario.getEmail(),usuario.getContrasena1());
	
        System.out.println("usuario exictente:"+ usuarioExistente);
        
		Map<String, Object> res = new HashMap<>();
		if(usuarioRepo.existsByEmail(usuario.getEmail())) {
			res.put("mensaje", "el correo ay esta en uso");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
		}
		if(!(usuarioExistente==null)){
			res.put("mensaje", "error en correo o contraseña");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
		}if(!usuario.getContrasena1().equals(usuario.getContrasena2())){
			res.put("mensaje", "Error las claves no son iguales");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
		}else {
			Usuario u = new Usuario();
			u.setNombre(usuario.getNombre());
			u.setApellido(usuario.getApellido());
			u.setEmail(usuario.getEmail());
			u.setContrasena(contrasenaEncriptado);
			u.setTelefono(usuario.getTelefono());
			u.setRol(Rol.CLIENTE);
			u.setEstado(Estado.ACTIVO);
			u.setFecha_creado(LocalDateTime.now());
			
			usuarioRepo.save(u);
			res.put("mensaje", "Usuario Registrado");
			res.put("status",HttpStatus.OK);
			res.put("usuario", u);
			return ResponseEntity.status(HttpStatus.OK).body(res);
			
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> actualizarUsuario(Usuario user, int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
