package repositories.proyecto;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import conexionDB.MongoDB;

public class ProyectoRepositoryImpl implements ProyectoRepository {

	@Override
	public void getAll() {
		MongoCollection<Document> doc = MongoDB.database.getCollection("Proyectos");
		ArrayList<Document> proyectos = new ArrayList<>();
		doc.find().into(proyectos);
	}
	
	@Override
	public void getById() {
		// TODO Auto-generated method stub
		
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
	public Boolean update(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}
}
