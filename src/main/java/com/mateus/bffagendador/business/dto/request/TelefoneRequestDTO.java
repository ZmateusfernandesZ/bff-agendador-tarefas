package com.mateus.bffagendador.business.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelefoneRequestDTO {

    private String numero;
    private String ddd;

}
