package controllers;

import models.TipoMateria;
import models.Usuario;
import play.mvc.*;
@CRUD.For(TipoMateria.class)
@With(Secure.class)
public class TipoMaterias extends CRUD {
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
