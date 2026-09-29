package com.mateus.bffagendador.business;


import com.mateus.bffagendador.business.dto.request.TarefasRequestDTO;
import com.mateus.bffagendador.business.dto.response.TarefasResponseDTO;
import com.mateus.bffagendador.infrastructure.Enum.StatusNotificacao;
import com.mateus.bffagendador.infrastructure.client.TarefasClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefasClient tarefasClient;

    public TarefasResponseDTO gravarTarefa(String token, TarefasRequestDTO dto){
        return tarefasClient.gravarTarefa(token, dto);

    }

    public List<TarefasResponseDTO> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal, String token){
        return tarefasClient.buscarListaTarefasPeriodo(dataInicial, dataFinal, token);
    }

    public List<TarefasResponseDTO> buscaTarefasEmail(String token){
        return tarefasClient.buscaTarefasPorEmail(token);

    }

    public void deletarTarefaPorId(String id, String token){
        tarefasClient.deletaTarefaPorId(id, token);

    }

    public TarefasResponseDTO alteraStatus(StatusNotificacao status, String id, String token){
        return tarefasClient.alteraStatusTarefa(status, id, token);

    }

    public TarefasResponseDTO updateTarefas(TarefasRequestDTO dto, String id, String token) {
        return tarefasClient.updateTarefas(dto, id, token);
    }
}
