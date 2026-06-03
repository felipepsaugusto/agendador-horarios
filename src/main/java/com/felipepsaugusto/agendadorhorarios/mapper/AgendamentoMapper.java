package com.felipepsaugusto.agendadorhorarios.mapper;

import com.felipepsaugusto.agendadorhorarios.dto.AgendamentoRequest;
import com.felipepsaugusto.agendadorhorarios.dto.AgendamentoResponse;
import com.felipepsaugusto.agendadorhorarios.entity.Agendamento;
import org.springframework.stereotype.Component;

@Component
public class AgendamentoMapper {

    public Agendamento toAgendamento(AgendamentoRequest request){
        return new Agendamento(request.servico(),
                request.profissional(), request.dataHoraAgendamento(), request.telefoneCliente(), request.nomeCliente());
    }

    public AgendamentoResponse toResponse(Agendamento agendamento){
        return new AgendamentoResponse(agendamento.getId(), agendamento.getServico(), agendamento.getProfissional(),
                agendamento.getDataHoraAgendamento(), agendamento.getTelefoneCliente(), agendamento.getNomeCliente(), agendamento.getDataHoraInsercao());
    }
}
