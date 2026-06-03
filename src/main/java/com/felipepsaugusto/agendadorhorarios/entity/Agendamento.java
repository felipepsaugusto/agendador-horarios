package com.felipepsaugusto.agendadorhorarios.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_agendamentos")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Length(min = 5, max = 20)
    private String servico;
    @NotBlank
    private String profissional;
    @NotNull
    private LocalDateTime dataHoraAgendamento;
    @Column(name = "telefone_cliente", nullable = false)
    private String telefoneCliente;
    @Column(name = "nome_cliente", nullable = false)
    private String nomeCliente;
    @Column(name = "datahora_insercao")
    @CreationTimestamp
    private LocalDateTime dataHoraInsercao = LocalDateTime.now();

    public Agendamento(String servico, String profissional, LocalDateTime dataHoraAgendamento, String telefoneCliente, String nomeCliente) {
        this.servico = servico;
        this.profissional = profissional;
        this.dataHoraAgendamento = dataHoraAgendamento;
        this.telefoneCliente = telefoneCliente;
        this.nomeCliente = nomeCliente;
    }
}
