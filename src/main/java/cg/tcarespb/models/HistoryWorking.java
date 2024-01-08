package cg.tcarespb.models;

import cg.tcarespb.models.enums.EDateInWeek;
import cg.tcarespb.models.enums.ESessionOfDate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "history_working")
public class HistoryWorking {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

   @ManyToOne
    private Contract contract;
    @ManyToOne
    private Employee employee;
    private EDateInWeek dateInWeek;
    private ESessionOfDate sessionOfDate;
    private String dateTime;
}
