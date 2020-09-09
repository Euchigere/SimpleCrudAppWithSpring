package dev.euchigere.facebookclonewithspring.repos;

import dev.euchigere.facebookclonewithspring.models.CommentLike;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CommentLikeRepo extends CrudRepository<CommentLike, Long> {
    @Query(value = "SELECT comment_id FROM comment_like c WHERE c.user_id = ?", nativeQuery = true)
    List<Long> findAllCommentLikeIdByUserId(Long userId);

    @Modifying
    @Query(value = "delete from comment_like c where c.user_id=? and c.comment_id=?", nativeQuery = true)
    @Transactional
    void deleteByUserAndCommentId(Long userId, Long commentId);
}
