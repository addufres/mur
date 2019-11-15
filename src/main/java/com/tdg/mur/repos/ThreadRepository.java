package com.tdg.mur.repos;

import com.tdg.mur.model.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
 
import java.util.Optional;
 
public interface ThreadRepository extends JpaRepository<Thread, Long> {
    Optional<Thread> findByName(String ThreadName);
}