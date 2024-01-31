package repositories;


public interface CrudRepository<T> {
	void getAll();
	void getById();
	Boolean save (T entity);
	Boolean delete(String nombre);
	Boolean update(String nombre);
}
