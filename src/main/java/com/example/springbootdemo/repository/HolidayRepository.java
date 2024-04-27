package com.example.springbootdemo.repository;

import com.example.springbootdemo.model.Holiday;
import com.example.springbootdemo.model.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * HolidayRepository
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public interface HolidayRepository extends JpaRepository<Holiday, Long> {

    List<Holiday> findAllByDeletedFalse();

    Optional<Holiday> findByIdAndDeletedFalse(Long id);

    @Query("    SELECT h " +
            "   FROM Holiday h " +
            "   WHERE h.deleted = FALSE " +
            "       AND h.date BETWEEN :dateStart AND :dateEnd")
    List<Holiday> findAllByDates(@Param("dateStart") Date dateStart, @Param("dateEnd") Date dateEnd);
}
