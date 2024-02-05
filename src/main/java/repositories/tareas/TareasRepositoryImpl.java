package repositories.tareas;

import static com.mongodb.client.model.Filters.eq;

import java.sql.Date;
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
			InsertOneResult result = collection.insertOne(new Document().append("_id", new ObjectId())
					.append("nombre", t.getNombre()).append("descripcion", t.getDescripcion())
					.append("estado", t.getEstado()).append("fecha_vencimiento", t.getFecha_vencimiento())
					.append("id_proyecto", t.getId_proyecto()));
			IO.println("Se le ha asignado la id: " + result.getInsertedId());
			return true;
		} catch (Exception ex) {
			return false;
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
	public Boolean update(ObjectId id) {
		Document tarea = getById(id);
		if (tarea != null) {
			try {
				IO.print("Introduce el nuevo nombre da la tarea: ");
				String nombre = IO.readString();
				IO.print("Introduce la nueva descripcion de la tarea: ");
				String descripcion = IO.readString();
				IO.print("Introduce la nueva Fecha de vencimiento de la tarea: (yyyy-MM-dd): ");
				Date fechaFin = Date.valueOf(IO.readLocalDate());

				Bson updates = Updates.combine(Updates.set("nombre", nombre), Updates.set("descripcion", descripcion),
						Updates.set("fecha_vencimiento", fechaFin));

				MongoCollection<Document> doc = MongoDB.database.getCollection("Tareas");
				UpdateResult result = doc.updateOne(eq("_id", id), updates);
				if (result.getModifiedCount() > 0) {
					IO.print("Tarea actualizada exitosamente.");
					return true;
				} else {
					IO.print("No se pudo actualizar la tarea.");
					return false;
				}
			} catch (Exception e) {
				IO.print("Error al ingresar los nuevos valores.");
				return false;
			}
		} else {
			IO.print("La tarea con el ID proporcionado no existe.");
			return false;
		}
	}

	@Override
	public void getByState(String estado) {
		// TODO Auto-generated method stub
		MongoCollection<Document> doc = MongoDB.database.getCollection("Tareas");
		ArrayList<Document> tareas = new ArrayList<>();
		doc.find(eq("estado", estado)).into(tareas);
		if(!tareas.isEmpty()) {
			for (Document tarea : tareas) {
				IO.println(tarea);
			}			
		} else {
			IO.println("No se han encontrado tareas con ese estado.");
		}
	}
}
