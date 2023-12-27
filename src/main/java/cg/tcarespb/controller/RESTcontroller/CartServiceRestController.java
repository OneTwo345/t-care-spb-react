package cg.tcarespb.controller.RESTcontroller;

import cg.tcarespb.service.cartService.CartServiceService;
import cg.tcarespb.service.serviceGeneral.request.ServiceSaveRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cartServices")
@AllArgsConstructor
public class CartServiceRestController {
    private  final CartServiceService cartServiceService;


    @PostMapping
    public void create(@RequestBody ServiceSaveRequest req) {
//        cartServiceService.create(req);
    }
}
