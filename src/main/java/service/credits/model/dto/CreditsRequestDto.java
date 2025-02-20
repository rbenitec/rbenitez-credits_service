package service.credits.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CreditsRequestDto {
    private String creditType;
    private String clientId;
    private Double amount;
    private Double interestRate;
    private Integer termMonths;
}
