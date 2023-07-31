package com.atorres.nttdata.accountmsf.controller;

import com.atorres.nttdata.accountmsf.client.FeignApiClient;
import com.atorres.nttdata.accountmsf.model.RequestAccount;
import com.atorres.nttdata.accountmsf.model.RequestUpdateAccount;
import com.atorres.nttdata.accountmsf.model.clientms.ClientDto;
import com.atorres.nttdata.accountmsf.model.dto.AccountDto;
import com.atorres.nttdata.accountmsf.service.AccountService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/account")
@Slf4j
public class AccountController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private FeignApiClient feignApiClient;

	/**
	 * Metodo prueba para testear Resilience4J
	 * @return clientes
	 */
	@CircuitBreaker(name = "resilienceAlternativo" , fallbackMethod = "metodoAlternativo")
	@GetMapping(value = "/clientes",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<ClientDto> getClientes(){
		return feignApiClient.getAllClients()
				.doOnNext(account -> log.info("Cliente encontrado"));
	}

	/**
	 * Metodo para traer la cuenta
	 * @param productId id producto
	 * @return cuenta
	 */
	@GetMapping(value = "/{productId}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Mono<AccountDto> getAccount(
					@PathVariable String productId){
		return accountService.getAccount(productId)
						.doOnNext(account -> log.info("Cuenta encontrada con exito"));
	}

	/**
	 * Endpoint para obtener todas las cuentas de un cliente
	 * @param id id del cliente
	 * @return devuelve una lista de cuentas
	 */
	@CircuitBreaker(name = "resilienceAlternativo" , fallbackMethod = "metodoAlternativo")
	@GetMapping(value = "/client/{id}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<AccountDto> getAllAccountClient(@PathVariable String id){
		return accountService.getAllAccountsByClient(id)
						.doOnNext(account -> log.info("Cuenta encontrada: "+account.getId()));
	}

	/**
	 * Endpoint para crear una cuenta para un cliente por su id y un RequestAccount
	 * @param id id del cliente
	 * @param requestAccount request con los datos de la cuenta
	 * @return retorna la entidad relacion client-product
	 */
	@PostMapping(value = "/client/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Mono<AccountDto> createAccount(@PathVariable String id, @RequestBody Mono<RequestAccount> requestAccount){
		return requestAccount.flatMap(account -> accountService.createAccount(id,account)
						.doOnSuccess(v -> log.info("Cuenta creada con exito")));
	}
	/**
	 * Metodo para eliminar una cuenta
	 * @param clientId request
	 * @return void
	 */
	@DeleteMapping(value ="/delete/{clientId}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Mono<Void> deleteAccount(@PathVariable String clientId){
		return accountService.delete(clientId)
						.doOnNext(v -> log.info("Cuenta eliminada con exito"));
	}

	/**
	 * Metodo para actualizar el balance de la cuenta
	 * @param request request
	 * @return AccountDao
	 */
	@PutMapping(value="/update",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Mono<AccountDto> updateAccount(@RequestBody Mono<RequestUpdateAccount> request){
		return request.flatMap(account -> accountService.update(account)
						.doOnSuccess(v -> log.info("Cuenta actualizada con exito")));
	}

	/**
	 * Circuit Breaker metodo alternativo
	 * @param throwable excepcion
	 * @return ClientDtos
	 */
	private Flux<ClientDto> metodoAlternativo(Throwable throwable) {
		log.error("Error en la llamada a feignApiClient.getAllClients(): " + throwable.getMessage());
		// Puedes devolver una respuesta de fallback, por ejemplo, una lista vacía o un flujo con algunos datos predeterminados
		return Flux.empty();
	}
}
