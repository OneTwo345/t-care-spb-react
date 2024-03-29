package cg.tcarespb.models;

import cg.tcarespb.models.enums.EGender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "salers")
@SQLDelete(sql = "UPDATE salers SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Saler {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String personID;
    private String firstName;
    private String lastName;
    private EGender gender;
    private Boolean deleted = false;
    @OneToMany(mappedBy = "saler")
    private List<Cart> carts;
}
