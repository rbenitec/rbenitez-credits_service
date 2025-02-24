package service.credits.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import service.credits.model.dto.CreditsRequestDto;
import service.credits.model.dto.CreditsResponseDto;
import service.credits.model.dto.DeleteResponseDto;
import service.credits.model.dto.UpdateCreditRequestDto;
import service.credits.service.CreditsService;

@RestController
@RequestMapping("/credits")
@RequiredArgsConstructor
public class CreditsController {

    private final CreditsService creditsService;

    @PostMapping()  // Save credits
    public Mono<ResponseEntity<CreditsResponseDto>> saveCredit(@RequestBody Mono<CreditsRequestDto> creditsRequest) {
        return creditsService.saveCredit(creditsRequest)
                .map(ResponseEntity.status(HttpStatus.OK)::body)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }


    @GetMapping("/{creditId}")   // Mostrar el credito de un cliente
    public Mono<ResponseEntity<CreditsResponseDto>> getCreditById(@PathVariable("creditId") String creditId) {
        return creditsService.getCreditById(creditId)
                .map(ResponseEntity.status(HttpStatus.OK)::body)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }


    @PutMapping("/{creditId}")  // Actualizar credito
    public Mono<ResponseEntity<CreditsResponseDto>> updateCredit(@PathVariable("creditId") String creditId, @RequestBody Mono<UpdateCreditRequestDto> updateCreditRequestDto) {
        return creditsService.updateCredit(updateCreditRequestDto, creditId)
                .map(ResponseEntity.status(HttpStatus.OK)::body)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }


    @DeleteMapping("/{creditId}")   // Eliminar Creditos
    Mono<ResponseEntity<DeleteResponseDto>> deleteCredit(@PathVariable("creditId") String creditId) {
        return creditsService.deleteCredit(creditId)
                .map(ResponseEntity.status(HttpStatus.OK)::body)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

}
