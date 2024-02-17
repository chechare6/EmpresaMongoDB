package repositories.tareas;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;

import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;

import IO.IO;
import conexionDB.MongoDB;
import model.Tarea;

/**
 * Implementación de la interfaz TareasRepository para realizar operaciones relacionadas con las tareas en MongoDB.
 */
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
		if (t.getId_proyecto() != null) {
			try {
				MongoCollection<Document> collection = MongoDB.database.getCollection("Tareas");
				InsertOneResult result = collection.insertOne(new Document().append("_id", new ObjectId())
						.append("nombre", t.getNombre()).append("descripcion", t.getDescripcion())
						.append("estado", t.getEstado()).append("fecha_vencimiento", t.getFecha_vencimiento())
						.append("id_proyecto", t.getId_proyecto()));
				IO.println("Se le ha asignado la id: " + result.getInsertedId());
				return true;
			} catch (Exception ex) {
				return false;
			}
		} else {
			try {
				MongoCollection<Document> collection = MongoDB.database.getCollection("Tareas");
				InsertOneResult result = collection.insertOne(new Document().append("_id", new ObjectId())
						.append("nombre", t.getNombre()).append("descripcion", t.getDescripcion())
						.append("estado", t.getEstado()).append("fecha_vencimiento", t.getFecha_vencimiento()));
				IO.println("Se le ha asignado la id: " + result.getInsertedId());
				return true;
			} catch (Exception ex) {
				return false;
			}
		}
	}

	@Override
	public Boolean delete(ObjectId id) {
		try {
			MongoCollection<Document> collection = MongoDB.database.getCollection("Tareas");
			Bson query = eq("_id", id);
			DeleteResult result = collection.deleteOne(query);
			IO.println("Se ha borrado " + result.getDeletedCount() + " entrada/s.");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean update(Tarea t) {
		Document tarea = getById(t.getId());
		if (tarea != null) {
			try {
				if (t.getId_proyecto() != null) {
					Bson updates = Updates.combine(Updates.set("nombre", t.getNombre()),
							Updates.set("descripcion", t.getDescripcion()),
							Updates.set("fecha_vencimiento", t.getFecha_vencimiento()),
							Updates.set("id_proyecto", t.getId_proyecto()));
					MongoCollection<Document> doc = MongoDB.database.getCollection("Tareas");
					UpdateResult result = doc.updateOne(tarea, updates);
					IO.println("Se han modificado: " + result.getModifiedCount() + " registros.");
				} else {
					Bson updates = Updates.combine(Updates.set("nombre", t.getNombre()),
							Updates.set("descripcion", t.getDescripcion()),
							Updates.set("fecha_vencimiento", t.getFecha_vencimiento()));
					MongoCollection<Document> doc = MongoDB.database.getCollection("Tareas");
					UpdateResult result = doc.updateOne(tarea, updates);
					IO.println("Se han modificado: " + result.getModifiedCount() + " registros.");
				}
				return true;
			} catch (Exception e) {
				IO.println("Error al ingresar los nuevos valores: " + e.getMessage());
			}
		}
		return false;
	}
	
	@Override
	public boolean updateByState(Tarea t) {
		Document tarea = getById(t.getId());
		if (tarea != null) {
			try {
					Bson update = Updates.set("estado", t.getEstado());
					MongoCollection<Document> doc = MongoDB.database.getCollection("Tareas");
					UpdateResult result = doc.updateOne(tarea, update);
					IO.println("Se han modificado: " + result.getModifiedCount() + " registros.");
				return true;
			} catch (Exception e) {
				IO.println("Error al ingresar los nuevis valores: " + e.getMessage());
			}
		}
		
		return false;
	}
	
	@Override
	public void getByState(String estado) {
		// TODO Auto-generated method stub
		MongoCollection<Document> doc = MongoDB.database.getCollection("Tareas");
		ArrayList<Document> tareas = new ArrayList<>();
		doc.find(eq("estado", estado)).into(tareas);
		if (!tareas.isEmpty()) {
			for (Document tarea : tareas) {
				IO.println(tarea);
			}
		} else {
			IO.println("No se han encontrado tareas con ese estado.");
		}
	}
}
