package repositories.proyecto;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;

import conexionDB.MongoDB;
import model.Proyecto;

public class ProyectoRepositoryImpl implements ProyectoRepository {

	@Override
	public void getAll() {
		MongoCollection<Document> doc = MongoDB.database.getCollection("Proyectos");
		ArrayList<Document> proyectos = new ArrayList<>();
		doc.find().into(proyectos);
		for (Document empleado : proyectos) {
			System.out.println(empleado);
		}
	}

	@Override
	public Boolean save(Proyecto p) {
		try {
			MongoCollection<Document> collection = MongoDB.database.getCollection("Proyectos");
			InsertOneResult result = collection.insertOne(new Document().append("_id", new ObjectId())
					.append("nombre", p.getNombre()).append("descripcion", p.getDescripcion())
					.append("fecha_inicio", p.getFecha_inicio()).append("fecha_fin", p.getFecha_fin()));
			System.out.println("Se le ha asignado la id: " + result.getInsertedId());
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
			System.out.println("Se ha borrado " + result.getDeletedCount() + " entradas.");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Boolean update(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean getById(String id) {
		try {
			MongoCollection<Document> doc = MongoDB.database.getCollection("Proyectos");
			System.out.println("Se ha encontrado un proyecto con esa id:" + doc.find(eq("_id", id)));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
