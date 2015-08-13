package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

@Entity
public class Materia extends Model {
    public String nombre;
    public int creditos;
    public String serie;
    @ManyToOne
    public Materia prerrequisito;
    @ManyToOne
    public Ciclo ciclo;
	@ManyToOne
	public TipoMateria tipo;
	@ManyToOne
	public Eje eje;
	public int estado;
	public Materia(String nombre, int creditos, String serie,
			Materia prerrequisito, TipoMateria tipo, Eje eje, int estado) {
		super();
		this.nombre = nombre;
		this.creditos = creditos;
		this.serie = serie;
		this.prerrequisito = prerrequisito;
		this.tipo = tipo;
		this.eje = eje;
		this.estado = estado;
	}
	
	public String toString(){
		return this.nombre;
	}
	
	
	
	
    
}
