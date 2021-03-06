package ru.easyjava.spring.data.jpa.dal;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import ru.easyjava.spring.data.jpa.entity.Passport;

/**
 * Repository for passport entity.
 */
public interface PassportRepository extends CrudRepository<Passport, Long>, QueryDslPredicateExecutor<Passport> { }
