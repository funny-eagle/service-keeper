package org.nocoder.servicekeeper.application.service.impl;

import org.nocoder.servicekeeper.application.service.UserService;
import org.nocoder.servicekeeper.domain.modal.User;
import org.nocoder.servicekeeper.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public User getById(int id) {
        return userRepository.getById(id);
    }

    @Override
    public User getByUsernameAndPassword(String username, String password) {
        return userRepository.getByUsernameAndPassword(username, password);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(User user) {
        return userRepository.update(user);
    }
}
