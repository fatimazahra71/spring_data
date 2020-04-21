package com.master4.services;

import com.master4.entities.User;
import com.master4.exceptions.ResourceNotFoundException;
import com.master4.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Optional;

@Service
public  class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public Page<User> getAllUsers(Optional<Integer> pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(0, pageSize, Sort.by(sortBy));
        if (pageNo.isPresent()) {
            paging = PageRequest.of(pageNo.get(), pageSize, Sort.by(sortBy));
        }
        return userRepository.findAll(paging);
    }

    @Override
    @Transactional
    public User findById(long id) throws ResourceNotFoundException
    {
        return userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id));
    }

    @Override
    @Transactional
    public void save(User user){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        user.setCreated(timestamp);
        userRepository.save(user);

    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public User findByIdWithArticles(long id) {
        return null;
    }


    @Transactional
    public void deletedById(long id){
        userRepository.deleteById(id);
    }
}
