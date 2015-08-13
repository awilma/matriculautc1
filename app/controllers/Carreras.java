package controllers;

import models.Carrera;
import models.Usuario;
import play.mvc.Before;
import play.mvc.With;


@CRUD.For(Carrera.class)
@With(Secure.class)
public class Carreras extends CRUD{
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
