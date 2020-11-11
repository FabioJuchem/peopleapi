package com.fabiojuchem.peopleapi.infrastructure.querydsl;

import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public interface QueryDslRepository<T> extends QuerydslPredicateExecutor {
    default List<T> findAllAsList(Predicate predicate) {
        return (List<T>) findAllAsCollection(predicate, ArrayList::new);
    }

    default Collection findAllAsCollection(Predicate predicate, Supplier<Collection> supplier) {
        Collection collection = supplier.get();
        findAll(predicate).forEach(collection::add);
        return collection;
    }

    @Override
    Optional<T> findOne(Predicate predicate);

    @Override
    boolean exists(Predicate predicate);
}
