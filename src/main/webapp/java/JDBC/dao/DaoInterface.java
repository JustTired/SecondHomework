package JDBC.dao;

import java.util.List;

public interface DaoInterface<K, E> {
    E instance(E entity);

    List<E> readAll();

    E readFirst();

    boolean update(K identify, E entity);

    boolean delete(K identify);
}
