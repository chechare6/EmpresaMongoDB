package repositories;

import org.bson.Document;
import org.bson.types.ObjectId;

public interface CrudRepository<T> {
	void getAll();
	Document getById(ObjectId id);
	Boolean save (T entity);
	Boolean delete(ObjectId	id);
	Boolean update(ObjectId	id);
	//.-------
	//Document getByState(ObjectId id);
}
