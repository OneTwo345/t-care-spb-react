package cg.tcarespb.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "cart_skills")
@SQLDelete(sql = "UPDATE cart_skills SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class CartSkill {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private Boolean deleted = false;

    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Skill skill;

    public CartSkill(Cart cart, Skill skill) {
        this.cart = cart;
        this.skill = skill;
    }
}
