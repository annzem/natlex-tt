package com.github.annzem.natlex_rest.repository;

import com.github.annzem.natlex_rest.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobRepository extends JpaRepository <Job, Long> {
    Optional<Job> findById (Long id);
}
