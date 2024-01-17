package cg.tcarespb.service.contactEmployee.response;

import cg.tcarespb.models.enums.EGender;
import cg.tcarespb.service.cart.response.CartAllFieldResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContactEmployeeResponse {
    private String idContact;
    private String idCart;
    private String idUser;
    private String idEmployee;
    private CartAllFieldResponse cart;
    private ContactRelativeResponse user;
    private ContactRelativeResponse employee;

    public ContactEmployeeResponse(String idContact, String idCart, String idUser, String idEmployee) {
        this.idContact = idContact;
        this.idCart = idCart;
        this.idUser = idUser;
        this.idEmployee = idEmployee;
    }
}
