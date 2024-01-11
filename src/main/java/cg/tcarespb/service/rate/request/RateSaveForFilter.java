package cg.tcarespb.service.rate.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RateSaveForFilter {
    private Float quantity;
    private String content;
    private String idUser;
}
