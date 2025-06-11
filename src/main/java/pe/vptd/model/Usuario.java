package pe.vptd.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import pe.vptd.model.constante.Estado;
import pe.vptd.model.constante.Rol;

@Entity
@Data
@Table(name = "usuarios")
public class Usuario {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String contrasena;
    private String telefono;
    private Rol rol;
    private Estado estado;
    private LocalDateTime fecha_creado;
    private LocalDateTime fecha_modificacion;
	
}