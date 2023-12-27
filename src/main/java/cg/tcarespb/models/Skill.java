package cg.tcarespb.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE skills SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@Table(name = "skills")
public class Skill {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    private Boolean deleted = false;

    @OneToMany(mappedBy = "skill")
    private List<EmployeeSkill> employeeSkills;

    @OneToMany(mappedBy = "skill")
    private List<CartSkill> cartSkills;
}
