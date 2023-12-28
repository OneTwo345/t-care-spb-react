package cg.tcarespb.service.dateSession.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class DateSessionSaveRequest {
    private String date;
    private List<String> sessionOfDateList;
}
