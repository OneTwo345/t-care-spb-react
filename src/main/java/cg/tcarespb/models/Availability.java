package cg.tcarespb.models;

import cg.tcarespb.models.enums.EDateInWeek;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "availabilities")
public class Availability {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private EDateInWeek dateInWeek;
    private LocalTime timeStart;
    private LocalTime timeEnd;
    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Cart cart;

}
