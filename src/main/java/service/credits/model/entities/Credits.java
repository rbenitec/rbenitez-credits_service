package service.credits.model.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "credits")
public class Credits {
    @Id
    private Integer id;
    private String creditType;
    private String clientId;
    private Double amount;
    private Double interestRate;
    private Integer termMonths;
    private String dateCreated;
    private String dateUpdate;
    private List<String> payments;
}
