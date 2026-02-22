package com.uniovi.sdi.sdi2526902spring.repositories;

import com.uniovi.sdi.sdi2526902spring.entities.*;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Long> {
    User findByDni(String dni);
}
