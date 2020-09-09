package dev.euchigere.facebookclonewithspring.repos;

import dev.euchigere.facebookclonewithspring.models.PostLike;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PostLikeRepo extends CrudRepository<PostLike, Long> {
    @Query(value = "SELECT post_id FROM post_like p WHERE p.user_id = ?", nativeQuery = true)
    List<Long> findAllPostLikeIdByUserId(Long userId);

    @Modifying
    @Query(value = "delete from post_like p where p.user_id=? and p.post_id=?", nativeQuery = true)
    @Transactional
    void deleteByUserAndPostId(Long userId, Long postId);
}
