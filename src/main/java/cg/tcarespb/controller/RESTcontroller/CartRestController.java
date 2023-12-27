package cg.tcarespb.controller.RESTcontroller;

import cg.tcarespb.service.cart.CartService;
import cg.tcarespb.service.cartService.request.CartServiceListSaveRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@AllArgsConstructor
public class CartRestController {
    private final CartService cartService;

    @PostMapping
    public void create() {
        cartService.create();
    }
    @PutMapping("/services/{id}")
    public ResponseEntity<Void> deleteServiceGeneral(@PathVariable("id") String id, @RequestBody CartServiceListSaveRequest req) {

        return ResponseEntity.noContent().build();
    }
}
