package Controller;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.bson.Document;

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
