package com.MobileProgramming.repository;

import com.MobileProgramming.domain.User;
import com.MobileProgramming.repository.JPA.JPAUserRepository;
import com.MobileProgramming.repository.JPA.JPAUserRepositoryImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class BaseRepositoryImpl {
    JPAUserRepository jpaUserRepository;

    public BaseRepositoryImpl(JPAUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }



    public void saveUser(User user) {
        jpaUserRepository.saveUser(user);
    }
}
