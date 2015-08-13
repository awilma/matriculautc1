package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

@Entity
public class Eje extends Model {
    public String nombre;
	public Eje(String nombre) {
		super();
		this.nombre = nombre;
	}
	public String toString(){
		return this.nombre;
	}
}
