package com.PractionBE.domain.repository;

import com.PractionBE.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
