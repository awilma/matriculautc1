package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

@Entity
public class Malla extends Model {
	@ManyToOne
    public Carrera carrera;
    @OneToMany( cascade = CascadeType.ALL, mappedBy="malla")
    public List<Ciclo> ciclos;
    
	public Malla(Carrera carrera, List<Ciclo> ciclos) {
		super();
		this.carrera = carrera;
		this.ciclos = ciclos;
	}
    public String toString(){
    	
    	try{
    		return "Malla de: "+this.carrera.nombre;
		}catch(Exception ex){
			return "";
		}
    }
	
    
}
