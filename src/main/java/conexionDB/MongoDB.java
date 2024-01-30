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

public class MongoDB {
	private final String FILE_PROPS = "app.config";
	private static MongoClient db = null;
	public static MongoDatabase database = null;
	
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
	
	public static MongoClient getClient() {
		if(db == null) {
			new MongoDB();
		}
		return db;
	}
	
	public static void initDatabase() {
		CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
		
		MongoClient mongoClient = MongoDB.getClient();
        database = mongoClient.getDatabase("gestionDB").withCodecRegistry(pojoCodecRegistry);
	}
	
}
