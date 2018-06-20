/*
 * FileName: PersonController.java
 * Author:   zzw
 * Date:     2018年05月28日
 * Description:
 */
package com.zzw.controller;

import com.zzw.repository.PersonRepository;
import com.zzw.repository.entity.PersonEntity;
import com.zzw.vo.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 〈〉<br>
 * 〈〉
 *
 * @author zzw
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本]（可选）
 */
public class PersonController {


    private final PersonRepository personRepository;
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @GetMapping("/people")
    public Flux<PersonEntity> all() {
        return personRepository.findAll();
    }


    @GetMapping("/people/{id}")
    Mono<Person> findById(@PathVariable String id) {
        return personRepository.findOne(id);
    }
}
