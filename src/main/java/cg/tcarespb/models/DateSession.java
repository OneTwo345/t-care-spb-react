package cg.tcarespb.models;

import cg.tcarespb.models.enums.EDateInWeek;
import cg.tcarespb.models.enums.ESessionOfDate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table( name = "date_session")
public class DateSession {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Enumerated(EnumType.STRING)
    private EDateInWeek dateInWeek;
    @Enumerated(EnumType.STRING)
    private ESessionOfDate sessionOfDate;

    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Employee employee;


    public DateSession(String id) {
        this.id = id;
    }

    public DateSession(String id, EDateInWeek dateInWeek, ESessionOfDate sessionOfDate, Employee employee) {
        this.id = id;
        this.dateInWeek = dateInWeek;
        this.sessionOfDate = sessionOfDate;
        this.employee = employee;
    }

}
