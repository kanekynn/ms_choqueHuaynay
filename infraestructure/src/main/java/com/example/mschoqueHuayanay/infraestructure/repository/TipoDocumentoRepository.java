package com.example.mschoqueHuayanay.infraestructure.repository;

import com.example.mschoqueHuayanay.infraestructure.entity.TipoDocumentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface TipoDocumentoRepository extends JpaRepository<TipoDocumentoEntity, Long> {
    TipoDocumentoEntity findByCodTipo(@Param("codTipo") String codTipo);
}
