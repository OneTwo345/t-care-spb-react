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

import java.time.LocalTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "availabilities")
@SQLDelete(sql = "UPDATE availabilities SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Availability {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private LocalTime timeStart;
    private LocalTime timeEnd;
    private Boolean deleted = false;
    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Cart cart;

}
