package cg.tcarespb.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "employee_services")
public class EmployeeServiceGeneral {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private Boolean deleted = false;

    @ManyToOne
    private Employee employee;
    @ManyToOne
    private ServiceGeneral service;

    public EmployeeServiceGeneral(Employee employee, ServiceGeneral service) {
        this.employee = employee;
        this.service = service;
    }


}
