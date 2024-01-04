package cg.tcarespb.service.account.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccountListResponse {
    private String id;
    private String email;
}
