package com.tdg.mur.repos;

import com.tdg.mur.model.Comment;
import com.tdg.mur.model.Post;
import com.tdg.mur.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
 
import java.util.List;
 
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
 
    List<Comment> findAllByUser(User user);
}