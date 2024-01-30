package conexionDB;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import model.Empleado;

public class QuickStartPojo {

	public static void main(String[] args) {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
		
		MongoClient mongoClient = MongoDB.getClient();
        MongoDatabase database = mongoClient.getDatabase("gestionDB").withCodecRegistry(pojoCodecRegistry);
        MongoCollection<Empleado> collection = database.getCollection("Empleados", Empleado.class);
//        Empleado empleado = collection.find(eq("nombre", "César")).first();
        ArrayList<Empleado> empleados = new ArrayList<>(); 
        collection.find().into(empleados);
        for (Empleado empleado : empleados) {
			System.out.println(empleado);
		}
        System.out.println("----------");
        MongoCollection<Document> collectionDoc = database.getCollection("Empleados");
        ArrayList<Document> docs = new ArrayList<>();
        collectionDoc.find().into(docs);
        System.out.println(docs);
        System.out.println("----------");
        for (Document document : docs) {
			System.out.println(document);
		}        
	}
	
	public static String pretty(String json) {
		JsonElement je = JsonParser.parseString(json);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(je);
	}

}
