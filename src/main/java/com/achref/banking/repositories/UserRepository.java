package com.achref.banking.repositories;

import com.achref.banking.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAllByFirstname(String firstname);

    List<User> findAllByFirstnameContaining(String firstname);

    List<User> findAllByFirstnameContainingIgnoreCase(String firstname);

    List<User> findAllByAccountIban(String iban);

    User findByFirstnameContainingIgnoreCaseAndEmail(String firstname, String email);

    @Query("from User where firstname = :fn")
    List<User> searchByFirstname (@Param("fn") String firstname);

    @Query("from User where firstname = '%firstname%'")
    List<User> searchByFirstnameContaining(String firstname);

   @Query("from User u inner join Account a on u.id = a.user.id where a.iban = :iban")
    List<User> searchByIban (String iban);

   @Query(value = "select * from _user u inner join account a  on u.id = a.id_user and a.iban = :iban", nativeQuery = true)
   List<User> searchByIbanNative(String iban);

    Optional<User> findByEmail(String email);
}
