package com.jpaappj3.jpa_app;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.CrudRepository;

import com.jpaappj3.jpa_app.entities.Person;
import com.jpaappj3.jpa_app.repositories.PersonRepository;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class JpaAppApplication implements CommandLineRunner{

	@Autowired
	private PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		// create();
		List<Person> persons = (List<Person>) personRepository.findByProgrammingLanguage(programmingLanguage: "Java");
		persons.stream().forEach(person -> System.out.println(person));

	}

	@Transactional
	public void create(){

		Scanner	scanner = new Scanner(System.in);
		System.out.println("Ingrese el nombre: ");
		String name = scanner.next();
		System.out.println("Ingrese el apellido: ");
		String lastname = scanner.next();
		System.out.println("Ingrese el lenguaje de programación: ");
		String programmingLanguage = scanner.next();
		scanner.close();

		Person person = new Person(null, name, lastname, programmingLanguage);

		CrudRepository<Person, Long> personRepository;
		Person personNew = personRepository.save(person);
		System.out.println(personNew);

		personRepository.findById(personNew.getId()).ifPresent(System.out::println);


	}

}
