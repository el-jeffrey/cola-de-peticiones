package Utilidades;

import Modelo.ClsRequestDTO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonStructure;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class Utilidades {

	public static ClsRequestDTO onProcesar(ClsRequestDTO objeto) {
		System.out.println("Ejecutando a [onProcesar]... ");

		System.out.println("Método: " + objeto.getMetodo());

//		String urlstr = "http://localhost:8002/solicitud/listar"; http://localhost:8001/iceberg-microservice-estudiante/estudiante; https://ventas-dale.azurewebsites.net/api/productos
		String urlstr = objeto.getUrl();

		if (objeto.getMetodo().equals("GET")) {
			try {
				URL url = new URL(urlstr);
				HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
				conexion.setRequestMethod(objeto.getMetodo());

				conexion.setUseCaches(false);
				conexion.setRequestProperty("Accept-Charset", "UTF-8");
				conexion.setDoOutput(true);
				conexion.setDoInput(true);

				objeto.setStatus(conexion.getResponseCode());
				System.out.println("STATUS CODE: " + conexion.getResponseCode());

				if (conexion.getResponseCode() >= 300) {
					String resp = "Did not receive successful HTTP response: status code = "
							+ conexion.getResponseCode(); // + ", status message = [" + conexion.getResponseMessage() +
															// "]";

					objeto.setResultado(resp);
					throw new IOException(resp);
				}

				InputStream inputStream = conexion.getInputStream();
				JsonReader jReader = Json.createReader(new InputStreamReader(inputStream, "UTF-8"));

				JsonStructure json = jReader.read();

				objeto.setResultado(new GsonBuilder().setPrettyPrinting().create()
						.toJson(JsonParser.parseString((json.toString()))));

				System.out.println("Resultado: \n" + objeto.getResultado());

				return objeto;
			} catch (IOException e) {
				System.out.println("ERROR: " + e.getMessage());

				if (objeto.getStatus() == null) {
					objeto.setResultado(e.getMessage());
				}

				return objeto;
			}
		} else {
			try {
				URL url = new URL(urlstr);
				HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
				conexion.setRequestMethod(objeto.getMetodo());
				conexion.setUseCaches(false);
				conexion.setRequestProperty("Content-Type", "application/json; charset=utf8");
				conexion.setRequestProperty("Accept", "application/json");
				conexion.setRequestProperty("Content-Type", "application/json; charset=utf8");
				conexion.setRequestProperty("Connection", "keep-alive");
				conexion.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
				conexion.setDoInput(true);
				conexion.setDoOutput(true);

				System.out.println("JSON: " + objeto.getBody());

				if (objeto.getHeaders() != null && objeto.getHeaders().size() > 0) {
					objeto.getHeaders().forEach((k, v) -> {
						System.out.println("Key: " + k + ": Value: " + v);

						conexion.setRequestProperty(k, v);
					});
				}

				try (OutputStream os = conexion.getOutputStream()) {
					byte[] input = objeto.getBody().getBytes("UTF-8");
					os.write(input, 0, input.length);
				}

				objeto.setStatus(conexion.getResponseCode());
				System.out.println("STATUS CODE: " + conexion.getResponseCode());

				if (!(conexion.getResponseCode() == HttpURLConnection.HTTP_OK
						|| conexion.getResponseCode() == HttpURLConnection.HTTP_CREATED)) {
					String resp = "Did not receive successful HTTP response: status code = "
							+ conexion.getResponseCode(); // + ", status message = [" + conexion.getResponseMessage() +
															// "]";

					objeto.setResultado(resp);
					throw new IOException(resp);
				}

				InputStream inputStream = conexion.getInputStream();
				JsonReader jReader = Json.createReader(new InputStreamReader(inputStream, "UTF-8"));
				JsonStructure json = jReader.read();

				objeto.setResultado(new GsonBuilder().setPrettyPrinting().create()
						.toJson(JsonParser.parseString((json.toString()))));

				System.out.println("Resultado: " + objeto.getResultado());

				return objeto;
			} catch (IOException e) {
				System.out.println("ERROR: " + e.getMessage());
				return objeto;
			}
		}
	}

	public static void onGenerarDocumentoPDF(String cadena) throws Exception {
		System.out.println("Ejecutando [onGenerarDocumentoPDF]... ");

		Document document = new Document(PageSize.A4, 20, 20, 20, 20);
		PdfWriter.getInstance(document, new FileOutputStream("Documento.pdf"));

		document.open();

		document.addTitle("Resultado");
		document.addKeywords("Java, PDF, iText");
		document.addAuthor("EEspinosa");
		document.addCreator("EEspinosa");

		document.add(Chunk.NEWLINE);

		document.add(new Paragraph(cadena));

		document.add(Chunk.NEWLINE);

		document.close();
	}

	public static void onGenerarDocumentoTXT(String cadena) throws Exception {
		System.out.println("Ejecutando [onGenerarDocumentoTXT]... ");

		try {
			String ruta = "Documento.txt";
			File file = new File(ruta);
			// Si el archivo no existe es creado
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(cadena);
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void onProcesar2() {
		System.out.println("Ejecutando [onProcesar2]... ");

//		CloseableHttpClient httpClient = HttpClients.createDefault();
//		HttpPost post = new HttpPost(objeto.getUrl());
//		post.setHeader("Content-Type", "application/json; charset=utf8");
//		post.setHeader("Accept", "application/json");
//		post.setHeader("Content-Type", "application/json; charset=utf8");
//		
//		
//		post.setHeader("token", "token");
//		post.setHeader("X-Transaction-K", "trans");
//		post.setHeader("X-Device-ID", "device");
//
//		post.setEntity(new StringEntity(objeto.getBody()));
//		
//		CloseableHttpResponse response = httpClient.execute(post);
//		System.out.println("--------------> Respuesta: " + response.getStatusLine().getStatusCode());
//		httpClient.close();
	}

}
