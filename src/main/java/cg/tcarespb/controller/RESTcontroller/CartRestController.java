package cg.tcarespb.controller.RESTcontroller;

import cg.tcarespb.models.Cart;
import cg.tcarespb.service.cart.CartService;
import cg.tcarespb.service.cart.request.CartDateSessionListSaveRequest;
import cg.tcarespb.service.cart.request.CartServiceListSaveRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@AllArgsConstructor
public class CartRestController {
    private final CartService cartService;

    @PostMapping
    public ResponseEntity<?> create() {
        Cart cart = cartService.create();
        return new ResponseEntity<>(cart.getId(), HttpStatus.CREATED);
    }

    @PutMapping("/services/{id}")
    public ResponseEntity<?> updateServiceGeneral(@PathVariable("id") String id, @RequestBody CartServiceListSaveRequest req) {
        cartService.updateCartService(req, id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/dateSessions/{id}")
    public ResponseEntity<?> updateDateSession(@PathVariable("id") String id, @RequestBody CartDateSessionListSaveRequest req) {
        cartService.updateDateSessionCart(req, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCartById(@PathVariable("id") String id) {
        Cart cart = cartService.findById(id);
        return new ResponseEntity<>(cart.getId(), HttpStatus.OK);
    }
}
