package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Rol extends Model {
	public String descripcion;
	public String toString(){
		return this.descripcion;
	}
    
}
