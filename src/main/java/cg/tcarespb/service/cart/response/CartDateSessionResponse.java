package cg.tcarespb.service.cart.response;

import cg.tcarespb.models.enums.EDateInWeek;
import cg.tcarespb.models.enums.ESessionOfDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDateSessionResponse {
    private EDateInWeek dateInWeek;
    private String dateInWeekName;
    private ESessionOfDate sessionOfDate;
    private String sessionOfDateName ;
}
