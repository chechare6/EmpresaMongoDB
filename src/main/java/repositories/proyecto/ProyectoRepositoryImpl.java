package repositories.proyecto;

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
import model.Proyecto;

public class ProyectoRepositoryImpl implements ProyectoRepository {

	@Override
	public void getAll() {
		MongoCollection<Document> doc = MongoDB.database.getCollection("Proyectos");
		ArrayList<Document> proyectos = new ArrayList<>();
		doc.find().into(proyectos);
	}

	@Override
	public Document getById(ObjectId id) {
		MongoCollection<Document> doc = MongoDB.database.getCollection("Proyectos");
		Document proyecto = doc.find(eq("_id", id)).first();
		return proyecto;
	}

	@Override
	public Boolean save(Proyecto p) {
		try {
			MongoCollection<Document> collection = MongoDB.database.getCollection("Proyectos");
			InsertOneResult result = collection.insertOne(new Document().append("_id", new ObjectId())
					.append("nombre", p.getNombre()).append("descripcion", p.getDescripcion())
					.append("fecha_inicio", p.getFecha_inicio()).append("fecha_fin", p.getFecha_fin()));
			IO.println("Se le ha asignado la id: " + result.getInsertedId());
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public Boolean delete(String nombre) {
		try {
			MongoCollection<Document> collection = MongoDB.database.getCollection("Proyectos");
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

	@Override
	public void addEmpleado() {
		// TODO: MÉTODO QUE AÑADIRÁ UN EMPLEADO A UN PROYECTO
		
	}
}
