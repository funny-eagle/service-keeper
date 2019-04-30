package org.nocoder.servicekeeper.application.service;

import org.nocoder.servicekeeper.domain.modal.User;

public interface UserService {

    User getById(int id);

    User getByUsernameAndPassword(String username, String password);

    int update(User user);
}
