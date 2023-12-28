package cg.tcarespb.service.cart.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class CartDateSessionSaveRequest {
    private String date;
    private List<String> sessionOfDateList;
}
