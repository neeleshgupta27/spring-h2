package com.neel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neel.entity.Person;
import com.neel.model.PersonDto;
import com.neel.repository.PersonRepository;

@Service
public class PersonService {

	private PersonRepository personRepository;

	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	public List<Person> getAllPersons(){
		List<Person> personList = personRepository.findAll();
		return personList;
	}
	
	public Person getOnePerson(Long personId) {
		return personRepository.findOne(personId);
	}
	
	public PersonDto save(PersonDto personDto) {
		Long personId = personDto.getId();
		Person oldPerson = personRepository.findOne(personId);
		if(oldPerson!=null) {
			System.out.println("Record already avilable with ID:" +personId.toString());
		}
		Person person = new Person();
		person.setId(personDto.getId());
		person.setFirstName(personDto.getFirstName());
		person.setLastName(personDto.getLastName());
		Person newPerson = personRepository.save(person);
		return personDto;
	}
	
	public PersonDto update(PersonDto personDto, Long personId) {
		Person person = personRepository.findOne(personId);
		person.setFirstName(personDto.getFirstName());
		person.setLastName(personDto.getLastName());
		personRepository.saveAndFlush(person);
		return personDto;
	}
	
	public String delete(Long personId) {
		personRepository.delete(personId);
		return "Record deleted successfully";
	}
	
	
}
