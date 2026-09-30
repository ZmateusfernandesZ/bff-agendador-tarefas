package com.mateus.bffagendador.infrastructure.client;

import com.mateus.bffagendador.business.dto.request.TarefasRequestDTO;
import com.mateus.bffagendador.business.dto.response.TarefasResponseDTO;
import com.mateus.bffagendador.infrastructure.Enum.StatusNotificacao;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "notificacao", url = "${notificacao.url}/")
public interface EmailClient {

    @PostMapping
    void enviarEmail(@RequestBody TarefasResponseDTO dto);


}
