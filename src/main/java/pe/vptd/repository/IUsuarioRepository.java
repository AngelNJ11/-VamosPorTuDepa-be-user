package pe.vptd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.vptd.model.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario,Integer> {
	
	boolean existsByEmail(String email);
	Usuario findByEmailAndContrasena(String email, String contrasena);
	Usuario findByEmail(String email);
	

}
