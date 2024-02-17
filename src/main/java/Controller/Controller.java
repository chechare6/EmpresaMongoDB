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

/**
 * Clase Controller que gestiona las operaciones entre la interfaz de usuario y la base de datos.
 */
public class Controller {
	private final Logger logger = Logger.getLogger(Controller.class.getName());
	
	// DEPENDENCIAS
	private final EmpleadosRepository empleadosRepository;
	private final ProyectoRepository proyectoRepository;
	private final TareasRepository tareasRepository;

	/**
	 * Constructor que recibe las instancias de los repositorios necesarios.
	 *
	 * @param empleadosRepository Repositorio de empleados.
	 * @param proyectoRepository  Repositorio de proyectos.
	 * @param tareasRepository    Repositorio de tareas.
	 */
	public Controller(EmpleadosRepository empleadosRepository, ProyectoRepository proyectoRepository, TareasRepository tareasRepository) {
		this.empleadosRepository = empleadosRepository;
		this.proyectoRepository = proyectoRepository;
        this.tareasRepository = tareasRepository;
    }

	// EMPLEADOS
	/**
	 * Método que obtiene todos los empleados de la base de datos y los imprime.
	 */
	public void getEmpleados() {
		logger.info("Obteniendo empleados...");
		empleadosRepository.getAll();
	}

	/**
	 * Método que busca un empleado por su ID.
	 *
	 * @param id ID del empleado a buscar.
	 * @return Documento que representa al empleado.
	 */
	public Document searchEmpleado(ObjectId id) {
		logger.info("Buscando empleado...");
		return empleadosRepository.getById(id);
	}

	/**
	 * Método que añade un empleado a la base de datos.
	 *
	 * @param e Empleado a añadir.
	 * @return true si se añade correctamente, false en caso contrario.
	 */
	public Boolean addEmpleado(Empleado e) {
		logger.info("Añadiendo empleado...");
		return empleadosRepository.save(e);
	}

	/**
	 * Método que elimina un empleado de la base de datos.
	 *
	 * @param id ID del empleado a eliminar.
	 * @return true si se elimina correctamente, false en caso contrario.
	 */
	public Boolean deleteEmpleado(ObjectId id) {
		/* TODO: AQUI TENEMOS QUE MIRAR SI HAY MÁS DE UN EMPLEADO CON ESE NOMBRE Y HACER ELEGIR, ¿O BORRAR SEGÚN ID? */
		logger.info("Borrando empleado...");
		return empleadosRepository.delete(id);
	}

	/**
	 * Método que actualiza la información de un empleado en la base de datos.
	 *
	 * @param e Nuevo estado del empleado.
	 * @return true si se actualiza correctamente, false en caso contrario.
	 */
	public Boolean updateEmpleado(Empleado e) {
		logger.info("Modificando empleado...");
		return empleadosRepository.update(e);
	}

	// PROYECTOS

	/**
	 * Método que obtiene todos los proyectos de la base de datos y los imprime.
	 */
	public void getProyectos() {
		logger.info("Obteniendo proyectos...");
		proyectoRepository.getAll();
	}

	/**
	 * Método que busca un proyecto por su ID.
	 *
	 * @param id ID del proyecto a buscar.
	 * @return Documento que representa al proyecto.
	 */
	public Document searchProyecto(ObjectId id) {
		logger.info("Buscando proyecto...");
		return proyectoRepository.getById(id);
	}

	/**
	 * Método que añade un proyecto a la base de datos.
	 *
	 * @param p Proyecto a añadir.
	 * @return true si se añade correctamente, false en caso contrario.
	 */
	public Boolean addProyecto(Proyecto p) {
		logger.info("Añadiendo proyecto...");
		return proyectoRepository.save(p);
	}

	/**
	 * Método que elimina un proyecto de la base de datos.
	 *
	 * @param id ID del proyecto a eliminar.
	 * @return true si se elimina correctamente, false en caso contrario.
	 */
	public Boolean deleteProyecto(ObjectId id) {
		logger.info("Borrando proyecto...");
		return proyectoRepository.delete(id);
		
	}

	/**
	 * Método que actualiza la información de un proyecto en la base de datos.
	 *
	 * @param p Nuevo estado del proyecto.
	 * @return true si se actualiza correctamente, false en caso contrario.
	 */
	public Boolean updateProyecto(Proyecto p) {
		logger.info("Modificando proyecto...");
		return proyectoRepository.update(p);
	}

	/**
	 * Método que asocia un empleado a un proyecto.
	 *
	 * @param idEmpleado ID del empleado a asociar.
	 * @param idProyecto ID del proyecto al que se asociará el empleado.
	 * @return true si se realiza la asociación correctamente, false en caso contrario.
	 */
	public Boolean addEmpleadoToProyecto(ObjectId idEmpleado, ObjectId idProyecto) {
		logger.info("Añadiendo empleado al proyecto...");
		return proyectoRepository.addEmpleado(idEmpleado, idProyecto);
	}

	/**
	 * Método que elimina un empleado de un proyecto.
	 *
	 * @param idProyecto ID del proyecto del cual se eliminará el empleado.
	 * @return true si se elimina correctamente, false en caso contrario.
	 */
	public Boolean deleteEmpleadoFromProyecto(ObjectId idProyecto) {
		logger.info("Eliminando empleado del proyecto...");
		return proyectoRepository.deleteEmpleado(idProyecto);
	}

	//TAREAS

	/**
	 * Método que obtiene todas las tareas de la base de datos y las imprime.
	 */
	public void getTareas() {
		logger.info("Obteniendo tareas...");
		tareasRepository.getAll();
	}

	/**
	 * Método que busca una tarea por su ID.
	 *
	 * @param id ID de la tarea a buscar.
	 * @return Documento que representa a la tarea.
	 */
	public Document searchTarea(ObjectId id) {
		logger.info("Buscando tarea...");
		return tareasRepository.getById(id);
	}

	/**
	 * Método que añade una tarea a la base de datos.
	 *
	 * @param t Tarea a añadir.
	 * @return true si se añade correctamente, false en caso contrario.
	 */
	public boolean addTarea(Tarea t){
		logger.info("Añadiendo tarea...");
		return tareasRepository.save(t);
	}

	/**
	 * Método que elimina una tarea de la base de datos.
	 *
	 * @param id ID de la tarea a eliminar.
	 * @return true si se elimina correctamente, false en caso contrario.
	 */
	public Boolean deleteTarea(ObjectId id){
		return tareasRepository.delete(id);
	}

	/**
	 * Método que actualiza la información de una tarea en la base de datos.
	 *
	 * @param t Nueva información de la tarea.
	 * @return true si se actualiza correctamente, false en caso contrario.
	 */
	public Boolean updateTarea(Tarea t) {
		logger.info("Modificando empleado...");
		return tareasRepository.update(t);
	}

	/**
	 * Método que busca tareas por su estado.
	 *
	 * @param state Estado de las tareas a buscar.
	 */
	public void searchTareaByState(String state) {
		logger.info("Buscando tarea por estado...");
		tareasRepository.getByState(state);
	}

	/**
	 * Método que actualiza el estado de una tarea en la base de datos.
	 *
	 * @param t Tarea con el nuevo estado.
	 * @return true si se actualiza correctamente, false en caso contrario.
	 */
	public Boolean updateTareaByState(Tarea t) {
		logger.info("Actualizando el estado de la tarea...");
		return tareasRepository.updateByState(t);
	}
}
