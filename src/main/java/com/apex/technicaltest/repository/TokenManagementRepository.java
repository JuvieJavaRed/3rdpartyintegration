package com.apex.technicaltest.repository;

import com.apex.technicaltest.entities.TokenManagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenManagementRepository extends JpaRepository<TokenManagement, String> {
}
