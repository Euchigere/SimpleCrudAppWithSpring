package dev.euchigere.facebookclonewithspring.repos;

import dev.euchigere.facebookclonewithspring.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface of user model
 */
@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    // derived query to find user by email
    List<User> findUserByEmail(String email);

    // derived query to find user by email and password
    List<User> findByEmailAndPassword(String email, String password);
}
