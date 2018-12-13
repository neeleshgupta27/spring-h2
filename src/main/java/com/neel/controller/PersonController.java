package com.neel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.neel.entity.Person;
import com.neel.model.PersonDto;
import com.neel.service.PersonService;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

	@Autowired
	private PersonService personService;

	@RequestMapping(value = "/getAllPersons", method = RequestMethod.GET)
	public List<Person> getAllPersons() {
		return personService.getAllPersons();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Person getOnePerson(@PathVariable("id") Long personId) {
		return personService.getOnePerson(personId);
	}

	@RequestMapping(method = RequestMethod.POST)
	public PersonDto createPerson(@RequestBody PersonDto personDto) {
		return personService.save(personDto);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public PersonDto updatePerson(@RequestBody PersonDto personDto, @PathVariable("id") Long personId) {
		return personService.update(personDto, personId);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long personId) {
		return personService.delete(personId);
	}

}
