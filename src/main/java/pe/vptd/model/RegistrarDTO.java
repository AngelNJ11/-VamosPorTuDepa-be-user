package pe.vptd.model;



import lombok.Data;

@Data
public class RegistrarDTO {
	
    private String nombre;
    private String apellido;
    private String email;
    private String contrasena1;
    private String contrasena2;
    private String telefono;
}
