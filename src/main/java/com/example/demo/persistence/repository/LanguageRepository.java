package com.example.demo.persistence.repository;

import com.example.demo.data.entity.Language;
import com.example.demo.data.enums.ELanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


public interface LanguageRepository extends JpaRepository<Language, UUID> {

    Optional<Language> findByName(ELanguage language);
}
