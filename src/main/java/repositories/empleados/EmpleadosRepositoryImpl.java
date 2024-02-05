package repositories.empleados;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;

import IO.IO;
import conexionDB.MongoDB;
import model.Empleado;

public class EmpleadosRepositoryImpl implements EmpleadosRepository {

	@Override
	public void getAll() {
		MongoCollection<Document> doc = MongoDB.database.getCollection("Empleados");
		ArrayList<Document> empleados = new ArrayList<>();
		doc.find().into(empleados);
		for (Document empleado : empleados) {
			IO.println(empleado);
		}
	}

	@Override
	public Document getById(ObjectId id) {
		MongoCollection<Document> doc = MongoDB.database.getCollection("Empleados");
		Document empleado = doc.find(eq("_id", id)).first();
		return empleado;
	}

	@Override
	public Boolean save(Empleado e) {
		try {
			MongoCollection<Document> collection = MongoDB.database.getCollection("Empleados");
			InsertOneResult result = collection.insertOne(new Document().append("_id", new ObjectId())
					.append("nombre", e.getNombre()).append("puesto", e.getPuesto()));
			IO.println("Se le ha asignado la id: " + result.getInsertedId());
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public Boolean delete(ObjectId id) {
		try {
			MongoCollection<Document> collection = MongoDB.database.getCollection("Empleados");
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
		Document empleado = getById(id);
		if (empleado != null) {
			try {
				IO.print("Intoduce el nuevo nombre del empleado: ");
				String nombre = IO.readString();
				IO.print("Introduce el nuevo puesto del empleado: ");
				String puesto = IO.readString();
				Bson updates = Updates.combine(Updates.set("nombre", nombre), Updates.set("puesto", puesto));
				MongoCollection<Document> collection = MongoDB.database.getCollection("Empleados");
				try {
					UpdateResult result = collection.updateOne(empleado, updates);
					IO.println("Modified document count: " + result.getModifiedCount());
					;
					return true;
				} catch (Exception e) {
					return false;
				}
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}

}
