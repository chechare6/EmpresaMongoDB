package repositories.tareas;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;

import IO.IO;
import conexionDB.MongoDB;
import model.Tarea;

public class TareasRepositoryImpl implements TareasRepository {

	@Override
	public void getAll() {
		MongoCollection<Document> doc = MongoDB.database.getCollection("Tareas");
		ArrayList<Document> tareas = new ArrayList<>();
		doc.find().into(tareas);
		for (Document tarea : tareas) {
			IO.println(tarea);
		}
	}

	@Override
	public Document getById(ObjectId id) {
		MongoCollection<Document> doc = MongoDB.database.getCollection("Tareas");
		Document tarea = doc.find(eq("_id", id)).first();
		return tarea;
	}

	@Override
	public Boolean save(Tarea t) {
		try {
			MongoCollection<Document> collection = MongoDB.database.getCollection("Tareas");
			InsertOneResult result = collection.insertOne(new Document()
					.append("_id", new ObjectId())
					.append("nombre", t.getNombre())
					.append("puesto", t.getDescripcion())
					.append("estado", t.getEstado())
					.append("fecha_vencimiento", t.getFecha_vencimiento())
					.append("id_proyect", t.getId_proyecto()));
			IO.println("Se le ha asignado la id: " + result.getInsertedId());
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public Boolean delete(String nombre) {
		try {
			MongoCollection<Document> collection = MongoDB.database.getCollection("Tareas");
			Bson query = eq("nombre", nombre);
			DeleteResult result = collection.deleteOne(query);
			IO.println("Se ha borrado " + result.getDeletedCount() + " entrada/s.");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean update(ObjectId id) {
		// TODO Auto-generated method stub
		return null;
	}
}
