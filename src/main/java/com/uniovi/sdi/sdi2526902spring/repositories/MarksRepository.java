package com.uniovi.sdi.sdi2526902spring.repositories;

import com.uniovi.sdi.sdi2526902spring.entities.Mark;
import com.uniovi.sdi.sdi2526902spring.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MarksRepository extends CrudRepository<Mark, Long> {
    Page<Mark> findAllByUserOrderByIdAsc(Pageable pageable,User user);

    @Modifying
    @Query("UPDATE Mark m SET m.resend = :resend WHERE m.id = :id")
    void updateResend(@Param("resend") Boolean resend, @Param("id") Long id);

    @Query("SELECT r FROM Mark r WHERE (LOWER(r.description) LIKE LOWER(CONCAT('%', :busqueda, '%')) OR LOWER(r.user.name) LIKE LOWER(CONCAT('%', :busqueda, '%')))")
    Page<Mark> searchByDescriptionAndName(Pageable pageable,
                                          @Param("busqueda") String searchText);

    @Query("SELECT r FROM Mark r WHERE (LOWER(r.description) LIKE LOWER(CONCAT('%', :busqueda, '%')) OR LOWER(r.user.name) LIKE LOWER(CONCAT('%', :busqueda, '%'))) AND r.user = :user")
    Page<Mark> searchByDescriptionNameAndUser(Pageable pageable,
                                              @Param("busqueda") String searchText,
                                              @Param("usuario") User user);

    Page<Mark> findAll(Pageable pageable);
}
