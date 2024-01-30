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

public class QuickStart {
	public static String pretty(String json) {
		JsonElement je = JsonParser.parseString(json);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(je);
	}
	
	public static void main(String[] args) {
		MongoClient mongoClient = MongoDB.getClient();
		MongoDatabase database = mongoClient.getDatabase("gestionDB");
		System.out.println("Conectado");			
		MongoCollection<Document> collection = database.getCollection("Proyectos");
		Document doc = collection.find(eq("nombre", "Proyecto 1")).first();
		if(doc != null) {
			System.out.println(pretty(doc.toJson()));
		}
		else
			System.out.println("No se ha encontrado la BBDD");
	}
}
