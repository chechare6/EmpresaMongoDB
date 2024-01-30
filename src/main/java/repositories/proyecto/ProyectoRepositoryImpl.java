package repositories.proyecto;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import conexionDB.MongoDB;
import model.Empleado;
import model.Proyecto;

public class ProyectoRepositoryImpl implements ProyectoRepository {

	@Override
	public ArrayList<Document> getAll() {
		MongoCollection<Document> doc = MongoDB.database.getCollection("Proyectos");
		ArrayList<Document> proyectos = new ArrayList<>();
		doc.find().into(proyectos);
		return proyectos;
	}

	@Override
	public boolean addEmpleado(Empleado e, Proyecto p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEmpleado(Empleado e, Proyecto p) {
		// TODO Auto-generated method stub
		return false;
	}

}
