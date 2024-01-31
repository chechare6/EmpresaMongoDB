package repositories;


public interface CrudRepository<T> {
	void getAll();
	Boolean save (T entity);
	Boolean delete(String nombre);
	Boolean update(String nombre);
}
