package repositories.proyecto;

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
import model.Proyecto;

/**
 * Implementación de la interfaz ProyectoRepository para realizar operaciones relacionadas con los proyectos en MongoDB.
 */
public class ProyectoRepositoryImpl implements ProyectoRepository {

	@Override
	public void getAll() {
		MongoCollection<Document> doc = MongoDB.database.getCollection("Proyectos");
		ArrayList<Document> proyectos = new ArrayList<>();
		doc.find().into(proyectos);
		for (Document proyecto : proyectos) {
			IO.println(proyecto);
		}
	}

	@Override
	public Document getById(ObjectId id) {
		MongoCollection<Document> doc = MongoDB.database.getCollection("Proyectos");
		Document proyecto = doc.find(eq("_id", id)).first();
		return proyecto;
	}

	@Override
	public Boolean save(Proyecto p) {
		if (p.getIdEmpleado() != null) {
			try {
				MongoCollection<Document> collection = MongoDB.database.getCollection("Proyectos");
				InsertOneResult result = collection
						.insertOne(new Document().append("_id", new ObjectId()).append("nombre", p.getNombre())
								.append("descripcion", p.getDescripcion()).append("fecha_inicio", p.getFecha_inicio())
								.append("fecha_fin", p.getFecha_fin()).append("idEmpleado", p.getIdEmpleado()));
				IO.println("Se le ha asignado la id: " + result.getInsertedId());
				return true;
			} catch (Exception ex) {
				return false;
			}
		} else {
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
	}

	@Override
	public Boolean delete(ObjectId id) {
		try {
			MongoCollection<Document> collection = MongoDB.database.getCollection("Proyectos");
			Bson query = eq("_id", id);
			DeleteResult result = collection.deleteOne(query);
			IO.println("Se ha borrado " + result.getDeletedCount() + " entrada/s.");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean update(Proyecto p) {
		Document proyecto = getById(p.get_id());
		if (proyecto != null) {
			try {
				if (p.getIdEmpleado() != null) {
					Bson updates = Updates.combine(Updates.set("nombre", p.getNombre()),
							Updates.set("descripcion", p.getDescripcion()),
							Updates.set("fecha_inicio", p.getFecha_inicio()),
							Updates.set("fecha_fin", p.getFecha_fin()), Updates.set("idEmpleado", p.getIdEmpleado()));
					MongoCollection<Document> collection = MongoDB.database.getCollection("Proyectos");
					UpdateResult result = collection.updateOne(proyecto, updates);
					IO.println("Se han modificado: " + result.getModifiedCount() + " registros.");
				} else {
					Bson updates = Updates.combine(Updates.set("nombre", p.getNombre()),
							Updates.set("descripcion", p.getDescripcion()),
							Updates.set("fecha_inicio", p.getFecha_inicio()),
							Updates.set("fecha_fin", p.getFecha_fin()), Updates.set("idEmpleado", p.getIdEmpleado()));
					MongoCollection<Document> collection = MongoDB.database.getCollection("Proyectos");
					UpdateResult result = collection.updateOne(proyecto, updates);
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
	public Boolean addEmpleado(ObjectId idEmpleado, ObjectId idProyecto) {
		Document proyecto = getById(idProyecto);
		if ((proyecto.getObjectId("idEmpleado") == null)) {
			try {
				Bson updates = Updates.set("idEmpleado", idEmpleado);
				MongoCollection<Document> collection = MongoDB.database.getCollection("Proyectos");
				try {
					UpdateResult result = collection.updateOne(proyecto, updates);
					IO.println("Modified document count: " + result.getModifiedCount());
					return true;
				} catch (Exception e) {
					return false;
				}
			} catch (Exception e) {
				return false;
			}
		} else {
			IO.println("Este proyecto ya tiene asignado un empleado.");
			return false;
		}
	}

	@Override
	public Boolean deleteEmpleado(ObjectId idProyecto) {
		Document proyecto = getById(idProyecto);
		if (proyecto.getObjectId("idEmpleado") != null) {
			try {
				Bson update = Updates.set("idEmpleado", null);
				MongoCollection<Document> collection = MongoDB.database.getCollection("Proyectos");
				try {
					UpdateResult result = collection.updateOne(proyecto, update);
					IO.println("Modified document count: " + result.getModifiedCount());
					return true;
				} catch (Exception e) {
					return false;
				}
			} catch (Exception e) {
				return false;
			}
		} else {

			return false;
		}
	}
}
