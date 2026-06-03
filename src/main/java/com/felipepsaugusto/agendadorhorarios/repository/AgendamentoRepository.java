package com.felipepsaugusto.agendadorhorarios.repository;

import com.felipepsaugusto.agendadorhorarios.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findByDataHoraAgendamentoBetween(LocalDateTime dataInicio, LocalDateTime dataFim);
    void deleteByDataHoraAgendamento(LocalDateTime data);
    Agendamento findByDataHoraAgendamentoAndNomeCliente(LocalDateTime dataHoraAgendamento, String nomeCliente);
    Agendamento findByDataHoraAgendamento(LocalDateTime dataHora);
}
