package com.goit.javaonline5.user.dao;

import com.goit.javaonline5.user.dao.abstraction.UserDaoService;
import com.goit.javaonline5.user.model.UserModel;
import com.goit.javaonline5.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserDaoServiceImpl implements UserDaoService {

    private final UserRepository userRepository;

    @Override
    public UserModel save(UserModel entity) {
        return userRepository.save(entity);
    }

    @Override
    public List<UserModel> saveAll(List<UserModel> entities) {
        return userRepository.saveAll(entities);
    }

    @Override
    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserModel findById(final UUID id) {
        return userRepository.findById(id).orElse(new UserModel());
    }

    @Override
    public UserModel findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public UserModel updateById(UserModel entity, UUID id) {
        entity.setId(id);

        return userRepository.save(entity);
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }
}
