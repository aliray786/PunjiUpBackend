package in.timesinternet.punjiup.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import in.timesinternet.punjiup.entity.embeddable.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer customerId;
    @Column(nullable = false, unique = true, updatable = false)
    private String email;
    @Column(nullable = false)
    @JsonIgnore
    private String cusPassword;
    private String firstName;
    private String lastName;
    private Boolean isActive;
    @Embedded
    Address address;
    private Date lastTradeDate;
    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
    private List<Transaction> transactionList = new ArrayList<Transaction>();
    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<CustomerFund> customerFundList = new ArrayList<CustomerFund>();
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;
    void addTransaction(Transaction transaction)
    {
        transactionList.add(transaction);
        transaction.setCustomer(this);
    }
    void addCustomerFund(CustomerFund customerFund)
    {
        customerFundList.add(customerFund);
        customerFund.setCustomer(this);
    }
   /* @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
    Cart cart;
          */

}
