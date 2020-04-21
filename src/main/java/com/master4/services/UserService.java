package com.master4.services;

import com.master4.entities.User;
import com.master4.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserService {
    public Page<User> getAllUsers(Optional<Integer> pageNo, Integer pageSize, String sortBy);

    User findById(long id) throws ResourceNotFoundException;

    void save(User user);

    void deleteById(long id);

    User findByIdWithArticles(@Param("id") long id);
}
