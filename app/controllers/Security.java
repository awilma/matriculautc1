package controllers;

import models.Usuario;

import org.junit.Before;

public class Security extends Secure.Security{

	public static boolean authenticate(String username, String password) {
		   
		   Usuario user = Usuario.find("byEmail", username).first();

		    if( user != null){ 
		  	  
		  			  
		  			  if( user.password.equals(password)){	
			    		  	return true;
		  			  }else{
		  				  flash.put("clave","ERROR: Contraseña Incorrecta.");
		  				  
		  				  return false;
		  			  }
		  			  

		    }else{	
		  	  flash.put("usuario","ERROR: Este email no está registrado");
		  	 
		  	  return false;
		    }

		}


}
