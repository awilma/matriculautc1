package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.junit.internal.matchers.Each;

import net.sf.oval.constraint.Email;
import net.sf.oval.constraint.MinLength;
import play.data.validation.Match;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.data.validation.Unique;
import play.db.jpa.Model;


@Entity
public class Usuario extends Model {
	
	
	@Match(value="[0-9]+$",message="Ingrese solo números")
	@Required(message="Ingrese el número de cedula")
	@MaxSize(value=10,message="La cédula no puede tener más de 10 dígitos")
	@MinLength(value=10,message="La cédula no puede tener menos de 10 dígitos")
	@Unique(message="Esta cédula ya ha sido Registrada Anteriormente")	
	 public String cedula;
	
	@Required(message="Ingrese los apellidos")
	@Match(value="[áéíóúÁÉÍÓÚñÑA-Za-z ]+$",message="Ingrese solo letras")
	 public String nombre;
	
	@Required(message="Ingrese los apellidos")
	@Match(value="[áéíóúÁÉÍÓÚñÑA-Za-z ]+$",message="Ingrese solo letras")
	 public String apellido;
	
	@Match(value="[0-9]+$",message="Ingrese solo números")
	@Required(message="Ingrese el número de telefono")
	@MaxSize(value=10,message="La cédula no puede tener más de 10 dígitos")
	@MinLength(value=10,message="La cédula no puede tener menos de 10 dígitos")
	 public String telefono;
	
	
	@Required(message="Ingrese la direccion")
	 public String direccion;
	
	@Required(message="Ingrese el password")
	 public  String password;
	
	@Unique(message="Esta email ya ha sido registrado anteriormente")	
	@Required(message="Ingrese el email")
	@Email	
	 public  String email;
	 
	 @ManyToOne
	 public Carrera carrera;
	 
	 @ManyToOne
	 public EstadoCiclo ciclo;
	 
	 @ManyToOne
	 public Rol rol;
	
	public Usuario(String cedula, String nombre, String apellido,
			String telefono, String direccion, String password, String email, Rol rol) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.direccion = direccion;
		this.password = password;
		this.email = email;		
		this.rol = rol;
	}

	public Usuario(String cedula, String nombre, String apellido,
			String telefono, String direccion, String password, String email,
			Carrera carrera,EstadoCiclo estado, Rol rol) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.direccion = direccion;
		this.password = password;
		this.email = email;
		this.carrera = carrera;
		this.ciclo=estado;
		this.rol = rol;
	}
	
	
	public String toString(){
		return "TIPO: "+rol.descripcion+" NOMBRES: "+apellido+" "+nombre+" CEDULA:"+cedula;
	}
	public boolean aprobadoDoc(){
		Documentacion d = null; 
		List<Documentacion> doc = Documentacion.find("usuario_id=?", this.id).fetch();
		 for (Documentacion docum: doc) {
		        if (docum.periodo.estado) {
					d=docum;
				} //Muestra cada uno de los nombres dentro de listaDeNombres
		 }
		if (d==null) {
			return false;
		}
		 else{			 
			 if (d.revisada){
					return true;
				}else{
					return false;
				}			 
		 }		
	}
	
	

}
