package Controller;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;

import conexionDB.MongoDB;
import model.Empleado;
import repositories.empleados.EmpleadosRepository;
import repositories.proyecto.ProyectoRepository;
import repositories.tareas.TareasRepository;

public class Controller {
	private final Logger logger = Logger.getLogger(Controller.class.getName());
	// DEPENDENCIAS
	private final EmpleadosRepository empleadosRepository;
	private final ProyectoRepository proyectoRepository;
	private final TareasRepository departamentosRepository;
	
	public Controller(EmpleadosRepository empleadosRepository, ProyectoRepository proyectoRepository, TareasRepository departamentosRepository) {
		this.empleadosRepository = empleadosRepository;
		this.departamentosRepository = departamentosRepository;
		this.proyectoRepository = proyectoRepository;
	}

	// EMPLEADOS
	public ArrayList<Document> getEmpleados(){
		logger.info("Obteniendo empleados...");
		return empleadosRepository.getAll();
	}
	
	public Boolean addEmpleado(Empleado e) {
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
	
	// DEPARTAMENTOS
	public ArrayList<Document> getDepartamentos(){
		logger.info("Obteniendo empleados...");
		return departamentosRepository.getAll();
	}
	
	// PROYECTOS
	public ArrayList<Document> getProyectos(){
		logger.info("Obteniendo proyectos...");
		return proyectoRepository.getAll();
	}
}
