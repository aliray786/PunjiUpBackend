package in.timesinternet.punjiup.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
@Getter
@Setter
public class FundmanagerDto {
    private String email;
    private String mgrPassword;
    private String firstName;
    private String lastName;
    private String companyName;
    private String educationQualification;
    private String experience;
}
