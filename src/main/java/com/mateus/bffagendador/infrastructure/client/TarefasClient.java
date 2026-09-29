package com.mateus.bffagendador.infrastructure.client;

import com.mateus.bffagendador.business.dto.request.TarefasRequestDTO;
import com.mateus.bffagendador.business.dto.response.TarefasResponseDTO;
import com.mateus.bffagendador.infrastructure.Enum.StatusNotificacao;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefas", url = "${agendador-tarefas.url}/")
public interface TarefasClient {

    @PostMapping
    TarefasResponseDTO gravarTarefa(@RequestHeader("Authorization") String token, @RequestBody TarefasRequestDTO dto);

    @GetMapping("/eventos")
    List<TarefasResponseDTO> buscarListaTarefasPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
                                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
                                                       @RequestHeader("Authorization") String token);
    @GetMapping
    List<TarefasResponseDTO> buscaTarefasPorEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping
    void deletaTarefaPorId(@RequestParam("id") String id, @RequestHeader("Authorization") String token);

    @PatchMapping
    TarefasResponseDTO alteraStatusTarefa(@RequestParam("status") StatusNotificacao status, @RequestParam("id")String id, @RequestHeader("Authorization") String token);

    @PutMapping
    TarefasResponseDTO updateTarefas(@RequestBody TarefasRequestDTO dto, @RequestParam("id") String id, @RequestHeader("Authorization") String token);


}
