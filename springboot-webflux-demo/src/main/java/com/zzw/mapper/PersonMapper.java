package com.zzw.mapper;
import com.zzw.repository.entity.PersonByCountryEntity;
import com.zzw.repository.entity.PersonByCountryKey;
import com.zzw.repository.entity.PersonEntity;
import com.zzw.vo.Person;

public class PersonMapper {

  private PersonMapper() {}

  public static Person toPerson(PersonByCountryEntity personByCountryEntity) {
    return new Person(
        personByCountryEntity.getKey().getId(),
        personByCountryEntity.getKey().getFirstName(),
        personByCountryEntity.getKey().getLastName(),
        personByCountryEntity.getKey().getCountry(),
        personByCountryEntity.getAge());
  }

  public static Person toPerson(PersonEntity personEntity) {
    return new Person(
        personEntity.getId(),
        personEntity.getFirstName(),
        personEntity.getLastName(),
        personEntity.getCountry(),
        personEntity.getAge());
  }

  public static PersonEntity toPersonEntity(Person person) {
    return new PersonEntity(
        person.getId(),
        person.getFirstName(),
        person.getLastName(),
        person.getCountry(),
        person.getAge());
  }

  public static PersonByCountryEntity toPersonByCountryEntity(Person person) {
    return new PersonByCountryEntity(
        new PersonByCountryKey(
            person.getCountry(), person.getFirstName(), person.getLastName(), person.getId()),
        person.getAge());
  }
}
