package AlgoChemin;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class API {
	
	public static String formateAdresse(String adresse) {
		String format = "";
		for(int i = 0; i<adresse.length();i++ ) {
			if(adresse.charAt(i) ==  ' ') 
			{
				format = format + "+";
			}
			else  {
				format = format + adresse.charAt(i);
			}
		}
		return format;
	}
	
	public static Position getGPS(String sadresse) {
		  // Remplacez "Your Address" par l'adresse que vous souhaitez géocoder
        String address = sadresse;

        // Construisez l'URL de requête pour Nominatim
        String nominatimUrl = "https://nominatim.openstreetmap.org/search?format=json&q=" + address;

        // Configurez le client HTTP
        HttpClient httpClient = HttpClient.newHttpClient();

        // Construisez la requête HTTP GET
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(nominatimUrl))
                .build();

        String latitude = null;
        String longitude = null;
        String jsonResponse = "";
        String reponse = "";
        
        try {
            // Envoyez la requête et obtenez la réponse
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            // Traitez la réponse JSON pour obtenir les coordonnées
            // Notez que c'est une simplification, vous devrez analyser correctement la réponse JSON
            jsonResponse = response.body();
            System.out.println("Réponse JSON : " + jsonResponse);
            for(int i = 1; i<jsonResponse.length()-1;i++) {
            	reponse = reponse + jsonResponse.charAt(i);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        
        //String jsonString = "{\"place_id\":123,\"lat\":\"48.8566\",\"lon\":\"2.3522\",\"display_name\":\"Paris, France\"}";

        try {
            // Utilisez Jackson pour mapper la chaîne JSON vers un objet JsonNode
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(reponse);

            // Extrait les coordonnées du nœud JSON
            latitude = jsonNode.get("lat").asText();
            longitude = jsonNode.get("lon").asText();

            System.out.println("Latitude : " + latitude);
            System.out.println("Longitude : " + longitude);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return new Position(latitude, longitude);
	}
	
	public static void main(String[] args) {
		System.out.println("Test formatage adresse");
		System.out.println("Adresse Avant formatage");
		String adresse = "rue de la Maladrerie";
		System.out.println(adresse);
		adresse = API.formateAdresse(adresse);
		System.out.println("Adresse Après Formatage");
		System.out.println(adresse);
	}
}
