package dev.euchigere.facebookclonewithspring.repos;

import dev.euchigere.facebookclonewithspring.models.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepo extends CrudRepository<Comment, Long> {
    @Override
    List<Comment> findAll();

    Optional<Comment> findCommentByPostAndId(Long postId, Long commentId);
}
