package service.credits.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import service.credits.model.dto.CreditsRequestDto;
import service.credits.model.dto.CreditsResponseDto;
import service.credits.model.dto.DeleteResponseDto;
import service.credits.model.dto.UpdateCreditRequestDto;
import service.credits.model.entities.Credits;
import service.credits.repository.CreditsRepository;
import service.credits.service.CreditsService;
import service.credits.util.Utility;

@Service
@RequiredArgsConstructor
public class CreditsServiceImpl implements CreditsService {

    private final CreditsRepository creditsRepository;

    @Override
    public Mono<CreditsResponseDto> save(Mono<CreditsRequestDto> requestDto) {
        return requestDto
                .map(request -> Credits.builder()
                        .id(Utility.generatedIdCredit())
                        .creditType(request.getCreditType())
                        .amount(request.getAmount())
                        .termMonths(request.getTermMonths())
                        .interestRate(request.getInterestRate())
                        .creditType(request.getCreditType())
                        .dateCreated(Utility.obtenerFechaHoraActual())
                        .dateUpdate(Utility.obtenerFechaHoraActual())
                        .build())
                .flatMap(creditsRepository::save)
                .map(creditsSaved -> {
                    CreditsResponseDto CreditsResponseDto = new CreditsResponseDto();
                    CreditsResponseDto.setId(creditsSaved.getId());
                    CreditsResponseDto.setCreditType(creditsSaved.getCreditType());
                    CreditsResponseDto.setAmount(creditsSaved.getAmount());
                    CreditsResponseDto.setInterestRate(creditsSaved.getInterestRate());
                    CreditsResponseDto.setTermMonths(creditsSaved.getTermMonths());
                    CreditsResponseDto.setCreatedAt(creditsSaved.getDateCreated());
                    CreditsResponseDto.setUpdateAt(creditsSaved.getDateUpdate());
                    return CreditsResponseDto;
                });
    }

    @Override
    public Mono<CreditsResponseDto> getCreditById(String creditId) {
        return creditsRepository.findById(creditId)
                .map(credits -> CreditsResponseDto.builder()
                        .id(credits.getId())
                        .creditType(credits.getCreditType())
                        .amount(credits.getAmount())
                        .interestRate(credits.getInterestRate())
                        .termMonths(credits.getTermMonths())
                        .createdAt(credits.getDateCreated())
                        .updateAt(credits.getDateUpdate())
                        .build());
    }

    @Override
    public Mono<CreditsResponseDto> update(Mono<UpdateCreditRequestDto> updateCreditRequest, String creditId) {
        return updateCreditRequest
                .flatMap(request -> creditsRepository.findById(creditId)
                        .map(credit -> {
                            credit.setAmount(request.getAmount());
                            credit.setInterestRate(request.getInterestRate());
                            credit.setTermMonths(request.getTermMonths());
                            credit.setDateUpdate(Utility.obtenerFechaHoraActual());
                            return credit;
                        }))
                .flatMap(creditsRepository::save)
                .map(creditsSaved -> CreditsResponseDto.builder()
                        .id(creditsSaved.getId())
                        .creditType(creditsSaved.getCreditType())
                        .amount(creditsSaved.getAmount())
                        .interestRate(creditsSaved.getInterestRate())
                        .termMonths(creditsSaved.getTermMonths())
                        .createdAt(creditsSaved.getDateCreated())
                        .updateAt(creditsSaved.getDateUpdate())
                        .build());
    }

    @Override
    public Mono<DeleteResponseDto> deleteCredit(String creditId) {
        return creditsRepository.deleteById(creditId)
                .then(Mono.just(DeleteResponseDto.builder()
                        .message("Se elimino correctamente el credito, con id: ".concat(creditId))
                        .build()))
                .onErrorResume(error -> Mono.just(DeleteResponseDto.builder()
                        .message("Error al eliminar credito con id: "
                                .concat(creditId)
                                .concat(" - error: ".concat(error.getMessage()))
                        )
                        .build()));
    }

}
