package service.credits.mapper;

import org.springframework.stereotype.Component;
import service.credits.model.dto.CreditsRequestDto;
import service.credits.model.entities.Credits;
import service.credits.util.Utility;

import java.util.function.Function;

@Component
public class MapperToCredit implements Function<CreditsRequestDto, Credits> {
    @Override
    public Credits apply(CreditsRequestDto creditsRequestDto) {
        return Credits.builder()
                .id(Utility.generatedIdCredit())
                .creditType(creditsRequestDto.getCreditType())
                .amount(creditsRequestDto.getAmount())
                .termMonths(creditsRequestDto.getTermMonths())
                .interestRate(creditsRequestDto.getInterestRate())
                .creditType(creditsRequestDto.getCreditType())
                .dateCreated(Utility.getDateTimeNow())
                .dateUpdate(Utility.getDateTimeNow())
                .build();
    }
}
