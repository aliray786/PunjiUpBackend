package in.timesinternet.punjiup.dto;
import in.timesinternet.punjiup.entity.enumaration.TransactionStatus;
import in.timesinternet.punjiup.entity.enumaration.TransactionType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class TransactionDto {
    TransactionType transactionType;
    Double amount;
    Integer fundId;
    Integer customerId;
}
