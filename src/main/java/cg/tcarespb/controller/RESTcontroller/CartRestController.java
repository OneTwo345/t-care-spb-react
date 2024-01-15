package cg.tcarespb.controller.RESTcontroller;

import cg.tcarespb.models.Cart;
import cg.tcarespb.service.cart.CartService;
import cg.tcarespb.service.cart.request.*;
import cg.tcarespb.service.cart.response.CartListResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class CartRestController {
    private final CartService cartService;


//    @PostMapping
//    public ResponseEntity<?> create() {
//        Cart cart = new Cart();
////        User user = new User();
////        cart.setUser(user);
//        cartService.create(cart);
//        return new ResponseEntity<>(cart.getId(), HttpStatus.CREATED);
//    }

    @PostMapping("/createFilter")
    public ResponseEntity<?> createAllPropertiesCart(@RequestBody CartSaveFilterRequest req) {
        String id = cartService.createCartForFilter(req);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
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

//    @PutMapping("/jobTypes/{id}")
//    public ResponseEntity<?> updateJobType(@PathVariable("id") String id, @RequestBody CartJobTypeSaveRequest req) {
//        cartService.updateJobType(req, id);
//        return ResponseEntity.noContent().build();
//    }

    @PutMapping("/infoPatient/{id}")
    public ResponseEntity<?> updateInfoPatient(@PathVariable("id") String id, @RequestBody CartInfoPatientSaveRequest req) {
        cartService.updateInfoPatient(req, id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/noteEmployee/{id}")
    public ResponseEntity<?> updateNoteForEmployee(@PathVariable("id") String id, @RequestBody CartNoteForEmployeeSaveRequest req) {
        cartService.updateNoteForEmployee(req, id);
        return ResponseEntity.noContent().build();
    }

//    @PutMapping("/priceRange/{id}")
//    public ResponseEntity<?> updatePriceRange(@PathVariable("id") String id, @RequestBody CartPriceMinMaxSaveRequest req) {
//        cartService.updatePriceMinMax(req, id);
//        return ResponseEntity.noContent().build();
//    }

    @PutMapping("/locations/{id}")
    public ResponseEntity<?> updateLocation(@PathVariable("id") String id, @RequestBody CartLocationSaveRequest req) {
        cartService.updateLocationForCart(req, id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/cartSkills/{id}")
    public ResponseEntity<?> updateCartSkill(@PathVariable("id") String id, @RequestBody CartSkillSaveRequest req) {
        cartService.updateCartSkill(req, id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/cartInfos/{id}")
    public ResponseEntity<?> updateCartInfo(@PathVariable("id") String id, @RequestBody CartInfoSaveRequest req) {
        cartService.updateCartInfo(req, id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/timeStartEnd/{id}")
    public ResponseEntity<?> updateTimeStartEnd(@PathVariable("id") String id, @RequestBody CartTimeStartEndSaveRequest req) {
        cartService.updateTimeStartEnd(req, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCartById(@PathVariable("id") String id) {
        Cart cart = cartService.findById(id);
        return new ResponseEntity<>(cart.getId(), HttpStatus.OK);
    }


    @GetMapping("/filter/{id}")
    public ResponseEntity<?> filterList(@PathVariable("id") String id, @PageableDefault(size = 20) Pageable pageable) {
        return new ResponseEntity<>(cartService.filter(id, pageable), HttpStatus.OK);
    }
//    @GetMapping("/filterTest/{id}")
//    public ResponseEntity<?> filterList(@PathVariable("id") String id){
//        return new ResponseEntity<>(cartService.filterTest(id), HttpStatus.OK);
//    }

    @GetMapping("/sale/{id}")
    public ResponseEntity<List<CartListResponse>> getCartList(@PathVariable("id") String id) {
        List<CartListResponse> cartListResponses = cartService.findCartBySaler(id);
        return ResponseEntity.ok(cartListResponses);
    }

    @PutMapping("/updateAllField/{id}")
    public ResponseEntity<?> updateAllField(@PathVariable("id") String id, @RequestBody  CartAllFieldRequest req) {
        cartService.updateAllFieldCart(req, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
