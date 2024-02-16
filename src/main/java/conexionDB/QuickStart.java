package conexionDB;

import static com.mongodb.client.model.Filters.*;
import org.bson.Document;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Clase de ejemplo (QuickStart) para realizar operaciones básicas con MongoDB.
 */
public class QuickStart {

	/**
	 * Formatea una cadena JSON para imprimirlo de manera legible.
	 *
	 * @param json Cadena JSON a formatear.
	 * @return Cadena JSON formateada.
	 */
	public static String pretty(String json) {
		JsonElement je = JsonParser.parseString(json);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(je);
	}
	
	public static void main(String[] args) {
		// Obtenemos la instancia del cliente MongoDB
		MongoClient mongoClient = MongoDB.getClient();

		// Nos conectamos a la base de datos "gestionDB"
		MongoDatabase database = mongoClient.getDatabase("gestionDB");
		System.out.println("Conectado");

		// Obtenemos la colección "Proyectos"
		MongoCollection<Document> collection = database.getCollection("Proyectos");

		// Buscamos un documento con nombre "Proyecto 1"
		Document doc = collection.find(eq("nombre", "Proyecto 1")).first();
		if(doc != null) {
			// Imprimimos el documento de manera legible
			System.out.println(pretty(doc.toJson()));
		}
		else
			System.out.println("No se ha encontrado la BBDD");
	}
}
