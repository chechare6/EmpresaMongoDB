package repositories.tareas;

import model.Tarea;
import repositories.CrudRepository;

public interface TareasRepository extends CrudRepository<Tarea>{
	public void getByState(String estado);
	//----
	public boolean updateTareaByState(Tarea t);
}