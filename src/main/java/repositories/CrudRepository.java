package repositories;

import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Interfaz genérica para operaciones CRUD en la base de datos.
 *
 * @param <T> Tipo de entidad para la cual se realizarán las operaciones CRUD.
 */
public interface CrudRepository<T> {
	/**
	 * Obtiene todos los documentos de la colección.
	 */
	void getAll();

	/**
	 * Busca un documento por su ID.
	 *
	 * @param id ID del documento a buscar.
	 * @return Documento correspondiente al ID proporcionado.
	 */
	Document getById(ObjectId id);

	/**
	 * Guarda un nuevo documento en la colección.
	 *
	 * @param entity Entidad a ser guardada.
	 * @return true si la operación se realiza con éxito, false en caso contrario.
	 */
	Boolean save (T entity);

	/**
	 * Elimina un documento de la colección por su ID.
	 *
	 * @param id ID del documento a eliminar.
	 * @return true si la operación se realiza con éxito, false en caso contrario.
	 */
	Boolean delete(ObjectId	id);

	/**
	 * Actualiza un documento existente en la colección.
	 *
	 * @param entity Entidad con la información actualizada.
	 * @return true si la operación se realiza con éxito, false en caso contrario.
	 */
	Boolean update(T entity);
}
