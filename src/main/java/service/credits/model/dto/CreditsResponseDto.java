package service.credits.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditsResponseDto {
    private String id;
    private String creditType;
    private String clientId;
    private Double amount;
    private Double interestRate;
    private Integer termMonths;
    private String createdAt;
    private String updateAt;
}
