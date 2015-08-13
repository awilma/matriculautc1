package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

@Entity
public class Ciclo extends Model {
	 
    public String nombre;
    @ManyToOne
    public EstadoCiclo estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="ciclo")
    public List<Materia> materias;
    @ManyToOne
	public Malla malla;    
    public String toString(){
    	return this.nombre;
    }
}
