package repositories.empleados;

import model.Empleado;
import repositories.CrudRepository;

public interface EmpleadosRepository extends CrudRepository<Empleado>{
	public void addToProyecto(); //Método para añadir a un empleado a un proyecto
}