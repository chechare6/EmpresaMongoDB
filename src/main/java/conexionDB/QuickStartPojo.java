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

/**
 * Clase de ejemplo (QuickStartPojo) para realizar operaciones con objetos POJO en MongoDB.
 */
public class QuickStartPojo {

	public static void main(String[] args) {
        // Configuramos el proveedor y registro de códecs para objetos POJO
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        // Obtenemos la instancia del cliente MongoDB y nos conectamos a la base de datos "gestionDB"
        MongoClient mongoClient = MongoDB.getClient();
        MongoDatabase database = mongoClient.getDatabase("gestionDB").withCodecRegistry(pojoCodecRegistry);

        // Obtenemos la colección "Empleados" con códecs para objetos POJO
        MongoCollection<Empleado> collection = database.getCollection("Empleados", Empleado.class);
//        Empleado empleado = collection.find(eq("nombre", "César")).first();

        // Recogemos y mostramos todos los empleados
        ArrayList<Empleado> empleados = new ArrayList<>(); 
        collection.find().into(empleados);
        for (Empleado empleado : empleados) {
			System.out.println(empleado);
		}
        System.out.println("----------");

        // Obtenemos y mostramos todos los documentos en la colección "Empleados" sin códecs de POJO
        MongoCollection<Document> collectionDoc = database.getCollection("Empleados");
        ArrayList<Document> docs = new ArrayList<>();
        collectionDoc.find().into(docs);
        System.out.println(docs);
        System.out.println("----------");

        // Mostramos cada documento de manera legible
        for (Document document : docs) {
			System.out.println(document);
		}        
	}

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

}
