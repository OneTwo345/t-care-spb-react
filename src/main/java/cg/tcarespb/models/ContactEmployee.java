package cg.tcarespb.models;

import cg.tcarespb.models.enums.EContactStatus;
import cg.tcarespb.models.enums.EPayStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "contact_employees")
public class
ContactEmployee {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private BigDecimal fee;
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    private EContactStatus contactStatus;

    @OneToOne
    private Cart cart;
    @ManyToOne
    private Employee employee;


}
