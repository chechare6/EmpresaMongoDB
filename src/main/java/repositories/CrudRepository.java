package repositories;

import org.bson.Document;
import org.bson.types.ObjectId;

public interface CrudRepository<T> {
	void getAll();
	Document getById(ObjectId id);
	Boolean save (T entity);
	Boolean delete(String nombre);
	Boolean update(ObjectId	id);
}
