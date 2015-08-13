package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class UnidadAcademica extends Model {
	
	public String nombre;
	public String siglas;
	public String director;
	
	
	
	public String toString(){
		return nombre;
	}
    
}
