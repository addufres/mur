package com.tdg.mur.repos;

import com.tdg.mur.model.Post;
import com.tdg.mur.model.User;
import com.tdg.mur.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
 
import java.util.Optional;
 
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}