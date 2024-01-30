package repositories;

import java.util.ArrayList;

public interface CrudRepository<T> {
	ArrayList<T> getAll();
}
