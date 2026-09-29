package com.mateus.bffagendador.business.dto.request;

import com.mateus.bffagendador.business.dto.response.EnderecoResponseDTO;
import com.mateus.bffagendador.business.dto.response.TelefoneResponseDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioRequestDTO {

    private String nome;
    private String email;
    private String senha;
    private List<EnderecoResponseDTO> enderecos;
    private List<TelefoneResponseDTO> telefones;
}
