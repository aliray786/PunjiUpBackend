package in.timesinternet.punjiup.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import in.timesinternet.punjiup.entity.enumaration.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @Column(nullable = false,updatable = false)
    String email;
    @JsonIgnore
    @Column(nullable = false)
    String password;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    Role role;
    private Boolean isActive;


}
