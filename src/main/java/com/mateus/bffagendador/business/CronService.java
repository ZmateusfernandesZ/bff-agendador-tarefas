package com.mateus.bffagendador.business;

import com.mateus.bffagendador.business.dto.request.LoginRequestDTO;
import com.mateus.bffagendador.business.dto.response.LoginResponseDTO;
import com.mateus.bffagendador.business.dto.response.TarefasResponseDTO;
import com.mateus.bffagendador.infrastructure.Enum.StatusNotificacao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final TarefaService tarefaService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;

    @Value("${senha.email}")
    private String senha;

    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefasProximaHora () {
        String token = login(converterParaRequestDTO());

        log.info("TOKEN RECEBIDO:" + token);

        System.out.println("========== CRON ==========");
        log.info("Executando: " + LocalDateTime.now());


        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);
        LocalDateTime horaFuturaMaisCinco = LocalDateTime.now().plusHours(1).plusMinutes(5);

        List<TarefasResponseDTO> listaTarefas = tarefaService.buscaTarefasAgendadasPorPeriodo(horaFutura, horaFuturaMaisCinco, token);
        log.info("Busca inicial: " + horaFutura);
        log.info("Busca final: " + horaFuturaMaisCinco);
        log.info("Quantidade de tarefas: " + listaTarefas.size());

        listaTarefas.forEach(tarefa -> {
            log.info("TAREFA ENCONTRADA: " + tarefa.getId());
            log.info("EMAIL: " + tarefa.getEmailUsuario());
            log.info("DATA: " + tarefa.getDataEvento());
            emailService.enviaEmail(tarefa);
            log.info("EMAIL ENVIADO");

            tarefaService.alteraStatus(StatusNotificacao.NOTIFICADO, tarefa.getId(), token );
            log.info("Status Alterado!");
            });
    }

    public String login (LoginRequestDTO dto){
        return usuarioService.loginUsuario(dto);
    }

    public LoginRequestDTO converterParaRequestDTO(){
        return LoginRequestDTO.builder()
                .email(email)
                .senha(senha)
                .build();
    }
}
