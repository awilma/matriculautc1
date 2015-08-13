package controllers;

import java.util.List;

import models.Carrera;
import models.Ciclo;
import models.EstadoCiclo;
import models.Rol;
import models.Usuario;
import play.mvc.Before;
import play.mvc.Controller;

public class Usuarios extends Controller {
	
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

    public static void registro() {
    	List<Carrera> carreras=Carrera.findAll();
    	List<EstadoCiclo>	ciclos=EstadoCiclo.findAll();
        render(carreras,ciclos);
    }
    

    
    public static void guardar(String nombre, String apellido, String cedula, String direccion, String telefono, String email, String password,long id_carrera,long id_ciclo, String confirmacionPassword) {
        
    	
    	flash.put("nombre",nombre);
    	flash.put("apellido",apellido);
    	flash.put("cedula",cedula);
    	flash.put("telefono",telefono);
    	flash.put("direccion",direccion);
    	
    	renderArgs.put("id",id_carrera); 
    	
    	if(password.equals(confirmacionPassword)){
    		
    		Usuario usuario=Usuario.find("byEmail", email).first();	
    		
    		Rol rol=Rol.find("byDescripcion", "Estudiante").first();
    		
    		Carrera carrera=Carrera.findById(id_carrera);
    		EstadoCiclo est=EstadoCiclo.findById(id_ciclo);
    		if(usuario==null){
    			
    			usuario=new Usuario(cedula,nombre, apellido,telefono,direccion,password, email,carrera,est,rol);
    			usuario.save();
    			confirmacionRegistro();
;    		}else{
    			flash.put("repetido","El email "+email+" ya está en uso. :(");
    			registro();
    		}
    	}else{
    		flash.put("email",email);
    		flash.put("errorPassword",":(");
    		redirect("/usuarios/registro");
    	}
    	
    	
    }
    
    
    public static void confirmacionRegistro(){
    	render();
    }
    
    

}
