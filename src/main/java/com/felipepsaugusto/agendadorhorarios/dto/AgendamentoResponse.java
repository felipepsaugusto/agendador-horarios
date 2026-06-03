package com.felipepsaugusto.agendadorhorarios.dto;

import java.time.LocalDateTime;

public record AgendamentoResponse(
        Long id,
        String servico,
        String profissional,
        LocalDateTime dataHoraAgendamento,
        String telefoneCliente,
        String nomeCliente,
        LocalDateTime dataHoraInsercao
) {
}
