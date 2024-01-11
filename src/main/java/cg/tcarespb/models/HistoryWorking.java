package cg.tcarespb.models;

import cg.tcarespb.models.enums.EDateInWeek;
import cg.tcarespb.models.enums.ESessionOfDate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "history_workings")
public class HistoryWorking {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToOne
    private Contract contract;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Cart cart;

    @Enumerated(EnumType.STRING)
    private EDateInWeek dateInWeek;

    @Enumerated(EnumType.STRING)
    private ESessionOfDate sessionOfDate;

    private LocalDate date;
}
