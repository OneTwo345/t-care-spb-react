package cg.tcarespb.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "add_infos")
public class AddInfo {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;

    @OneToMany(mappedBy = "addInfo")
    private List<EmployeeInfo> employeeInfos;

    @OneToMany(mappedBy = "addInfo")
    private List<CartInfo> cartInfos;

}
