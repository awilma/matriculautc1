package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Matricula extends Model {
	@ManyToOne
	public Usuario estudiante;
	@ManyToOne
	public Ciclo ciclo;
	
	@ManyToOne
	public Documentacion documentos;
	
	@ManyToOne
	public Periodo periodo ;

	public Matricula(Usuario estudiante, Ciclo ciclo, Documentacion documentos,
			Periodo periodo) {
		super();
		this.estudiante = estudiante;
		this.ciclo = ciclo;
		this.documentos = documentos;
		this.periodo = periodo;
	}
	
	
}
