package controllers;

import play.mvc.Before;
import play.mvc.With;
import models.Rol;
import models.Usuario;


@CRUD.For(Rol.class)
@With(Secure.class)
public class Roles extends CRUD{
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
