package in.timesinternet.punjiup.dto;

import in.timesinternet.punjiup.entity.embeddable.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
    private String email;
    private String cusPassword;
    private String firstName;
    private String lastName;
    Address address;
}
