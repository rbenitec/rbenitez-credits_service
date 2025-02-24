package service.credits.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import service.credits.exception.BusinessException;
import service.credits.mapper.MapperToCredit;
import service.credits.mapper.MapperToResponseCredit;
import service.credits.model.dto.CreditsRequestDto;
import service.credits.model.dto.CreditsResponseDto;
import service.credits.model.dto.DeleteResponseDto;
import service.credits.model.dto.UpdateCreditRequestDto;
import service.credits.repository.CreditsRepository;
import service.credits.service.CreditsService;
import service.credits.util.Utility;

@Service
@RequiredArgsConstructor
public class CreditsServiceImpl implements CreditsService {

    private final CreditsRepository creditsRepository;

    private final MapperToCredit mapperToCredit;
    private final MapperToResponseCredit mapperToResponseCredit;

    @Override
    public Mono<CreditsResponseDto> saveCredit(Mono<CreditsRequestDto> requestDto) {
        return requestDto
                .map(mapperToCredit)
                .flatMap(creditsRepository::save)
                .switchIfEmpty(Mono.error(new BusinessException("saveCredit", "Error saving credit to database")))
                .map(mapperToResponseCredit)
                .onErrorMap(ex -> new BusinessException("[saveCredit]: Error in the process of saving a credit", ex.getMessage()));
    }

    @Override
    public Mono<CreditsResponseDto> getCreditById(String creditId) {
        return creditsRepository.findById(creditId)
                .map(mapperToResponseCredit)
                .onErrorMap(throwable -> new BusinessException("[getCreditById]: error in the process of obtaining a credit by its id", throwable.getMessage()));
    }

    @Override
    public Mono<CreditsResponseDto> updateCredit(Mono<UpdateCreditRequestDto> updateCreditRequest, String creditId) {
        return updateCreditRequest
                .flatMap(request ->
                        creditsRepository.findById(creditId)
                                .map(credit -> {
                                    credit.setAmount(request.getAmount());
                                    credit.setInterestRate(request.getInterestRate());
                                    credit.setTermMonths(request.getTermMonths());
                                    credit.setDateUpdate(Utility.getDateTimeNow());
                                    return credit;
                                })
                                .switchIfEmpty(Mono.error(new BusinessException("updateCredit", "credit with id" + creditId + "does not exist")))
                )
                .flatMap(creditsRepository::save)
                .map(mapperToResponseCredit)
                .onErrorMap(ex -> new BusinessException("[updateCredit]: Error in the process of update a credit", ex.getMessage()));
    }

    @Override
    public Mono<DeleteResponseDto> deleteCredit(String creditId) {
        return creditsRepository.deleteById(creditId)
                .then(Mono.just(DeleteResponseDto.builder()
                        .message("The Credit was successfully deleted, with id: ".concat(creditId))
                        .build()))
                .onErrorResume(error -> Mono.just(DeleteResponseDto.builder()
                        .message("Error deleting credit with id: "
                                .concat(creditId)
                                .concat(" - error: ".concat(error.getMessage()))
                        )
                        .build()));
    }

}
