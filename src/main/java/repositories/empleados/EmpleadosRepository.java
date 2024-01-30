package repositories.empleados;

import org.bson.Document;

import model.Empleado;
import model.Proyecto;
import repositories.CrudRepository;

public interface EmpleadosRepository extends CrudRepository<Document>{
	boolean addProyect(Proyecto p, Empleado e);
	boolean deleteProyect(Proyecto p, Empleado e);
}
