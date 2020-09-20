package dev.euchigere.facebookclonewithspring.repos;

import dev.euchigere.facebookclonewithspring.models.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository interface of post model
 */
@Repository
public interface PostRepo extends CrudRepository<Post, Long> {
    // override the return type of the findAll method
    @Override
    List<Post> findAll();
}
