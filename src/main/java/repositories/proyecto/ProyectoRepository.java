package repositories.proyecto;

import model.Proyecto;
import repositories.CrudRepository;

public interface ProyectoRepository extends CrudRepository<Proyecto>{
	public void addEmpleado(); //Método que añadirá un empleado a un proyecto
}