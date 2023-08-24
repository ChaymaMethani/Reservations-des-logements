package com.stage.logement.classe;

import java.util.Date;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat; // pour la format de date et heure 

public class HttpResponse {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm") // sérialisé timeStamp sous format de chaine avec le modéle de formatage 
	private Date timeStamp; // represente le temps de generation de reponsehttp 

	private HttpStatus httpStatus; // represente un statut correspondant à un code specifique 

	private int httpStatusCode; // le code de reponse(404/200...)

	private String message; // message explicatif supplémentaire pour la reponse 

	public HttpResponse() { // initialise timeStamp 
	}

	public HttpResponse(HttpStatus httpStatus, int httpStatusCode, String message) {
		this.timeStamp = new Date();
		this.httpStatus = httpStatus;
		this.httpStatusCode = httpStatusCode;
		this.message = message;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

