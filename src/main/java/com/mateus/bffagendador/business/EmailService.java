package com.mateus.bffagendador.business;


import com.mateus.bffagendador.business.dto.response.*;
import com.mateus.bffagendador.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final EmailClient emailClient;


    public void enviaEmail (TarefasResponseDTO dto){
        emailClient.enviarEmail(dto);

    }








}
