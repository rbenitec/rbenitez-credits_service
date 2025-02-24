package service.credits.mapper;

import org.springframework.stereotype.Component;
import service.credits.model.dto.CreditsResponseDto;
import service.credits.model.entities.Credits;

import java.util.function.Function;

@Component
public class MapperToResponseCredit implements Function<Credits, CreditsResponseDto> {

    @Override
    public CreditsResponseDto apply(Credits credits) {
        return new CreditsResponseDto(
                credits.getId(),
                credits.getCreditType(),
                credits.getCustomerId(),
                credits.getAmount(),
                credits.getInterestRate(),
                credits.getTermMonths(),
                credits.getDateCreated(),
                credits.getDateUpdate()
        );
    }
}
