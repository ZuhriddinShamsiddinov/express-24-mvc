package uz.jl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.jl.domains.Uploads;

import java.util.Optional;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 12:55 PM 8/4/22 on Thursday in August
 */
public interface UploadsRepository extends JpaRepository<Uploads, Long> {
    Optional<Uploads> findByGeneratedName(String generatedName);
}
