package com.neel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neel.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
