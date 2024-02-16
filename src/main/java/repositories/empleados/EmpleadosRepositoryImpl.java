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

/**
 * Implementación de la interfaz EmpleadosRepository para realizar operaciones relacionadas con los empleados en MongoDB.
 */
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
		if (e.getSalario() != null && e.getFechaEntrada() != null) {
			MongoCollection<Document> collection = MongoDB.database.getCollection("Empleados");
			InsertOneResult result = collection.insertOne(new Document().append("_id", new ObjectId())
					.append("nombre", e.getNombre()).append("puesto", e.getPuesto()).append("salario", e.getSalario())
					.append("fecha_entrada", e.getFechaEntrada()));
			IO.println("Se le ha asignado la id: " + result.getInsertedId());
			return true;
		} else if (e.getSalario() != null && e.getFechaEntrada() == null) {
			MongoCollection<Document> collection = MongoDB.database.getCollection("Empleados");
			InsertOneResult result = collection.insertOne(new Document().append("_id", new ObjectId())
					.append("nombre", e.getNombre()).append("puesto", e.getPuesto()).append("salario", e.getSalario()));
			IO.println("Se le ha asignado la id: " + result.getInsertedId());
			return true;
		} else if (e.getSalario() == null && e.getFechaEntrada() != null) {
			MongoCollection<Document> collection = MongoDB.database.getCollection("Empleados");
			InsertOneResult result = collection
					.insertOne(new Document().append("_id", new ObjectId()).append("nombre", e.getNombre())
							.append("puesto", e.getPuesto()).append("fecha_entrada", e.getFechaEntrada()));
			IO.println("Se le ha asignado la id: " + result.getInsertedId());
			return true;
		} else {
			try {
				MongoCollection<Document> collection = MongoDB.database.getCollection("Empleados");
				InsertOneResult result = collection.insertOne(new Document().append("_id", new ObjectId())
						.append("nombre", e.getNombre()).append("puesto", e.getPuesto()));
				IO.println("Se le ha asignado la id: " + result.getInsertedId());
				return true;
			} catch (Exception ex) {
				IO.println("Error al guardar un nuevo empleado: " + ex.getMessage());
			}
		}
		return false;
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
	public Boolean update(Empleado e) {
		Document empleado = getById(e.get_id());
		if (empleado != null) {
			try {
				if (e.getSalario() != null && e.getFechaEntrada() != null) {
					Bson updates = Updates.combine(Updates.set("nombre", e.getNombre()),
							Updates.set("puesto", e.getPuesto()), Updates.set("salario", e.getSalario()),
							Updates.set("fecha_entrada", e.getFechaEntrada()));
					MongoCollection<Document> collection = MongoDB.database.getCollection("Empleados");
					UpdateResult result = collection.updateOne(empleado, updates);
					IO.println("Se han modificado: " + result.getModifiedCount() + " registros.");
				} else if (e.getSalario() != null && e.getFechaEntrada() == null) {
					Bson updates = Updates.combine(Updates.set("nombre", e.getNombre()),
							Updates.set("puesto", e.getPuesto()), Updates.set("salario", e.getSalario()));
					MongoCollection<Document> collection = MongoDB.database.getCollection("Empleados");
					UpdateResult result = collection.updateOne(empleado, updates);
					IO.println("Se han modificado: " + result.getModifiedCount() + " registros.");
				} else if (e.getSalario() == null && e.getFechaEntrada() != null) {
					Bson updates = Updates.combine(Updates.set("nombre", e.getNombre()),
							Updates.set("puesto", e.getPuesto()), Updates.set("fecha_entrada", e.getFechaEntrada()));
					MongoCollection<Document> collection = MongoDB.database.getCollection("Empleados");
					UpdateResult result = collection.updateOne(empleado, updates);
					IO.println("Se han modificado: " + result.getModifiedCount() + " registros.");
				} else {
					Bson updates = Updates.combine(Updates.set("nombre", e.getNombre()),
							Updates.set("puesto", e.getPuesto()));
					MongoCollection<Document> collection = MongoDB.database.getCollection("Empleados");
					UpdateResult result = collection.updateOne(empleado, updates);
					IO.println("Se han modificado: " + result.getModifiedCount() + " registros.");
				}
				return true;
			} catch (Exception ex) {
				IO.println("Error al ingresar los nuevos valores: " + ex.getMessage());
			}
		}
		return false;
	}

}
