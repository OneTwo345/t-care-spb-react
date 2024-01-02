package cg.tcarespb.models;

import cg.tcarespb.models.enums.EDecade;
import cg.tcarespb.models.enums.EGender;
import cg.tcarespb.models.enums.EJobType;
import cg.tcarespb.models.enums.EMemberOfFamily;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
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
    private String namePatient;
    private Integer agePatient;
    private String noteForPatient;
    private String noteForEmployee;
    private Integer hourPerDay;
    private BigDecimal priceMin;
    private BigDecimal priceMax;
    private Boolean deleted = false;
    @Enumerated(EnumType.STRING)
    private EMemberOfFamily memberOfFamily;
    @Enumerated(EnumType.STRING)
    private EGender gender;
    @Enumerated(EnumType.STRING)
    private EDecade eDecade;
    @Enumerated(EnumType.STRING)
    private EJobType eJobType;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "cart")
    private List<CartInfo> cartInfos;

    @OneToMany(mappedBy = "cart")
    private List<CartServiceGeneral> cartServices;

    @OneToMany(mappedBy = "cart")
    private List<CartSkill> cartSkills;

    @OneToMany(mappedBy = "cart")
    private List<Availability> availabilities;

    @OneToMany(mappedBy = "cart")
    private List<Contract> contracts;
    @OneToMany(mappedBy = "cart")
    private List<DateSession> dateSessions;
    @OneToOne(mappedBy = "cart")
    private LocationPlace locationPlace;

}
