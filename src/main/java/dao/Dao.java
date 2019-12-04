package dao;

import com.sun.tools.javac.util.List;

public interface Dao<T> {

    T get(int id);

    List<T> getAll();

    void save(T item);

    void delete(T item);

    void update(T item);
}
