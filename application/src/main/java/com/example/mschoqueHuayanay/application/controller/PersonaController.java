package com.example.mschoqueHuayanay.application.controller;


import com.example.mschoqueHuayanay.domain.aggregates.dto.PersonaDTO;
import com.example.mschoqueHuayanay.domain.aggregates.request.RequestPersona;
import com.example.mschoqueHuayanay.domain.ports.in.PersonaServiceIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/persona")
@RequiredArgsConstructor
public class PersonaController {
    private final PersonaServiceIn personaServiceIn;

    @PostMapping
    public ResponseEntity<PersonaDTO> registrar(@RequestBody RequestPersona requestPersona){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personaServiceIn.crearPersonaIn(requestPersona));
    }


}
