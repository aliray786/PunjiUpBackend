package in.timesinternet.punjiup.dto;
import in.timesinternet.punjiup.entity.enumaration.TransactionStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionStatusUpdateDto {
    Integer transactionId;
    TransactionStatus transactionStatus;
    Double amount;
    Integer fundId;
    Integer customerId;
}
