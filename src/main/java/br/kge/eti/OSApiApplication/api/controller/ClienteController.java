    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.kge.eti.OSApiApplication.api.controller;

import br.kge.eti.OSApiApplication.domain.model.Cliente;
import br.kge.eti.OSApiApplication.domain.model.OrdemServico;
import br.kge.eti.OSApiApplication.domain.repository.ClienteRepository;
import br.kge.eti.OSApiApplication.domain.service.ClienteService;
import br.kge.eti.OSApiApplication.domain.service.OrdemServicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aluno
 */
@RestController
public class ClienteController {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private ClienteService clienteService;
    
    
    @GetMapping("/clientes")
    
@Operation(summary = "Buscar Clientes Existentes", description = "Retornar uma lista completa dos clientes")
@ApiResponses(value = {
 @ApiResponse(responseCode = "200", description = "Resgatado com Sucesso"),
 @ApiResponse(responseCode = "404", description = "Não foi possível resgatar o produto")}) 
    
    public ResponseEntity<List<Cliente>> buscarTodos() {
        List<Cliente> listaCliente = clienteRepository.findAll();
       
        if (!listaCliente.isEmpty()){
            return ResponseEntity.ok(listaCliente);
        }else{
            return ResponseEntity.notFound().build();
        }
    
    }
    
    @GetMapping("/clientes/{clienteID}")
    
    
    
@Operation(summary = "Buscar Clientes pelo ID", description = "Retorna os clientes pelo ID")
@ApiResponses(value = {
 @ApiResponse(responseCode = "200", description = "Resgatado com sucesso!"),
 @ApiResponse(responseCode = "404", description = "Não encontrado - O produto não foi encontrado")})
    public ResponseEntity<Cliente> buscar(@PathVariable long clienteID) {
        
        Optional<Cliente> cliente = clienteRepository.findById(clienteID);
        
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }else{
        return ResponseEntity.notFound().build();
        
        } 
    }
        
        
        @PostMapping("/clientes")
        
        
@Operation(summary = "Adcionar um Cliente", description = "Adciona um cliente")
@ApiResponses(value = {
 @ApiResponse(responseCode = "200", description = "Resgatado com Sucesso"),
 @ApiResponse(responseCode = "404", description = "Não Encontrado")})
        @ResponseStatus(HttpStatus.CREATED)
        
        public Cliente adicionar(@Valid @RequestBody Cliente cliente) { 
        
        return clienteService.salvar(cliente);
        
        }
        @PutMapping("/clientes{clienteID}")
        
@Operation(summary = "Atualizar um Cliente", description = "Atualiza o Status de um Cliente")
@ApiResponses(value = {
 @ApiResponse(responseCode = "200", description = "Resgatado com Sucesso"),
 @ApiResponse(responseCode = "404", description = "Não Encontrado")})
        public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteID,
                @RequestBody Cliente cliente) {
         
            if (!clienteRepository.existsById(clienteID)) {
                return ResponseEntity.notFound().build();
            }
            cliente.setId(clienteID);
            cliente = clienteService.salvar(cliente);
            return ResponseEntity.ok(cliente);
        }
        @DeleteMapping("/clientes/{clienteID}")
        
        
@Operation(summary = "Get a product by id", description = "Returns a product as per the id")
@ApiResponses(value = {
 @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
 @ApiResponse(responseCode = "404", description = "Not found - The product was not found")})
        
        public ResponseEntity<Void> excluir(@PathVariable Long clienteID) {
        
            if (!clienteRepository.existsById(clienteID)) {
                return ResponseEntity.notFound().build();
            }
            clienteService.excluir(clienteID);
            return ResponseEntity.noContent().build();
        }
                  
    }

  

        
        

        
    
    
 
   

