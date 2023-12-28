package cg.tcarespb.service.cart.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CartServiceListSaveRequest {
    List<String> serviceList;
}
