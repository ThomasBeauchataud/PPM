package models.common;

import java.util.List;

public interface CommonDaoInterface<T> {

    /**
     * Insert a new Entity<T>
     * @param object <T>
     */
    void insert(T object);

    /**
     * Update an Entity<T>
     * @param object
     */
    void update(T object);

    /**
     * Return an Entity by his Id
     * @param id int
     * @return <T>
     */
    T getById(int id);

    /**
     * Return all Entities<T>
     * @return List<T>
     */
    List<T> getAll();

    /**
     * Delete and Entity<T> by his Id
     * @param id int
     */
    void deleteById(int id);

}
