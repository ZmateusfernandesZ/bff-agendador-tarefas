package com.mateus.bffagendador.controller;
import com.mateus.bffagendador.business.UsuarioService;
import com.mateus.bffagendador.business.dto.request.EnderecoRequestDTO;
import com.mateus.bffagendador.business.dto.request.LoginRequestDTO;
import com.mateus.bffagendador.business.dto.request.TelefoneRequestDTO;
import com.mateus.bffagendador.business.dto.request.UsuarioRequestDTO;
import com.mateus.bffagendador.business.dto.response.EnderecoResponseDTO;
import com.mateus.bffagendador.business.dto.response.LoginResponseDTO;
import com.mateus.bffagendador.business.dto.response.TelefoneResponseDTO;
import com.mateus.bffagendador.business.dto.response.UsuarioResponseDTO;
import com.mateus.bffagendador.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "Cadastro e login de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UsuarioController {

    private final UsuarioService usuarioService;


    @PostMapping
    @Operation(summary = "Salvar usuários", description = "Cria um novo usuário")
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso!")
    @ApiResponse(responseCode = "400", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioResponseDTO> salvaUsuario(@RequestBody UsuarioRequestDTO usuarioRequestDTO){
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioRequestDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Login de usuário", description = "login do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso!")
    @ApiResponse(responseCode = "401", description = "Credencias inválidas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public String login(@RequestBody LoginRequestDTO loginRequestDTO){

        return usuarioService.loginUsuario(loginRequestDTO);
    }

    @GetMapping
    @Operation(summary = "Buscar dados de usuários por e-mail", description = "Buscar dados do usário")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioResponseDTO> buscaUsuarioPorEmail(@RequestParam("email") String email, @RequestHeader(name = "Authorization", required = false)String token){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));

    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deleta usuário por e-mail", description = "Deleta usuário")
    @ApiResponse(responseCode = "200", description = "Usuário deletado!")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletarUsuarioPorEmail(@PathVariable String email, @RequestHeader(name = "Authorization", required = false)String token){
        usuarioService.deletarUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualiza dados do usuário", description = "Atualiza dados do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso!")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioResponseDTO>atualizaDadoUsuario(@RequestBody UsuarioRequestDTO usuarioRequestDTO, @RequestHeader(name = "Authorization", required = false)String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, usuarioRequestDTO));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualiza endereço do usuário", description = "Atualiza endereço do usuário")
    @ApiResponse(responseCode = "200", description = "Dados de usuário atualizado com sucesso!")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoResponseDTO> atualizaEndereco(@RequestParam("id") Long id, @RequestBody EnderecoRequestDTO enderecoRequestDTO, @RequestHeader(name = "Authorization", required = false)String token){
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, enderecoRequestDTO, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualiza telefone do usuário", description = "Atualiza telefone do usuário")
    @ApiResponse(responseCode = "200", description = "Dados de usuário atualizado com sucesso!")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneResponseDTO> atualizaTelefone(@RequestParam("id") Long id, @RequestBody TelefoneRequestDTO telefoneRequestDTO, @RequestHeader(name = "Authorization", required = false)String token){
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, telefoneRequestDTO, token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Cadastra endereço do usuário", description = "Cadastra endereço do usuário")
    @ApiResponse(responseCode = "200", description = "Endereço do usuário cadastrado com sucesso!")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoResponseDTO> cadastraEndereco(@RequestBody EnderecoRequestDTO dto, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token, dto));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Cadastra telefone do usuário", description = "Cadastra telefone do usuário")
    @ApiResponse(responseCode = "200", description = "Telefone do usuário cadastrado com sucesso!")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneResponseDTO> cadastraTelefone(@RequestBody TelefoneRequestDTO dto, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token, dto));
    }

}