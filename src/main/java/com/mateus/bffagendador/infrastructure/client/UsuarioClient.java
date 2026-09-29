package com.mateus.bffagendador.infrastructure.client;

import com.mateus.bffagendador.business.dto.request.EnderecoRequestDTO;
import com.mateus.bffagendador.business.dto.request.LoginRequestDTO;
import com.mateus.bffagendador.business.dto.request.TelefoneRequestDTO;
import com.mateus.bffagendador.business.dto.request.UsuarioRequestDTO;
import com.mateus.bffagendador.business.dto.response.EnderecoResponseDTO;
import com.mateus.bffagendador.business.dto.response.LoginResponseDTO;
import com.mateus.bffagendador.business.dto.response.TelefoneResponseDTO;
import com.mateus.bffagendador.business.dto.response.UsuarioResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}/")
public interface UsuarioClient {

    @GetMapping
    UsuarioResponseDTO buscaUsuarioPorEmail(@RequestParam("email") String email, @RequestHeader("Authorization") String token);

    @PostMapping
    UsuarioResponseDTO salvaUsuario(@RequestBody UsuarioRequestDTO usuarioRequestDTO);

    @PostMapping("/login")
    LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO);

    @DeleteMapping("/{email}")
    void deletarUsuarioPorEmail(@PathVariable String email, @RequestHeader("Authorization")String token);

    @PutMapping
    UsuarioResponseDTO atualizaDadoUsuario(@RequestBody UsuarioRequestDTO usuarioResponseDTO, @RequestHeader("Authorization")String token);

    @PutMapping("/endereco")
    EnderecoResponseDTO atualizaEndereco(@RequestParam("id") Long id, @RequestBody EnderecoRequestDTO enderecoResponseDTO, @RequestHeader("Authorization")String token);

    @PutMapping("/telefone")
    TelefoneResponseDTO atualizaTelefone(@RequestParam("id") Long id, @RequestBody TelefoneRequestDTO telefoneResponseDTO, @RequestHeader("Authorization")String token);

    @PostMapping("/endereco")
    EnderecoResponseDTO cadastraEndereco(@RequestBody EnderecoRequestDTO dto, @RequestHeader("Authorization") String token);

    @PostMapping("/telefone")
    TelefoneResponseDTO cadastraTelefone(@RequestBody TelefoneRequestDTO dto, @RequestHeader("Authorization") String token);


}
