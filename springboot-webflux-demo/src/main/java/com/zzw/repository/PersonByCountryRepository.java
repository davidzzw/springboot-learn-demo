package com.zzw.repository;

import com.zzw.repository.entity.PersonByCountryEntity;
import com.zzw.repository.entity.PersonByCountryKey;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PersonByCountryRepository extends ReactiveCassandraRepository<PersonByCountryEntity, PersonByCountryKey> {

  Flux<PersonByCountryEntity> findAllByKeyCountry(final String country);
}
