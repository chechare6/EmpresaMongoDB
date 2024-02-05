package repositories.proyecto;

import org.bson.types.ObjectId;

import model.Proyecto;
import repositories.CrudRepository;

public interface ProyectoRepository extends CrudRepository<Proyecto>{
	public Boolean addEmpleado(ObjectId idEmpleado, ObjectId idProyecto);
	public Boolean deleteEmpleado(ObjectId idEmpleado, ObjectId idProyecto);
}