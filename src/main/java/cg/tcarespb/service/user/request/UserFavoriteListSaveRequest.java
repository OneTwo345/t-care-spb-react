package cg.tcarespb.service.user.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class UserFavoriteListSaveRequest {
    private List<String > employeeFavoriteIdList;
}
