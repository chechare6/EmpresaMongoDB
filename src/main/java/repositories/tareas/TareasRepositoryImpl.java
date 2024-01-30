package repositories.tareas;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import conexionDB.MongoDB;

public class TareasRepositoryImpl implements TareasRepository{

	@Override
	public ArrayList<Document> getAll() {
		MongoCollection<Document> doc = MongoDB.database.getCollection("Tareas");
		ArrayList<Document> tareas = new ArrayList<>();
		doc.find().into(tareas);
		return tareas;
	}

}
