package cg.tcarespb.models;

import cg.tcarespb.models.enums.EDecade;
import cg.tcarespb.models.enums.EGender;
import cg.tcarespb.models.enums.EMemberOfFamily;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "carts")
@SQLDelete(sql = "UPDATE carts SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Cart {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private LocalDate timeStart;
    private LocalDate timeEnd;
    private Integer agePatient;
    private String noteForPatient;
    private String noteForEmployee;

    private Boolean deleted = false;
    @Enumerated(EnumType.STRING)
    private EMemberOfFamily memberOfFamily;
    @Enumerated(EnumType.STRING)
    private EGender gender;
    @Enumerated(EnumType.STRING)
    private EDecade eDecade;

    @ManyToOne
    private User user;
    @ManyToOne
    private Saler saler;

    @OneToMany(mappedBy = "cart")
    private List<CartInfo> cartInfos;

    @ManyToOne
    @JoinColumn(name = "service_id") // Assuming service_id is the foreign key column in Cart
    private ServiceGeneral service;

    @OneToMany(mappedBy = "cart")
    private List<CartSkill> cartSkills;

    @OneToMany(mappedBy = "cart")
    private List<DateSession> dateSessions;
    @OneToOne
    private LocationPlace locationPlace;

}
