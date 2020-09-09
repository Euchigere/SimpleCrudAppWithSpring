package dev.euchigere.facebookclonewithspring.repos;

import dev.euchigere.facebookclonewithspring.models.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface of comment model
 */
@Repository
public interface CommentRepo extends CrudRepository<Comment, Long> {
    // override the return type of the findAll method
    @Override
    List<Comment> findAll();

    // derived query to find comment by postId and commentId
    Optional<Comment> findCommentByPostAndId(Long postId, Long commentId);
}
