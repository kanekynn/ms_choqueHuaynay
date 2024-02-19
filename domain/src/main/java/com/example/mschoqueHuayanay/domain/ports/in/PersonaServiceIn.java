package com.example.mschoqueHuayanay.domain.ports.in;

import com.example.mschoqueHuayanay.domain.aggregates.dto.PersonaDTO;
import com.example.mschoqueHuayanay.domain.aggregates.dto.TipoDocumentoDTO;
import com.example.mschoqueHuayanay.domain.aggregates.dto.TipoPersonaDTO;
import com.example.mschoqueHuayanay.domain.aggregates.request.RequestPersona;

import java.util.List;
import java.util.Optional;

public interface PersonaServiceIn {

    PersonaDTO crearPersonaIn(RequestPersona requestPersona);


    Optional<PersonaDTO> obtenerPersonaIn(Long id);

    List<PersonaDTO> obtenerTodosIn();

    PersonaDTO actualizarIn(Long id, RequestPersona requestPersona);

    PersonaDTO deleteIn(Long id);

}
