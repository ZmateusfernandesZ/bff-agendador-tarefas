package com.mateus.bffagendador.controller;


import com.mateus.bffagendador.business.TarefaService;
import com.mateus.bffagendador.business.dto.request.TarefasRequestDTO;
import com.mateus.bffagendador.business.dto.response.TarefasResponseDTO;
import com.mateus.bffagendador.infrastructure.Enum.StatusNotificacao;
import com.mateus.bffagendador.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastro tarefas de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    @Operation(summary = "Salvar tarefa", description = "Cria uma nova tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa salva com sucesso!")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasResponseDTO>gravarTarefa(@RequestHeader(name = "Authorization", required = false) String token, @RequestBody TarefasRequestDTO dto){
        return ResponseEntity.ok(tarefaService.gravarTarefa(token, dto));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Busca lista de tarefas por periodo", description = "Busca tarefas cadastradas por periodo")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefasResponseDTO>> buscarListaTarefasPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
                                                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
                                                                              @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefaService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal, token));
    }

    @GetMapping
    @Operation(summary = "Busca tarefas por e-mail do usuário", description = "Busca lista de tarefas cadastradas por e-mail")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefasResponseDTO>> buscaTarefasPorEmail(@RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefaService.buscaTarefasEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Deleta tarefa pelo id", description = "Deleta tarefas pelo id")
    @ApiResponse(responseCode = "200", description = "Tarefas deletadas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id, @RequestHeader(name = "Authorization", required = false) String token){
        tarefaService.deletarTarefaPorId(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Altera o status da tarefa", description = "Altera o staus da tarefa")
    @ApiResponse(responseCode = "200", description = "Status da tarefa atualizado!")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasResponseDTO> alteraStatusTarefa(@RequestParam("status") StatusNotificacao status, @RequestParam("id")String id, @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefaService.alteraStatus(status, id, token));
    }

    @PutMapping
    @Operation(summary = "Altera os dados da tarefa", description = "Altera os dados da tarefa")
    @ApiResponse(responseCode = "200", description = "Dados da tarefa atualizado!")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasResponseDTO> updateTarefas(@RequestBody TarefasRequestDTO dto, @RequestParam("id") String id, @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefaService.updateTarefas(dto, id, token));
    }

}
