package com.uol.compasso.yara.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 *
 * @param <T>
 * @param <N>
 */
public interface IService<T,N> {

    T create(T entity);

    List<T> read();

    T read(N id);

    //Page<T> read(String nome, Pageable pageable);

    void update(T entity);

    void deleteById(N id);

    void delete(T entity);
}
