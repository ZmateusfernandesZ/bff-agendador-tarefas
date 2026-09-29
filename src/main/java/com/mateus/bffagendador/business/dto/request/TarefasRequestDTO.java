package com.mateus.bffagendador.business.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mateus.bffagendador.infrastructure.Enum.StatusNotificacao;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefasRequestDTO {
    private String nomeTarefa;
    private String descricao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataEvento;
}
