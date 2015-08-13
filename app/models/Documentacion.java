package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

@Entity
public class Documentacion extends Model {
	
	
	public Blob solicitud;
	public Blob documentosPersonales;
	public Blob foto;
	public Blob record;	
	
	public boolean revisada;

	@ManyToOne
	public Usuario usuario;
	
	@ManyToOne
	public Periodo periodo;

	@ManyToOne
	public Ciclo ciclo;

	
	
}
