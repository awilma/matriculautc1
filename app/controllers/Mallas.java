package controllers;

import models.Malla;
import models.Usuario;
import play.mvc.*;

@CRUD.For(Malla.class)
@With(Secure.class)
public class Mallas extends CRUD {
	
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
	
	public static void verMalla(){
		Usuario user = Usuario.find("byEmail", Security.connected()).first();
		
		
		Malla malla = Malla.find("byCarrera",user.carrera).first();
		render(malla);
	}
}
