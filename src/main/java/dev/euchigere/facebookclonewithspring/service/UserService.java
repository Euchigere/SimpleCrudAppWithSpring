package dev.euchigere.facebookclonewithspring.service;

import dev.euchigere.facebookclonewithspring.models.User;
import dev.euchigere.facebookclonewithspring.dto.ResponseObject;

import java.util.List;

public interface UserService {
    void saveUserData(User user);
    List<User> findUserByEmail(String email);
    List<User> findUserByEmailAndPassword(String email, String password);

    User findUserById(Long id);

    ResponseObject<User> authenticateUser(String email, String password);

    ResponseObject<User> validateUser(String email);
}
