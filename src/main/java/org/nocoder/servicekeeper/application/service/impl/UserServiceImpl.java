package org.nocoder.servicekeeper.application.service.impl;

import org.nocoder.servicekeeper.application.service.UserService;
import org.nocoder.servicekeeper.domain.modal.User;
import org.nocoder.servicekeeper.infrastructure.repository.UserRepository;
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
