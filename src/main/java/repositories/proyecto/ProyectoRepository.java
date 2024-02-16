package repositories.proyecto;

import org.bson.types.ObjectId;

import model.Proyecto;
import repositories.CrudRepository;

/**
 * Interfaz que extiende CrudRepository para operaciones específicas relacionadas con los proyectos.
 */
public interface ProyectoRepository extends CrudRepository<Proyecto>{

	/**
	 * Asocia un empleado a un proyecto.
	 *
	 * @param idEmpleado ID del empleado a asociar.
	 * @param idProyecto ID del proyecto al que se asociará el empleado.
	 * @return true si la operación se realiza con éxito, false en caso contrario.
	 */
	public Boolean addEmpleado(ObjectId idEmpleado, ObjectId idProyecto);

	/**
	 * Elimina un empleado de un proyecto.
	 *
	 * @param idProyecto ID del proyecto del cual se eliminará el empleado.
	 * @return true si la operación se realiza con éxito, false en caso contrario.
	 */
	public Boolean deleteEmpleado(ObjectId idProyecto);
}