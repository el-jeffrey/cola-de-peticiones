package Modelo;

import java.util.HashMap;

public class ClsRequestDTO {
	
	public Integer posicion;
	public String titulo;
	public String metodo;
	public String url;
	public String body;
	public HashMap<String, String> headers;
	
	public Integer getPosicion() {
		return posicion;
	}
	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getMetodo() {
		return metodo;
	}
	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public HashMap<String, String> getHeaders() {
		return headers;
	}
	public void setHeaders(HashMap<String, String> headers) {
		this.headers = headers;
	}

}
