package repositories;


public interface CrudRepository<T> {
	void getAll();
	Boolean getById(String id);
	Boolean save (T entity);
	Boolean delete(String nombre);
	Boolean update(String nombre);
}
