package dev.euchigere.facebookclonewithspring.repos;

import dev.euchigere.facebookclonewithspring.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    List<User> findUserByEmail(String email);
    List<User> findByEmailAndPassword(String email, String password);
}
