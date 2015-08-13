package models;

import java.text.DateFormat;
import java.util.Date;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Periodo extends Model {
	
	public Date fechaInicio;
	public Date fechaFin;
	public boolean estado;
	
	
	
	public String toString(){
		
		Date now = new Date();

		DateFormat df3 = DateFormat.getDateInstance(DateFormat.LONG);
		String fInicial = df3.format(this.fechaInicio);
		String fFinal=df3.format(this.fechaFin);
	
			
			
		return String.valueOf("Desde: "+fInicial+" - "+" Hasta: "+fFinal);
	}

	
	
    
}
