package com.example.mschoqueHuayanay.infraestructure.adapters;

import com.example.mschoqueHuayanay.domain.aggregates.constants.Constants;
import com.example.mschoqueHuayanay.domain.aggregates.dto.PersonaDTO;
import com.example.mschoqueHuayanay.domain.aggregates.request.RequestPersona;
import com.example.mschoqueHuayanay.domain.aggregates.response.ResponseReniec;
import com.example.mschoqueHuayanay.domain.ports.out.PersonaServiceOut;
import com.example.mschoqueHuayanay.infraestructure.entity.PersonaEntity;
import com.example.mschoqueHuayanay.infraestructure.entity.TipoDocumentoEntity;
import com.example.mschoqueHuayanay.infraestructure.mapper.PersonaMapper;
import com.example.mschoqueHuayanay.infraestructure.repository.PersonaRepository;
import com.example.mschoqueHuayanay.infraestructure.repository.TipoDocumentoRepository;
import com.example.mschoqueHuayanay.infraestructure.rest.client.ClienteReniec;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@Service
@RequiredArgsConstructor
public class PersonaAdpter implements PersonaServiceOut {

    private final PersonaRepository personaRepository;
    private final TipoDocumentoRepository tipoDocumentoRepository;
    private final PersonaMapper personaMapper;
    private final ClienteReniec reniec;

    @Value("${token.api}")
    private String tokenApi;


    @Override
    public PersonaDTO crearPersonaOut(RequestPersona requestPersona) {

        ResponseReniec datosReniec = getExecutionReniec(requestPersona.getNumDoc());
        personaRepository.save(getEntity(datosReniec,requestPersona));
        return personaMapper.mapToDto(getEntity(datosReniec,requestPersona));
    }


    @Override
    public Optional<PersonaDTO> obtenerPersonaOut(Long id) {
        return Optional.empty();
    }

    @Override
    public List<PersonaDTO> obtenerTodosOut() {
        return Optional.ofNullable(personaMapper.mapToDto(
                personaRepository.findById(id).get()));
    }

    @Override
    public PersonaDTO actualizarOut(Long id, RequestPersona requestPersona) {
        return null;
    }

    @Override
    public PersonaDTO deleteOut(Long id) {
        return null;
    }



    public ResponseReniec getExecutionReniec(String numero){
        String authorization = "Bearer "+tokenApi;
        ResponseReniec responseReniec = reniec.getInfoReniec(numero,authorization);
        return  responseReniec;
    }



    private PersonaEntity getEntity(ResponseReniec reniec, RequestPersona requestPersona){
        TipoDocumentoEntity tipoDocumento = tipoDocumentoRepository.findByCodTipo(requestPersona.getTipoDoc());
        PersonaEntity entity = new PersonaEntity();
        entity.setNumDocu(reniec.getNumeroDocumento());
        entity.setNombres(reniec.getNombres());
        entity.setApePat(reniec.getApellidoPaterno());
        entity.setApeMat(reniec.getApellidoMaterno());
        entity.setEstado(Constants.STATUS_ACTIVE);
        entity.setUsuaCrea(Constants.AUDIT_ADMIN);
        entity.setDateCreate(getTimestamp());
        entity.setTipoDocumento(tipoDocumento);
        return entity;
    }



    private PersonaEntity getEntityUpdate(ResponseReniec reniec, PersonaEntity personaActualizar){
        personaActualizar.setNombres(reniec.getNombres());
        personaActualizar.setApePat(reniec.getApellidoPaterno());
        personaActualizar.setApeMat(reniec.getApellidoMaterno());
        personaActualizar.setUsuaModif(Constants.AUDIT_ADMIN);
        personaActualizar.setDateModif(getTimestamp());
        return personaActualizar;
    }



    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        return timestamp;
    }
}
