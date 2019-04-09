package org.nocoder.servicekeeper.service.impl;

import org.nocoder.servicekeeper.modal.User;
import org.nocoder.servicekeeper.repository.UserRepository;
import org.nocoder.servicekeeper.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public User getById(int id) {
        return userRepository.getById(id);
    }
}
