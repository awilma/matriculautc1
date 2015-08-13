package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

@Entity
public class TipoMateria extends Model {
    public String descripcion;
    public String abreviacion;
	public TipoMateria(String descripcion, String abreviacion) {
		super();
		this.descripcion = descripcion;
		this.abreviacion = abreviacion;
	}
	public String toString(){
		return this.descripcion;
	}
}

