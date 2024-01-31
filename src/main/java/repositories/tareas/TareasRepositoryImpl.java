package repositories.tareas;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;

import conexionDB.MongoDB;

public class TareasRepositoryImpl implements TareasRepository{

	@Override
	public void getAll() {
		MongoCollection<Document> doc = MongoDB.database.getCollection("Tareas");
		ArrayList<Document> tareas = new ArrayList<>();
		doc.find().into(tareas);
	}
	
	@Override
	public Document getById(ObjectId id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean save(Document entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(ObjectId id) {
		// TODO Auto-generated method stub
		return null;
	}
}
