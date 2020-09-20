package dev.euchigere.facebookclonewithspring.service.impl;

import dev.euchigere.facebookclonewithspring.models.User;
import dev.euchigere.facebookclonewithspring.repos.UserRepo;
import dev.euchigere.facebookclonewithspring.service.UserService;
import dev.euchigere.facebookclonewithspring.dto.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;

    public void saveUserData(User user) {
        userRepo.save(user);
    }

    public List<User> findUserByEmail(String email) {
        return userRepo.findUserByEmail(email);
    }

    public List<User> findUserByEmailAndPassword(String email, String password) {
       return userRepo.findByEmailAndPassword(email, password);
    }

    public User findUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    public ResponseObject<User> authenticateUser(String email, String password) {
        ResponseObject<User> responseObject = new ResponseObject<>();
        List<User> users = findUserByEmailAndPassword(email, password);
        if (users == null || users.isEmpty()) {
            responseObject.setStatus(false);
            responseObject.setMessage("Invalid email and or password");
            return responseObject;
        }
        responseObject.setStatus(true);
        responseObject.setObject(users.get(0));
        return responseObject;
    }

    public ResponseObject<User> validateUser(String email) {
        ResponseObject<User> responseObject = new ResponseObject<>();
        List<User> users = findUserByEmail(email);

        if (!(users == null || users.isEmpty())) {
            responseObject.setStatus(false);
            responseObject.setMessage("Email already in use");
            return responseObject;
        }
        responseObject.setStatus(true);
        responseObject.setMessage("Sign up successful!\nkindly login with email and password");
        return responseObject;
    }
}
