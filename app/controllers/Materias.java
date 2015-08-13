package controllers;

import models.Materia;
import models.Usuario;
import play.mvc.*;

@CRUD.For(Materia.class)
@With(Secure.class)
public class Materias extends CRUD {	
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
	
}
