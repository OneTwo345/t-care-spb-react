package cg.tcarespb.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table( name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private Integer hourPerWeekMin;
    private Integer hourPerWeekMax;
    private BigDecimal priceMin;
    private BigDecimal priceMax;
    private Integer minHourPerJob;
    private Boolean deleted = false;

    @OneToOne
    private Employee employee;


}
