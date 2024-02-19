package com.example.mschoqueHuayanay.domain.impl;

import com.example.mschoqueHuayanay.domain.aggregates.dto.PersonaDTO;
import com.example.mschoqueHuayanay.domain.aggregates.dto.TipoDocumentoDTO;
import com.example.mschoqueHuayanay.domain.aggregates.dto.TipoPersonaDTO;
import com.example.mschoqueHuayanay.domain.aggregates.request.RequestPersona;
import com.example.mschoqueHuayanay.domain.aggregates.response.ResponseReniec;
import com.example.mschoqueHuayanay.domain.ports.in.PersonaServiceIn;
import com.example.mschoqueHuayanay.domain.ports.out.PersonaServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

@RequiredArgsConstructor

public class PersonaServiceImpl  implements PersonaServiceIn {
    private final PersonaServiceOut personaServiceOut;
    private final TipoDocumentoDTO tipoDocumentoDTO;
    private final TipoPersonaDTO tipoPersonaDTO;
    private final ResponseReniec responseReniec;


    @Override
    public PersonaDTO crearPersonaIn(RequestPersona requestPersona) {
        TipoDocumentoDTO tipoDocumento = tipoDocumentoDTO;
        tipoDocumento.getCodTipo();
        TipoPersonaDTO tipoPersona = tipoPersonaDTO;
        tipoPersona.getCodTipo();
        requestPersona.setTipoDoc(tipoDocumento);
        requestPersona.setTipoPer(tipoPersona);

        return personaServiceOut.crearPersonaOut(requestPersona);
    }

    @Override
    public Optional<PersonaDTO> obtenerPersonaIn(Long id) {
        return personaServiceOut.obtenerPersonaOut(id);
    }

    @Override
    public List<PersonaDTO> obtenerTodosIn() {
        return personaServiceOut.obtenerTodosOut();
    }

    @Override
    public PersonaDTO actualizarIn(Long id, RequestPersona requestPersona) {
        return personaServiceOut.actualizarOut(id,requestPersona);
    }

    @Override
    public PersonaDTO deleteIn(Long id) {
        return personaServiceOut.deleteOut(id);
    }
}
