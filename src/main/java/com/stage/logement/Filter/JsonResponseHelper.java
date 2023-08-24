package com.stage.logement.Filter;

import java.io.IOException;

import org.springframework.http.HttpStatus;

import com.stage.logement.classe.HttpResponse;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

public class JsonResponseHelper {
	public static void writeJsonResponse(HttpServletResponse response // l'objet à renvoyer c a d la reponse 
			, HttpResponse httpResponse )// contient les détails de la reponse a renvoyer 
	     throws StreamWriteException, DatabindException, IOException {
		setResponseHeader(response); 
		writeResponse(response, httpResponse);
	

}
	private static void writeResponse (HttpServletResponse response, HttpResponse httpResponse)// utiliser  l'écriture réelle de l'objet HttpResponse dans le flux de sortie de la réponse HTTP
	  throws StreamWriteException, DatabindException, IOException {
		ServletOutputStream outputStream = response.getOutputStream();
		
		ObjectMapper objectMapper = new ObjectMapper(); // utilisé pour convertir l'objet HttpResponse en format JSON et l'écrire dans le flux de sortie de la réponse
		objectMapper.writeValue(outputStream, httpResponse);
		
		outputStream.flush();
	}
	// ObjectMapper est une classe de la bibliothèque Jackson qui permet de mapper des objets Java en JSON et vice versa.

	private static void setResponseHeader(HttpServletResponse response) // utiliser pour definir les entêtes approprié pour la réponse JSON
	{
		response.setContentType("APPLICATION_JSON_VALUE"); //configure le type de contenu de la réponse à "application/json" 
		response.setStatus(HttpStatus.FORBIDDEN.value()); // definie le statut de la réponse à 403 (FORBIDDEN)
	}
}