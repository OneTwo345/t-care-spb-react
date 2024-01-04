package cg.tcarespb.models;

import cg.tcarespb.models.enums.EGender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "contracts")
public class Contract {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private LocalDate timeStart;
    private LocalDate timeEnd;
    private String namePatient;
    private Integer agePatient;
    private String content;
    private BigDecimal pricePerHour;
    private BigDecimal totalPrice;
    private Integer dateQuantity;
    private Integer hourPerDay;
    private Boolean deleted = false;
    @Enumerated(EnumType.STRING)
    private EGender gender;
    @ManyToOne
    private Employee employee;
    @ManyToOne
    private Cart cart;

}
