package repositories.empleados;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;

import conexionDB.MongoDB;
import model.Empleado;

public class EmpleadosRepositoryImpl implements EmpleadosRepository {

	@Override
	public void getAll() {
		MongoCollection<Document> doc = MongoDB.database.getCollection("Empleados");
		ArrayList<Document> empleados = new ArrayList<>();
		doc.find().into(empleados);
		for (Document empleado : empleados) {
			System.out.println(empleado);
		}
	}

	@Override
	public Boolean save(Empleado e) {
		try {
			MongoCollection<Document> collection = MongoDB.database.getCollection("Empleados");
			InsertOneResult result = collection.insertOne(new Document()
					.append("_id", new ObjectId())
					.append("nombre", e.getNombre())
					.append("puesto", e.getPuesto()));
			System.out.println("Se le ha asignado la id: " + result.getInsertedId());
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public Boolean delete(String nombre) {
		try {
			MongoCollection<Document> collection = MongoDB.database.getCollection("Empleados");
			Bson query = eq("nombre", nombre);
			DeleteResult result = collection.deleteOne(query);
			System.out.println("Se ha borrado " + result.getDeletedCount() + " entradas.");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean update(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}	
}
