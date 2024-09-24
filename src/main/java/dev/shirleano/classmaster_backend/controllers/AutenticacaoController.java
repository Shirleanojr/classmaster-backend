package dev.shirleano.classmaster_backend.controllers;

import dev.shirleano.classmaster_backend.dto.auth.AutenticacaoDto;
import dev.shirleano.classmaster_backend.dto.auth.RespostaTokenDto;
import dev.shirleano.classmaster_backend.services.auth.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sign-in")
public class AutenticacaoController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping
    public ResponseEntity realizarLogin(@RequestBody AutenticacaoDto dados) {
        RespostaTokenDto token = autenticacaoService.realizarLogin(dados);
        return ResponseEntity.ok().body(token);
    }
}
