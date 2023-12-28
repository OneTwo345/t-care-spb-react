package cg.tcarespb.service.dateSession.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class DateSessionListSaveRequest {
    List<DateSessionSaveRequest> listDateSession;

}
