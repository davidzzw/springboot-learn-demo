package com.zzw.repository;

import java.util.UUID;

import com.zzw.repository.entity.PersonEntity;
import com.zzw.vo.Person;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PersonRepository extends ReactiveCassandraRepository<PersonEntity, UUID> {
    Mono<Person> findOne(String id);
}
