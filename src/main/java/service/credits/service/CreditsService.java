package service.credits.service;


import reactor.core.publisher.Mono;
import service.credits.model.dto.CreditsRequestDto;
import service.credits.model.dto.CreditsResponseDto;
import service.credits.model.dto.DeleteResponseDto;
import service.credits.model.dto.UpdateCreditRequestDto;

public interface CreditsService {

    Mono<CreditsResponseDto> save(Mono<CreditsRequestDto> creditsRequest);

    Mono<CreditsResponseDto> getCreditById(String creditId);

    Mono<CreditsResponseDto> update(Mono<UpdateCreditRequestDto> updateCreditRequest, String creditId);

    Mono<DeleteResponseDto> deleteCredit(String creditId);

}
