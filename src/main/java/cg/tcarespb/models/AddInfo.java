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
@Table(name = "add_infos")
@SQLDelete(sql = "UPDATE add_infos SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class AddInfo {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    private Boolean deleted = false;

    @OneToMany(mappedBy = "addInfo")
    private List<EmployeeInfo> employeeInfos;

    @OneToMany(mappedBy = "addInfo")
    private List<CartInfo> cartInfos;

    public AddInfo(String id) {
        this.id = id;
    }
}
