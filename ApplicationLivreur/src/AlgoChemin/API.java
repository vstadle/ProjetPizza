package AlgoChemin;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class API {

	public static void main(String[] args) {
        // Remplacez "Your Address" par l'adresse que vous souhaitez géocoder
        String address = "7+rue+de+la+Maladrerie,+91150+Morigny+Champigny,+France";

        // Construisez l'URL de requête pour Nominatim
        String nominatimUrl = "https://nominatim.openstreetmap.org/search?format=json&q=" + address;

        // Configurez le client HTTP
        HttpClient httpClient = HttpClient.newHttpClient();

        // Construisez la requête HTTP GET
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(nominatimUrl))
                .build();

        try {
            // Envoyez la requête et obtenez la réponse
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            // Traitez la réponse JSON pour obtenir les coordonnées
            // Notez que c'est une simplification, vous devrez analyser correctement la réponse JSON
            String jsonResponse = response.body();
            System.out.println("Réponse JSON : " + jsonResponse);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        
        String jsonString = "{\"place_id\":123,\"lat\":\"48.8566\",\"lon\":\"2.3522\",\"display_name\":\"Paris, France\"}";

        try {
            // Utilisez Jackson pour mapper la chaîne JSON vers un objet JsonNode
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonString);

            // Extrait les coordonnées du nœud JSON
            String latitude = jsonNode.get("lat").asText();
            String longitude = jsonNode.get("lon").asText();

            System.out.println("Latitude : " + latitude);
            System.out.println("Longitude : " + longitude);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
}
