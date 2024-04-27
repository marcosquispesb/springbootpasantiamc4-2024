package com.example.springbootdemo.repository;

import com.example.springbootdemo.model.RegisterDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * RegisterDayRepository
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public interface RegisterDayRepository extends JpaRepository<RegisterDay, Long> {

    List<RegisterDay> findAllByDeletedFalse();

    Optional<RegisterDay> findByIdAndDeletedFalse(Long id);

    List<RegisterDay> findAllByUser_IdOrderByIdAsc(Long userId);

    List<RegisterDay> findAllByUser_IdIn(List<Long> userIds);

}
