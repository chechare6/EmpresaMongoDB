package repositories.tareas;

import model.Tarea;
import repositories.CrudRepository;

/**
 * Interfaz que extiende CrudRepository para operaciones específicas relacionadas con las tareas.
 */
public interface TareasRepository extends CrudRepository<Tarea>{

	/**
	 * Obtiene todas las tareas que coinciden con un estado específico.
	 *
	 * @param estado Estado de las tareas a buscar.
	 */
	public void getByState(String estado);

	/**
	 * Actualiza el estado de una tarea específica en la base de datos.
	 *
	 * @param t Tarea con el nuevo estado.
	 * @return true si la operación se realiza con éxito, false en caso contrario.
	 */
	public boolean updateByState(Tarea t);
}