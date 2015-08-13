package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

@Entity
public class EstadoCiclo extends Model {
    public String descripcion;

	public EstadoCiclo(String descripcion) {
		super();
		this.descripcion = descripcion;
	}
    
	public String toString(){
		return this.descripcion;
	}
}
