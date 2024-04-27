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
 * LicenceRepository
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public interface LicenceRepository extends JpaRepository<License, Long> {

    List<License> findAllByDeletedFalse();

    Optional<License> findByIdAndDeletedFalse(Long id);

    List<License> findAllByUser_Id(Long userId);

    @Query("    SELECT l " +
            "   FROM License l " +
            "   WHERE l.deleted = FALSE " +
            "       AND l.dateStart >= :dateStart AND l.dateEnd <= :dateEnd" +
            "       AND l.user.id IN :userIds")
    List<License> findAllByDates(@Param("userIds") List<Long> userIds
            , @Param("dateStart") Date dateStart, @Param("dateEnd") Date dateEnd);
}
