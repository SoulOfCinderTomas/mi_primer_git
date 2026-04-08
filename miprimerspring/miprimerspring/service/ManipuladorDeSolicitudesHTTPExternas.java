package co.edu.unbosque.miprimerspring.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;

import com.google.gson.Gson;
import co.edu.unbosque.miprimerspring.dto.JokeDTO;
import co.edu.unbosque.miprimerspring.dto.ListaJokeDTO;

public class ManipuladorDeSolicitudesHTTPExternas {

	private static final HttpClient HTTP_CLIENT = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2)
			.connectTimeout(Duration.ofSeconds(10)).build();

	public static String doGet(String url) {

		HttpRequest solicitud = HttpRequest.newBuilder().GET().uri(URI.create(url))
				.setHeader("User.Agent", "Java 11 HttpClient Bot").build();

		HttpResponse<String> respuesta = null;
		try {
			respuesta = HTTP_CLIENT.send(solicitud, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("status code -> " + respuesta.statusCode());
		return respuesta.body();
//		String uglyson = respuesta.body();
//		return prettyPrintUsingGson(uglyson);

	}

	public static JokeDTO doGetJokeDTO(String url) {

		HttpRequest solicitud = HttpRequest.newBuilder().GET().uri(URI.create(url))
				.setHeader("User.Agent", "Java 11 HttpClient Bot").build();

		HttpResponse<String> respuesta = null;
		try {
			respuesta = HTTP_CLIENT.send(solicitud, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("status code -> " + respuesta.statusCode());
		Gson gson = new Gson();
		return gson.fromJson(respuesta.body(), JokeDTO.class);
	}

	public static ArrayList<ListaJokeDTO> doGetListaJokeDTO(String url) {

		HttpRequest solicitud = HttpRequest.newBuilder().GET().uri(URI.create(url))
				.setHeader("User.Agent", "Java 11 HttpClient Bot").build();

		HttpResponse<String> respuesta = null;
		try {
			respuesta = HTTP_CLIENT.send(solicitud, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("status code -> " + respuesta.statusCode());
		Gson gson = new Gson();
		return gson.fromJson(respuesta.body(), ArrayList.class);
	}

	public static void main(String[] args) {
//		System.out.println(
//				doGet("https://v2.jokeapi.dev/joke/Programming,Dark?lang=es&blacklistFlags=sexist&idRange=1-6"));
//		
//		JokeDTO chiste = doGetJokeDTO(
//				"https://v2.jokeapi.dev/joke/Programming,Dark?lang=es&blacklistFlags=sexist&idRange=1-6");
//		System.out.println(chiste.toString());

		ArrayList<ListaJokeDTO> lista = doGetListaJokeDTO(
				"https://v2.jokeapi.dev/joke/Programming,Dark?lang=es&blacklistFlags=sexist&idRange=1-6&amount=4");
		System.out.println(lista.toString());

	}

}
