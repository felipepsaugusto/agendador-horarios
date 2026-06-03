package com.felipepsaugusto.agendadorhorarios.dto;

import java.time.LocalDateTime;

public record AgendamentoRequest(
        String servico,
        String profissional,
        LocalDateTime dataHoraAgendamento,
        String telefoneCliente,
        String nomeCliente
) {
}
