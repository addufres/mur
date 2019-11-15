package com.tdg.mur.repos;

import com.tdg.mur.model.Post;
import com.tdg.mur.model.Thread;
import com.tdg.mur.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
 
import java.util.List;
 
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByThread(Thread thread);
 
    List<Post> findByUser(User user);
}