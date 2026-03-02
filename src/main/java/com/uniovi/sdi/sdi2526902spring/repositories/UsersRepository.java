package com.uniovi.sdi.sdi2526902spring.repositories;

import com.uniovi.sdi.sdi2526902spring.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UsersRepository extends JpaRepository<User, Long> {
    User findByDni(String dni);

    @Query("SELECT r FROM User r WHERE (LOWER(r.name) LIKE LOWER(CONCAT('%', :busqueda, '%')) " +
            "OR LOWER(r.lastName) LIKE LOWER(CONCAT('%', :busqueda, '%')))")
    Page<User> searchByNameAndLastName(Pageable pageable,
                                       @Param("busqueda") String searchText);

}
