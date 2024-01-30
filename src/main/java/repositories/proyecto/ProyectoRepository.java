package repositories.proyecto;

import org.bson.Document;

import model.Empleado;
import model.Proyecto;
import repositories.CrudRepository;

public interface ProyectoRepository extends CrudRepository<Document>{
	boolean addEmpleado(Empleado e, Proyecto p);
	boolean deleteEmpleado(Empleado e, Proyecto p);
}
