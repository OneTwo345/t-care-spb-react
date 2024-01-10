package cg.tcarespb.controller.RESTcontroller;

import cg.tcarespb.repository.HistoryWorkingRepository;
import cg.tcarespb.service.employee.request.EmployeeScheduleSaveRequest;
import cg.tcarespb.service.historyWorking.HistoryWorkingService;
import cg.tcarespb.service.historyWorking.request.HistoryWorkingForCartRequest;
import cg.tcarespb.service.historyWorking.request.HistoryWorkingSaveRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/historyWorkings")
@CrossOrigin("http://localhost:3000")
public class HistoryWorkingRestController {
    private final HistoryWorkingService historyWorkingService;

    @PostMapping
    public ResponseEntity<?> createTest(@RequestBody HistoryWorkingSaveRequest req) {
        return new ResponseEntity<>(historyWorkingService.createTest(req).size(), HttpStatus.OK);
    }
    @PostMapping("/cart")
    public ResponseEntity<?> createTestForCart(@RequestBody HistoryWorkingForCartRequest req) {
        return new ResponseEntity<>(historyWorkingService.createHistoryWorkingForCart(req), HttpStatus.OK);
    }
}
