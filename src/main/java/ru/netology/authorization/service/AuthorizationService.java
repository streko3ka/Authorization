package ru.netology.authorization.service;

import org.springframework.stereotype.Service;
import ru.netology.authorization.Authorities;
import ru.netology.authorization.InvalidCredentials;
import ru.netology.authorization.UserRepository;
import ru.netology.authorization.UnauthorizedUser;

import java.util.List;

@Service
public class AuthorizationService {

    UserRepository userRepository;

    public List<Authorities> getAuthorities(String user, String password) {
        if (isEmpty(user) || isEmpty(password)) {
            throw new InvalidCredentials("User name or password is empty");
        }
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(user, password);
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("Unknown user " + user);
        }
        return userAuthorities;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> str) {
        return str == null || str.isEmpty();
    }
}