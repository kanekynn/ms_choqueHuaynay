package com.example.mschoqueHuayanay.infraestructure.repository;

import com.example.mschoqueHuayanay.infraestructure.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {
}
