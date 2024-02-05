package repositories.tareas;

import org.bson.Document;
import org.bson.types.ObjectId;

import model.Tarea;
import repositories.CrudRepository;

public interface TareasRepository extends CrudRepository<Tarea>{
	public Document getByState(ObjectId id);
}