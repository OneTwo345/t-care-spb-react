package cg.tcarespb.controller.RESTcontroller;

import cg.tcarespb.models.Cart;
import cg.tcarespb.service.cart.CartService;
import cg.tcarespb.service.cartService.CartServiceService;
import cg.tcarespb.service.cartService.request.CartServiceListSaveRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@AllArgsConstructor
public class CartRestController {
    private final CartService cartService;
    private final CartServiceService cartServiceService;

    @PostMapping
    public ResponseEntity<?> create() {
        Cart cart = cartService.create();
        return new ResponseEntity<>(cart.getId(), HttpStatus.CREATED);
    }

    @PutMapping("/services/{id}")
    public ResponseEntity<Void> deleteServiceGeneral(@PathVariable("id") String id, @RequestBody CartServiceListSaveRequest req) {
        cartServiceService.create(req, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCartById(@PathVariable("id") String id) {
        Cart cart = cartService.findById(id);
        return new ResponseEntity<>(cart.getId(), HttpStatus.OK);
    }
}
