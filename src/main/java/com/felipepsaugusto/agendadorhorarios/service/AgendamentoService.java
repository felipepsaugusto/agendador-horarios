package com.felipepsaugusto.agendadorhorarios.service;

import com.felipepsaugusto.agendadorhorarios.entity.Agendamento;
import com.felipepsaugusto.agendadorhorarios.exception.AgendamentoException;
import com.felipepsaugusto.agendadorhorarios.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public List<Agendamento> buscarAgendamentosPorDia(LocalDate data){
        if(data == null){
            throw new AgendamentoException("Data não pode ser nula");
        }
        LocalDateTime dataInicio = data.atStartOfDay();
        LocalDateTime dataFim = data.atTime(23,59, 59);
        List<Agendamento> agendamentoList = agendamentoRepository.findByDataHoraAgendamentoBetween(dataInicio, dataFim);
        if(!agendamentoList.isEmpty()){
            return agendamentoList;
        }else{
            throw new AgendamentoException("Não existe agendamentos nessa data: " + data);
        }
    }

    @Transactional
    public Agendamento criarAgendamento(Agendamento agendamento){
        if(agendamento == null || agendamento.getDataHoraAgendamento() == null){
            throw new AgendamentoException("Dados do agendamento não podem ser nulos");
        }
        List<Agendamento> buscaAgendamento = agendamentoRepository.findByDataHoraAgendamentoBetween
                (agendamento.getDataHoraAgendamento(), agendamento.getDataHoraAgendamento().plusMinutes(30));// pausa de 30 minutos entre um serviço e outro
        if(buscaAgendamento.isEmpty()){
            return agendamentoRepository.save(agendamento);
        }else{
            throw new AgendamentoException("Já existe um agendamento nesse horario");
        }
    }

    @Transactional
    public void apagarAgendamento(LocalDateTime data){
        if(data == null){
            throw new AgendamentoException("Data não pode ser nula");
        }
        Agendamento buscaAgendamento = agendamentoRepository.findByDataHoraAgendamento(data);
        if(Objects.nonNull(buscaAgendamento)){
            agendamentoRepository.deleteByDataHoraAgendamento(data);
        }else{
            throw new AgendamentoException("Não existe um agendamento nesse horario");
        }
    }

    @Transactional
    public Agendamento alterarAgendamento(LocalDateTime dataExistente, String nomeCliente, Agendamento agendamento){
        if(dataExistente == null || nomeCliente == null || agendamento == null){
            throw new AgendamentoException("Todos os parametros devem ser preenchidos");
        }
        Agendamento buscaAgendamento = agendamentoRepository.findByDataHoraAgendamentoAndNomeCliente(dataExistente, nomeCliente);
        if (Objects.nonNull(buscaAgendamento)){
            agendamento.setId(buscaAgendamento.getId());
            return agendamentoRepository.save(agendamento);
        }else{
            throw new AgendamentoException("Não existe um agendamento com esse horario ou esse cliente");
        }
    }

}
