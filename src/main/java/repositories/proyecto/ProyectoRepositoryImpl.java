package repositories.proyecto;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import conexionDB.MongoDB;
import model.Empleado;
import model.Proyecto;

public class ProyectoRepositoryImpl implements ProyectoRepository {

	@Override
	public void getAll() {
		MongoCollection<Document> doc = MongoDB.database.getCollection("Proyectos");
		ArrayList<Document> proyectos = new ArrayList<>();
		doc.find().into(proyectos);
	}

	@Override
	public Boolean save(Document entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(String nombre) {
		// TODO Auto-generated method stub
		return null;
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
