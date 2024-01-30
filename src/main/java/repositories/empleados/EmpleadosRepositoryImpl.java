package repositories.empleados;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import conexionDB.MongoDB;
import model.Empleado;
import model.Proyecto;

public class EmpleadosRepositoryImpl implements EmpleadosRepository {

	@Override
	public ArrayList<Document> getAll() {
		MongoCollection<Document> doc = MongoDB.database.getCollection("Empleados");
		ArrayList<Document> empleados = new ArrayList<>();
		doc.find().into(empleados);
		return empleados;
	}

	@Override
	public boolean addProyect(Proyecto p, Empleado e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteProyect(Proyecto p, Empleado e) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
