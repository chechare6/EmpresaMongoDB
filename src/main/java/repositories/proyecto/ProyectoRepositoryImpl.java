package repositories.proyecto;

import static com.mongodb.client.model.Filters.eq;

import java.sql.Date;
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
		try {
			MongoCollection<Document> collection = MongoDB.database.getCollection("Proyectos");
			InsertOneResult result = collection.insertOne(new Document()
					.append("_id", new ObjectId())
					.append("nombre", p.getNombre())
					.append("descripcion", p.getDescripcion())
					.append("fecha_inicio", p.getFecha_inicio())
					.append("fecha_fin", p.getFecha_fin()));
			IO.println("Se le ha asignado la id: " + result.getInsertedId());
			return true;
		} catch (Exception ex) {
			return false;
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
	public Boolean update(ObjectId id) {
		Document proyecto = getById(id);
		if (proyecto != null) {
			try {
				IO.print("Intoduce el nuevo nombre del proyecto: ");
				String nombre = IO.readString();
				IO.print("Intoduce la nueva descripcion del proyecto: ");
				String descripcion = IO.readString();
				IO.print("Introduce la nueva fecha de inicio: (yyyy-MM-dd)");
				Date fecha_inicio = Date.valueOf(IO.readLocalDate());
				IO.print("Introduce la nueva fecha de fin: (yyyy-MM-dd)");
				Date fecha_fin = Date.valueOf(IO.readLocalDate());
				Bson updates = Updates.combine(
						Updates.set("nombre", nombre),
						Updates.set("descripcion", descripcion),
						Updates.set("fecha_inicio", fecha_inicio),
						Updates.set("fecha_fin", fecha_fin));
				MongoCollection<Document> collection = MongoDB.database.getCollection("Proyectos");
				try {					
					UpdateResult result = collection.updateOne(proyecto, updates);
					IO.println("Modified document count: " + result.getModifiedCount());;
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

//	@Override
//	public Boolean addEmpleado(ObjectId idEmpleado, ObjectId idProyecto) {
//		Document proyecto = getById(idProyecto);
//		if (proyecto != null) {
//			try {
//				MongoCollection<Document> collection = MongoDB.database.getCollection("Proyectos");
//				try {
//					if(proyecto.get("idEmpleado").equals(null)) { // TODO: Mirar si hace bien la comparativa
//					UpdateResult result = collection.updateOne(proyecto, Updates.set("idEmpleado", idEmpleado)); 
//					IO.println("Modified document count: " + result.getModifiedCount());
//					return true;
//					} else {
//						IO.println("El proyecto ya esta asignado a un empleado");
//					}
//				} catch (Exception e) {
//					return false;
//				}
//			} catch (Exception e) {
//				return false;
//			}
//		}
//		return false;
//	}
//
//	@Override
//	public Boolean deleteEmpleado(ObjectId idProyecto) {
//		Document proyecto = getById(idProyecto);
//		if (proyecto != null) {
//			try {
//				MongoCollection<Document> collection = MongoDB.database.getCollection("Proyectos");
//				try {					
//					UpdateResult result = collection.updateOne(proyecto, Updates.set("idEmpleado", idEmpleado)); 
//					IO.println("Modified document count: " + result.getModifiedCount());
//					return true;
//				} catch (Exception e) {
//					return false;
//				}
//			} catch (Exception e) {
//				return false;
//			}
//		}
//		return false;
//		
//	}
}
