package br.com.adam.studyingspringboot.services;

import br.com.adam.studyingspringboot.dtos.PersonDTO;
import br.com.adam.studyingspringboot.controllers.PersonController;


import br.com.adam.studyingspringboot.infra.exceptions.PersonException;
import br.com.adam.studyingspringboot.model.Person;
import br.com.adam.studyingspringboot.repositories.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
public class PersonServices {
    @Autowired
    PersonRepository repository;

    public List<Person>findAll(){
        List<Person> personList = repository.findAll();
        if (!personList.isEmpty()){
            for (Person person:personList) {
                person.add(linkTo(PersonController.class).slash(person.getId()).withSelfRel());
            }
        }
        return personList;
    }
    public Person findById(long id){
        Optional<Person> entity = repository.findById(id);



        if(entity.isEmpty()){
            throw new PersonException();
        }
        entity.get().add(linkTo(PersonController.class).withRel("Person list"));
        BeanUtils.copyProperties(entity, PersonDTO.class);
        return entity.get();
    }
    public PersonDTO create(PersonDTO person){
        var entity = new Person();
        BeanUtils.copyProperties(person, entity);
        repository.save(entity);
        return person;

    }
    public PersonDTO update(PersonDTO personDto, long id){
        Optional<Person> entity =  repository.findById(id);
        if(entity.isEmpty()){
            throw new PersonException();
        }
        var person = entity.get();
        BeanUtils.copyProperties(personDto, person);
        repository.save(person);
        return personDto;
    }
    public void delete(long id){
        Optional<Person> entity =  repository.findById(id);
        if(entity.isEmpty()){
            throw new PersonException();
        }
       repository.delete(entity.get());

    }


}
