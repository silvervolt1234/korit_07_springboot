package com.shoppinglist.shoppinglist2.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

// Spring Data REST 에서 노출되지 않게
@RepositoryRestResource(exported = false)
public interface UserRepository extends JpaRepository<User, Long> {

    // UserDetailsService 에서 사용할 수 있게 추상 메서드 정의
    Optional<User> findByUsername(String username);
}
