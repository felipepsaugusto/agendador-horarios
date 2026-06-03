package com.felipepsaugusto.agendadorhorarios.controller;

import com.felipepsaugusto.agendadorhorarios.mapper.AgendamentoMapper;
import com.felipepsaugusto.agendadorhorarios.dto.AgendamentoRequest;
import com.felipepsaugusto.agendadorhorarios.dto.AgendamentoResponse;
import com.felipepsaugusto.agendadorhorarios.entity.Agendamento;
import com.felipepsaugusto.agendadorhorarios.service.AgendamentoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private AgendamentoMapper agendamentoMapper;

    @GetMapping
    public ResponseEntity<List<AgendamentoResponse>> buscarAgendamentosPorDia(@RequestParam @Valid @NotNull LocalDate data){
        List<Agendamento> agendamentos = agendamentoService.buscarAgendamentosPorDia(data);
        List<AgendamentoResponse> agendamentoResponses = agendamentos.stream().map(agendamentoMapper::toResponse).toList();
        return ResponseEntity.ok(agendamentoResponses);
    }

    @PostMapping
    public ResponseEntity<AgendamentoResponse> criarAgendamento(@RequestBody @Valid AgendamentoRequest request){
        Agendamento agendamento = agendamentoMapper.toAgendamento(request);
        AgendamentoResponse response = agendamentoMapper.toResponse(agendamentoService.criarAgendamento(agendamento));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> apagarAgendamentoPorDataEHorario(@RequestParam @Valid @NotNull LocalDateTime dataHora){
        agendamentoService.apagarAgendamento(dataHora);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<AgendamentoResponse> alterarAgendamento(@RequestParam @Valid @NotNull LocalDateTime dataExistente,
                                                                  @RequestParam @Valid @NotBlank String cliente, @RequestBody @Valid @NotNull AgendamentoRequest request){
         Agendamento agendamentoAlterado = agendamentoService.alterarAgendamento(dataExistente, cliente, agendamentoMapper.toAgendamento(request));
         return ResponseEntity.accepted().body(agendamentoMapper.toResponse(agendamentoAlterado));
    }
}
