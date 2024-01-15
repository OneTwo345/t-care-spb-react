package cg.tcarespb.service.contactEmployee;

import cg.tcarespb.models.Cart;
import cg.tcarespb.models.ContactEmployee;
import cg.tcarespb.models.Employee;
import cg.tcarespb.models.User;
import cg.tcarespb.models.enums.EContactStatus;
import cg.tcarespb.models.enums.EPayStatus;
import cg.tcarespb.repository.ContactEmployeeRepository;
import cg.tcarespb.service.cart.CartService;
import cg.tcarespb.service.contactEmployee.request.ContactEmployeeByCartSaveRequest;
import cg.tcarespb.service.contactEmployee.request.ContactEmployeeDateSaveRequest;
import cg.tcarespb.service.contactEmployee.response.ContactEmployeeResponse;
import cg.tcarespb.service.contactEmployee.response.ContactRelativeResponse;
import cg.tcarespb.service.employee.EmployeeService;
import cg.tcarespb.util.AppMessage;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@Service
public class ContactEmployeeService {
    private final ContactEmployeeRepository contactEmployeeRepository;
    private final EmployeeService employeeService;
    private final CartService cartService;


    public ContactEmployee findById(String id) {
        return contactEmployeeRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format(AppMessage.ID_NOT_FOUND, "Cart", id)));
    }

    public void createContactEmployee(ContactEmployeeByCartSaveRequest req) {
        Cart cart = cartService.findById(req.getIdCart());
        Employee employee = employeeService.findById(req.getIdEmployee());
        if (cart.getContactEmployees() != null) {
            contactEmployeeRepository.deleteById(cart.getContactEmployees().getId());
        }
        ContactEmployee contactEmployee = new ContactEmployee();
        contactEmployee.setCart(cart);
        contactEmployee.setEmployee(employee);
        contactEmployee.setContactStatus(EContactStatus.CONFIRMING);
        contactEmployeeRepository.save(contactEmployee);
    }

    public void updateDateForContactEmployee(ContactEmployeeDateSaveRequest req) {
        ContactEmployee contactEmployee = findById(req.getIdContactEmployee());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy ss:mm:hh");
        LocalDateTime dateTime = LocalDateTime.parse(req.getDate(), dateTimeFormatter);
        contactEmployee.setDateTime(dateTime);
        contactEmployeeRepository.save(contactEmployee);
    }


    public ContactEmployeeResponse getContactEmployee(String idContactEmployee) {
        ContactEmployee contactEmployee = findById(idContactEmployee);
        ContactEmployeeResponse contactEmployeeResponse = new ContactEmployeeResponse();
        ContactRelativeResponse user = new ContactRelativeResponse();
        user.setId(contactEmployee.getCart().getUser().getId());
        user.setGender(contactEmployee.getCart().getUser().getGender());
        user.setFirstName(contactEmployee.getCart().getUser().getFirstName());
        user.setLastName(contactEmployee.getCart().getUser().getLastName());
        user.setPhoneNumber(contactEmployee.getCart().getUser().getPhoneNumber());
        user.setPersonId(contactEmployee.getCart().getUser().getPersonID());

        ContactRelativeResponse employee = new ContactRelativeResponse();
        employee.setId(contactEmployee.getCart().getUser().getId());
        employee.setGender(contactEmployee.getCart().getUser().getGender());
        employee.setFirstName(contactEmployee.getCart().getUser().getFirstName());
        employee.setLastName(contactEmployee.getCart().getUser().getLastName());
        employee.setPhoneNumber(contactEmployee.getCart().getUser().getPhoneNumber());
        employee.setPersonId(contactEmployee.getCart().getUser().getPersonID());

        contactEmployeeResponse.setIdContact(contactEmployee.getId());
        contactEmployeeResponse.setIdCart(contactEmployee.getCart().getId());
        contactEmployeeResponse.setEmployee(employee);
        contactEmployeeResponse.setUser(user);
        return contactEmployeeResponse;
    }

    public void updateContactStatus(String idContactEmployee, EContactStatus status) {
        ContactEmployee contactEmployee = findById(idContactEmployee);
        contactEmployee.setContactStatus(status);
        contactEmployeeRepository.save(contactEmployee);
    }

    public Page<ContactEmployeeResponse> getAllContactEmployeeByEmployeeId(Pageable pageable, String idEmployee, EContactStatus status) {
        Page<ContactEmployeeResponse> contactEmployeeResponses = contactEmployeeRepository.getContactEmployee(idEmployee, status, pageable);
        contactEmployeeResponses.stream().forEach(e -> {
            User user = cartService.findById(e.getIdCart()).getUser();
            Employee employee = employeeService.findById(idEmployee);
            ContactRelativeResponse userResponse = new ContactRelativeResponse();
            userResponse.setId(user.getId());
            userResponse.setGender(user.getGender());
            userResponse.setFirstName(user.getFirstName());
            userResponse.setLastName(user.getLastName());
            userResponse.setPhoneNumber(user.getPhoneNumber());
            userResponse.setPersonId(user.getPersonID());
            ContactRelativeResponse employeeResponse = new ContactRelativeResponse();
            employeeResponse.setId(employee.getId());
            employeeResponse.setGender(employee.getGender());
            employeeResponse.setFirstName(employee.getFirstName());
            employeeResponse.setLastName(employee.getLastName());
            employeeResponse.setPhoneNumber(employee.getPhoneNumber());
            employeeResponse.setPersonId(employee.getPersonID());
            e.setUser(userResponse);
            e.setEmployee(employeeResponse);
        });
        return contactEmployeeResponses;

    }

}
