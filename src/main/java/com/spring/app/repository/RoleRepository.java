package com.spring.app.repository;

import com.spring.app.repository.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {
    Role findByUserId(Long userId);

    Page<Role> findByNameContainingAndFlagIgnoreCase(String name, String flag, Pageable pageable);
}
