package io.codefountain.spring.todo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import io.codefountain.spring.todo.domain.Todo;

@Repository
public interface TodoRepository extends PagingAndSortingRepository<Todo, Long>{

}
