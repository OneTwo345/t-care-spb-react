package cg.tcarespb.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    private String distanceForWork;
    private String longitude;
    private String latitude;
    private Boolean deleted = false;

    @OneToOne
    private Employee employee;
    @OneToOne
    private Contract contract;
    @OneToOne
//    private User user;
    private Cart cart;

}
