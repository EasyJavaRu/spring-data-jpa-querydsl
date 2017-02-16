package ru.easyjava.spring.data.jpa.entity;

import com.querydsl.core.types.Predicate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.easyjava.spring.data.jpa.dal.PassportRepository;
import ru.easyjava.spring.data.jpa.dal.PersonRepository;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(classes = ru.easyjava.spring.data.jpa.ContextConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class DalIT {
    @Inject
    private PersonRepository personRepository;

    @Inject
    private PassportRepository passportRepository;

    @Before
    @Transactional
    public void setUp() throws Exception {
        Passport p = new Passport();
        p.setSeries("AS");
        p.setNo("123456");
        p.setIssueDate(LocalDate.now());
        p.setValidity(Period.ofYears(20));

        Address a = new Address();
        a.setCity("Kickapoo");
        a.setStreet("Main street");
        a.setBuilding("1");

        Person person = new Person();
        person.setFirstName("Test");
        person.setLastName("Testoff");
        person.setDob(LocalDate.now());
        person.setPrimaryAddress(a);
        person.setPassport(p);

        Company c = new Company();
        c.setName("Acme Ltd");

        p.setOwner(person);
        person.setWorkingPlaces(Collections.singletonList(c));

        personRepository.save(person);
    }

    @Test
    @Transactional
    public void testGreeter() {
        QPerson qPerson = QPerson.person;
        Predicate personTestQuery = qPerson.firstName.eq("Test");

        personRepository.findAll(personTestQuery)
                .forEach(System.out::println);

        assertFalse(personRepository.exists(qPerson.firstName.endsWith("fail")));

        QPassport qPassport = QPassport.passport;
        Predicate passportSeriesQuery = qPassport.series.contains("A");
        Predicate passportPersonQuery = qPassport.owner.lastName.startsWith("Te").and(passportSeriesQuery);

        passportRepository.findAll(passportPersonQuery)
                .forEach(System.out::println);
    }
}
