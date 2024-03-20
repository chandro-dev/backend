package ara.main.Entity.personas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @Column(name = "payment_id")
    private String paymentId;
    private String state;
    @Column(name = "realization_date")
    private Date realizationDate;
    private String identification;
    private String mail;
}
