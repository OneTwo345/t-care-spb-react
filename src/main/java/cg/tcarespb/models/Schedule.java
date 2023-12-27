package cg.tcarespb.models;

import cg.tcarespb.models.enums.EJobType;
import cg.tcarespb.models.enums.ESessionOfDate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.UUID;

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
    private EJobType jobType;
    private ESessionOfDate sessionOfDate;
    private Boolean deleted = false;

    @OneToOne
    private Employee employee;


}
