package ru.easyjava.spring.data.jpa.dal;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import ru.easyjava.spring.data.jpa.entity.Person;

import java.util.Collection;

/**
 * Repository for person entity.
 */
public interface PersonRepository extends CrudRepository<Person, Long>, QueryDslPredicateExecutor<Person> { }
