package io.codefountain.spring.todo.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.codefountain.spring.todo.domain.Todo;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long>{

}
