package cg.tcarespb.models;

import cg.tcarespb.models.enums.ERole;
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
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String email;
    private String password;
    private Boolean deleted = false;

    @Enumerated(EnumType.STRING)
    private ERole eRole;


    @OneToOne
    private User user;

    @OneToOne
    private Employee employee;
}
