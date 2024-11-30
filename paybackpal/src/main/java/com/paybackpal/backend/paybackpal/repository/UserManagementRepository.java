package com.paybackpal.backend.paybackpal.repository;

import com.paybackpal.backend.paybackpal.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserManagementRepository extends JpaRepository<UserDetails, Long> {
    UserDetails findByEmail(String email);
}
