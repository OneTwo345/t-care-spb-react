package cg.tcarespb.models;

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
@Table(name = "locations")
@SQLDelete(sql = "UPDATE locations SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class LocationPlace {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    private Float distanceForWork;
    private Float longitude;
    private Float latitude;
    private Boolean deleted = false;

    @OneToOne
    private Employee employee;
    @OneToOne
    private Contract contract;
    @OneToOne
    private Cart cart;

}
