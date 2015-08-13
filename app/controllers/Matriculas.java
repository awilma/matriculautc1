package controllers;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import models.Ciclo;
import models.Documentacion;
import models.Matricula;
import models.Periodo;
import models.Usuario;
import play.db.jpa.Blob;
import play.libs.MimeTypes;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;


@With(Secure.class)
public class Matriculas extends Controller {

	@Before
	public static void mostrarUsuario(){
		try{
			Usuario user = Usuario.find("byEmail", Security.connected()).first();
			//obteniendo datos del usuario que ha iniciado sesion
			if(Security.isConnected()) {
		        renderArgs.put("conectado", user); 
		    }
			}catch(Exception ex){
				
			}
	}

    public static void index() {
    	Usuario usuario = Usuario.find("byEmail", Security.connected()).first();
    	Periodo periodoActual=Periodo.find("byEstado", true).first(); 
    	
    	Documentacion documentacion=null;
    	String fInicial=null;
    	String fFinal=null;
    	try{
    		documentacion=Documentacion.find("periodo_id=? and usuario_id=?",periodoActual.id,usuario.id).first();
    		DateFormat df3 = DateFormat.getDateInstance(DateFormat.LONG);
    		fInicial = df3.format(periodoActual.fechaInicio);
    		fFinal=df3.format(periodoActual.fechaFin);
    	}catch(Exception ex){
    		
    	}
 
	
    	
        render(periodoActual,documentacion,fFinal,fInicial);
    }
    
    
    public static void indexTutor(){
    	List<Ciclo> ciclos=Ciclo.findAll();
    	Periodo periodoActual=Periodo.find("byEstado", true).first(); 
 
		DateFormat df3 = DateFormat.getDateInstance(DateFormat.LONG);
		String fInicial = df3.format(periodoActual.fechaInicio);
		String fFinal=df3.format(periodoActual.fechaFin);
	
    	
    	render(periodoActual,ciclos,fInicial,fFinal);
    }
    
    
    public static void listadoDocumentos(long id_ciclo, long id_periodo){
    	
    	Periodo periodoActual=Periodo.findById(id_periodo);
    	Ciclo ciclo=Ciclo.findById(id_ciclo);
    	Usuario user = Usuario.find("byEmail", Security.connected()).first();
    	
    	
    	List<Documentacion> documentosSubidos=null;
    	
    	
    	try{
    	documentosSubidos=Documentacion.find("periodo_id=? and ciclo=? and revisada=? and carrera_id=? order by usuario.apellido ",periodoActual.id,ciclo,false,user.carrera.id).fetch();
    	}catch(Exception ex){
    		
    	}
    	
    	//List<Documentacion> documentosSubidos=Documentacion.findAll();
    	
    	render(documentosSubidos);
    	
    }
    
    public static void verDocumentacion(long id_documentacion){   
    	
    	Documentacion documentacionSubida=Documentacion.findById(id_documentacion);
    	render(documentacionSubida);
    }
    
    public static void subirDocumentacion(long id_periodo){
    	
    	Usuario user = Usuario.find("byEmail", Security.connected()).first();
    	Periodo periodoActual=Periodo.findById(id_periodo);    	
    	List<Ciclo> ciclos=Ciclo.findAll();
    	
    	Ciclo cicloSiguiente=null;
    	
    	for(int i=0;i<ciclos.size();i++){
    		Ciclo c=ciclos.get(i);
    		
    		if(c.estado==user.ciclo){
    			cicloSiguiente=ciclos.get(i+1);
    		}
    	}
    	
    	render(cicloSiguiente,periodoActual);
    }

    public static void guardarDocumentacion(long id_periodo,long id_ciclo,File solicitud, File documentos,File foto, File record) throws FileNotFoundException{
    	
    	Usuario usuario = Usuario.find("byEmail", Security.connected()).first();
    	Ciclo ciclo=Ciclo.findById(id_ciclo);
    	Periodo periodo=Periodo.findById(id_periodo);
    	Documentacion documentos1=new Documentacion();
    	
    	documentos1.solicitud=new Blob();
    	documentos1.solicitud.set(new FileInputStream(solicitud), MimeTypes.getContentType(solicitud.getName()));

    	
    	documentos1.documentosPersonales=new Blob();
    	documentos1.documentosPersonales.set(new FileInputStream(documentos), MimeTypes.getContentType(documentos.getName()));
    	
    	documentos1.foto=new Blob();
    	documentos1.foto.set(new FileInputStream(foto), MimeTypes.getContentType(foto.getName()));
    	
    	
    	documentos1.record=new Blob();
    	documentos1.record.set(new FileInputStream(record), MimeTypes.getContentType(record.getName()));
    	
    	documentos1.usuario=usuario;
    	
    	documentos1.ciclo=ciclo;
    	documentos1.periodo=periodo;
    	
    	documentos1.save();
    	
    	flash.put("confirmacionSubida","Documentacion subida exitosamente. Debe esperar a que su tutor la revise");
    	
    	index();

    }
    
    public static void verSolicitud(Long id){
    	final Documentacion documentos=Documentacion.findById(id);
    	notFoundIfNull(documentos);
    	response.setContentTypeIfNotSet(documentos.solicitud.type());
    	renderBinary(documentos.solicitud.get());
    	
    }
    
    
    
    public static void verDocumentosPersonales(Long id){
    	final Documentacion documentos=Documentacion.findById(id);
    	notFoundIfNull(documentos);
    	response.setContentTypeIfNotSet(documentos.documentosPersonales.type());
    	renderBinary(documentos.documentosPersonales.get());
    	
    }
    
    
    
    public static void verFoto(Long id){
    	final Documentacion documentos=Documentacion.findById(id);
    	notFoundIfNull(documentos);
    	response.setContentTypeIfNotSet(documentos.foto.type());
    	renderBinary(documentos.foto.get());
    	
    }
    
    public static void verRecord(Long id){
    	final Documentacion documentos=Documentacion.findById(id);
    	notFoundIfNull(documentos);
    	response.setContentTypeIfNotSet(documentos.record.type());
    	renderBinary(documentos.record.get());
    	
    }
    

    public static void informeMatricula(){
    	Usuario usuario = Usuario.find("byEmail", Security.connected()).first();
    	Matricula matricula=Matricula.find("byEstudiante", usuario).first();
    	render(matricula);
    }

    
    
    //Vista para validar
    public static void validarDocumentacion(long id_documentos){    	
    	Documentacion documentosValidar=Documentacion.findById(id_documentos);
    	render(documentosValidar);
    }
    
    
    
    //Proceso para validar
    public static void validacionDoc(long id_documentacion,boolean estado){
    	
    	
    	Documentacion documentacionValidada=Documentacion.findById(id_documentacion); 
    	Usuario usuario = Usuario.find("byEmail", Security.connected()).first();
    	
    	usuario.ciclo=documentacionValidada.ciclo.estado;
    	System.out.println("De "+usuario.ciclo+" a "+documentacionValidada.ciclo.estado);
    	usuario.save();
    	System.out.println(estado);
    	
    	if(estado){
    		documentacionValidada.revisada=estado; 
    		documentacionValidada.save();
    		flash.put("confirmacionValidar", "Operacion Exitosa..!");
    		Matricula matriculaLista=new Matricula(documentacionValidada.usuario, documentacionValidada.ciclo,documentacionValidada,documentacionValidada.periodo);
    		matriculaLista.save();
    	}
    	
    	
    	listadoDocumentos(documentacionValidada.ciclo.id, documentacionValidada.periodo.id);

    }
    
    

}
