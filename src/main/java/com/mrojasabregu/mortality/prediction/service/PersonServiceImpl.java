package com.mrojasabregu.mortality.prediction.service;

import java.util.Optional;

import com.mrojasabregu.mortality.prediction.model.Person;
import com.mrojasabregu.mortality.prediction.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements GenericEntityService<Person> {
	
	@Autowired
	PersonRepository personRepository;

	@Override
	public Iterable<Person> findAll() {
		return personRepository.findAll();
	}

	@Override
	public Person save(Person student) {
		return personRepository.save(student);
	}

	@Override
	public Optional<Person> findByID(String id) {
		return personRepository.findById(id);
	}

	@Override
	public void remove(String id) {
		Person st = new Person();
    	st.setId(id);
    	personRepository.delete(st);
	}
}
