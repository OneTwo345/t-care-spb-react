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

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "service_generals")
@SQLDelete(sql = "UPDATE service_generals SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class ServiceGeneral {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    private String description;
    private Boolean deleted = false;

    @OneToMany(mappedBy = "service")
    private List<EmployeeService> employeeServiceList;

    @OneToMany(mappedBy = "service")
    private List<CartServiceGeneral> cartServices;
}
