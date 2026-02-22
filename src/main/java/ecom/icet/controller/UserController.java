package ecom.icet.controller;

import ecom.icet.model.dto.UserDto;
import ecom.icet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto dto) {
        return ResponseEntity.ok(userService.createUser(dto));
    }

    @GetMapping("/role/{role}")
    public List<UserDto> getByRole(@PathVariable String role) {
        return userService.getAllUsersByRole(role);
    }
}
