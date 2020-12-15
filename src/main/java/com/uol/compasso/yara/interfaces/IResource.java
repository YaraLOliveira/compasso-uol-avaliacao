package com.uol.compasso.yara.interfaces;

import org.springframework.http.ResponseEntity;

/**
 *
 * Utilizamos o acrônomo C.R.U.D
 * @param <T> Tipo da classe que iremos ter no contrato
 * @param <N> Tipo numérico para identificador do objeto.
 */
public interface IResource<T,N> {

    ResponseEntity<?> create(T entity);

    ResponseEntity<?> read(N id);

    //ResponseEntity<?> read(String descricao, Integer page, Integer size);

    ResponseEntity<?> update(N id, T entity);

    ResponseEntity<?> patch(N id, T entity);

    ResponseEntity<?> delete(N id);

    ResponseEntity<?> options();

}
