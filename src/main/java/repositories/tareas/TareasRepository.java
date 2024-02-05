package repositories.tareas;

import org.bson.Document;

import model.Tarea;
import repositories.CrudRepository;

public interface TareasRepository extends CrudRepository<Tarea>{
	public Document getByState(String estado);
}