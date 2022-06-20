package se.lexicon.springdatajpa.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.springdatajpa.entity.AppUser;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {

    Optional<AppUser> findByEmail(String email);

    Optional<AppUser> findByEmailIgnoreCase(String email);

    List<AppUser> findByFirstNameAndLastName(String firstName, String lastName);

    List<AppUser> findByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);


}
