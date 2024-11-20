package Controllers;

import Service.UserService;
import entity.Customer;
import entity.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
q
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@EnableMethodSecurity

public class CustomerController {
    private final UserService userService;
    @postMapping ("/new")
    public String addUser(@RequestBody UserInfo userInfo) {
        return userService.addUser(userInfo);
    }
    final private List<Customer> customers = List.of(
            Customer.builder().id("001").name("Lưu VĨnh Tường").email("22133064@student.hcmute.edu.vn").build(),
            Customer.builder().id("002").name("Lưu VĨnh Tường").email("22133064@student.hcmute.edu.vn").build()
    );

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello is Tuong");
    }

    @GetMapping("/customer/all")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = this.customers;
                return ResponseEntity.ok(customers);
    }

    @GetMapping("/customer/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") String id) {
        List<Customer> customers = this.customers.stream().filter(customer -> customer.getId().equals(id)).toList();
        return ResponseEntity.ok(customers.get(0));
    }
}
