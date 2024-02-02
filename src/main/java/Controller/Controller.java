package Controller;

import java.util.logging.Logger;

import model.Tarea;
import org.bson.Document;
import org.bson.types.ObjectId;

import model.Empleado;
import model.Proyecto;
import repositories.empleados.EmpleadosRepository;
import repositories.proyecto.ProyectoRepository;
import repositories.tareas.TareasRepository;

public class Controller {
	private final Logger logger = Logger.getLogger(Controller.class.getName());
	// DEPENDENCIAS
	private final EmpleadosRepository empleadosRepository;
	private final ProyectoRepository proyectoRepository;
	private final TareasRepository tareasRepository;

	public Controller(EmpleadosRepository empleadosRepository, ProyectoRepository proyectoRepository, TareasRepository tareasRepository) {
		this.empleadosRepository = empleadosRepository;
		this.proyectoRepository = proyectoRepository;
        this.tareasRepository = tareasRepository;
    }

	// EMPLEADOS
	/**
	 * Método que busca en la BBDD todos los empleados y los imprime
	 * @return Un String de todos los empleados
	 */
	public void getEmpleados() {
		logger.info("Obteniendo empleados...");
		empleadosRepository.getAll();
	}
	
	/**
	 * Método para buscar empleados según su ID
	 * @param id [La id que busca]
	 * @return 
	 */
	public Document searchEmpleado(ObjectId id) {
		logger.info("Buscando empleado...");
		return empleadosRepository.getById(id);
	}

	/**
	 * Método para añadir un empleado a la BBDD
	 * @param e [Empleado a añadir]
	 * @return true si se consigue añadir, false en caso contrario
	 */
	public Boolean addEmpleado(Empleado e) {
		logger.info("Añadiendo empleado...");
		return empleadosRepository.save(e);
	}

	/**
	 * Método para borrar empleados de la BBDD
	 * @param nombre [Del empleado a borrar]
	 * @return true si se borra con éxito, false en caso contario
	 */
	public Boolean deleteEmpleado(String nombre) {
		/* TODO: AQUI TENEMOS QUE MIRAR SI HAY MÁS DE UN EMPLEADO CON ESE NOMBRE Y HACER ELEGIR, ¿O BORRAR SEGÚN ID? */
		logger.info("Borrando empleado...");
		return empleadosRepository.delete(nombre);
	}
	
	public Boolean updateEmpleado(ObjectId id) {
		logger.info("Modificando empleado...");
		return empleadosRepository.update(id);
	}

	// PROYECTOS
	public void getProyectos() {
		logger.info("Obteniendo proyectos...");
		proyectoRepository.getAll();
	}
	
	public void searchProyecto(ObjectId id) {
		logger.info("Buscando proyecto...");
		proyectoRepository.getById(id);
	}
	
	public Boolean addProyecto(Proyecto p) {
		logger.info("Añadiendo proyecto...");
		return proyectoRepository.save(p);
	}

	public Boolean deleteProyecto(String nombre) {
		logger.info("Borrando proyecto...");
		return proyectoRepository.delete(nombre);
		
	}

	//TAREAS
	public void getTareas() {
		logger.info("Obteniendo tareas...");
		tareasRepository.getAll();
	}
	public boolean addTarea(Tarea t){
		logger.info("Añadiendo tarea...");
		return tareasRepository.save(t);
	}

	public boolean deleteTarea(String nombre){
		return tareasRepository.delete(nombre);
	}
	public Boolean updateTarea(ObjectId id) {
		logger.info("Modificando empleado...");
		return tareasRepository.update(id);
	}
	public Document searchTarea(ObjectId id) {
		logger.info("Buscando empleado...");
		return tareasRepository.getById(id);
	}
}
