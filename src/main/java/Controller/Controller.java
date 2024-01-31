package Controller;

import java.util.ArrayList;
import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
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

	public Controller(EmpleadosRepository empleadosRepository, ProyectoRepository proyectoRepository,
			TareasRepository departamentosRepository) {
		this.empleadosRepository = empleadosRepository;
		this.departamentosRepository = departamentosRepository;
		this.proyectoRepository = proyectoRepository;
	}

	// EMPLEADOS
	/**
	 * Método que busca en la BBDD todos los empleados y los imprime
	 * @return Un String de todos los empleados
	 */
	public ArrayList<Document> getEmpleados() {
		logger.info("Obteniendo empleados...");
		return empleadosRepository.getAll();
	}

	/**
	 * Método para añadir un empleado a la BBDD
	 * @param e [Empleado a añadir]
	 * @return true si se consigue añadir, false en caso contrario
	 */
	public Boolean addEmpleado(Empleado e) {
		logger.info("Añadiendo empleado...");
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

	/**
	 * Método para borrar empleados de la BBDD
	 * @param nombre [Del empleado a borrar]
	 * @return true si se borra con éxito, false en caso contario
	 */
	public Boolean deleteEmpleado(String nombre) {
		/* TODO: AQUI TENEMOS QUE MIRAR SI HAY MÁS DE UN EMPLEADO CON ESE NOMBRE Y HACER ELEGIR, ¿O BORRAR SEGÚN ID? */
		logger.info("Borrando empleado...");
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

	// DEPARTAMENTOS
	public ArrayList<Document> getDepartamentos() {
		logger.info("Obteniendo empleados...");
		return departamentosRepository.getAll();
	}

	// PROYECTOS
	public ArrayList<Document> getProyectos() {
		logger.info("Obteniendo proyectos...");
		return proyectoRepository.getAll();
	}
}
