package conexionDB;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.io.FileInputStream;
import java.util.Properties;

import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 * Clase que gestiona la conexión y operaciones con la base de datos MongoDB.
 */
public class MongoDB {
	private final String FILE_PROPS = "app.config";
	private static MongoClient db = null;
	public static MongoDatabase database = null;

	/**
	 * Constructor privado para evitar instancias externas. Carga la configuración
	 * desde un archivo properties y establece la conexión con MongoDB.
	 */
	private MongoDB() {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(FILE_PROPS));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String uri = props.getProperty("protocol") + "://" + props.getProperty("user") + ":" + props.getProperty("pass") + "@" + props.getProperty("host");
		db = MongoClients.create(uri);
	}

	/**
	 * Obtiene una instancia del cliente MongoDB.
	 *
	 * @return Cliente MongoDB.
	 */
	public static MongoClient getClient() {
		if(db == null) {
			new MongoDB();
		}
		return db;
	}

	/**
	 * Inicializa la base de datos con el proveedor de códecs PojoCodecProvider.
	 * Utiliza la configuración proporcionada en el archivo properties.
	 */
	public static void initDatabase() {
		CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
		
		MongoClient mongoClient = MongoDB.getClient();
        database = mongoClient.getDatabase("gestionDB").withCodecRegistry(pojoCodecRegistry);
	}
	
}
