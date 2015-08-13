package models;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Carrera extends Model {
	
	public String nombre;
	public String coordinador;
	
	@ManyToOne
	public UnidadAcademica unidad;
	
	public String toString(){
		
		return this.nombre;
	}

}
