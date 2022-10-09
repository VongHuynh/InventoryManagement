package com.management.InventoryManagement.Controller;

import com.management.InventoryManagement.Config.JwtTokenProvider;
import com.management.InventoryManagement.DTO.UserAccountDTO;
import com.management.InventoryManagement.Entity.UserDetailsImpl;
import com.management.InventoryManagement.Payload.Request.ChangePasswordRequest;
import com.management.InventoryManagement.Payload.Response.ObjectResponse;
import com.management.InventoryManagement.Payload.Response.LoginResponse;
import com.management.InventoryManagement.Payload.Response.RegisterResponse;
import com.management.InventoryManagement.Service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/UserAccount")
@CrossOrigin("*")
public class UserAccountController {
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody UserAccountDTO userAccountDTO) {
        if (userAccountService.isLogin(userAccountDTO)) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userAccountDTO.getUserName(),
                            userAccountDTO.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            String jwt = jwtTokenProvider.generateToken(userAccountDTO.getUserName());
            List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                    .collect(Collectors.toList());
            return ResponseEntity.ok(new LoginResponse(jwt, userDetails.getUserAccount().getUserID(), userDetails.getUsername(), 200,
                    "Login successfully", true, roles));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ObjectResponse(HttpStatus.NOT_FOUND.value(),
                "Username or Password inValid", false, null));
    }

    @PostMapping("/registerAccount") @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> registerAccount(@RequestBody @Valid UserAccountDTO userAccountDTO) {
        RegisterResponse response = new RegisterResponse();
        response.setMessage("Register successfully");
        response.setUsername(userAccountDTO.getUserName());
        response.setIsSuccess(true);
        response.setResultCode(200);
        userAccountService.registerAccount(userAccountDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> updateAccount(@RequestBody @Valid UserAccountDTO userAccount) {
        userAccountService.updateAccount(userAccount);
        return ResponseEntity.status(200).body(new ObjectResponse(HttpStatus.OK.value(),
                "Update successfully", true, null));
    }

    @DeleteMapping("{id}") @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> deleteAccount(@PathVariable Integer id) {
        userAccountService.deleteAccount(id);
        return ResponseEntity.status(200).body(new ObjectResponse(HttpStatus.OK.value(),
                "Delete successfully", true, null));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getUserAcountDetails(@PathVariable Integer id) {
        if (userAccountService.findByUserId(id) != null) {
            return ResponseEntity.status(200).body(userAccountService.findByUserId(id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ObjectResponse(HttpStatus.NOT_FOUND.value(),
                "Not found user", false, null));
    }

    @GetMapping("isDeleted") @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getUserIsDeleted() {
        return ResponseEntity.status(200).body(new ObjectResponse(200, "Successfully",
                true, userAccountService.findUsersByIsDeleted()));
    }

    @GetMapping("isNotDeleted") @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getUserIsNotDeleted() {
        return ResponseEntity.status(200).body(new ObjectResponse(200, "Successfully",
                true, userAccountService.findUsersByIsNotDeleted()));
    }

    @PostMapping("changePassword")
    public ResponseEntity<?> changePassword(@RequestBody @Valid ChangePasswordRequest request) {
        if (userAccountService.updatePassword(request.getUsername(), request.getOldPassword(),
                request.getNewPassword())) {
            return ResponseEntity.status(200).body(new ObjectResponse(200, "Successfully", true, null));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ObjectResponse(HttpStatus.NOT_FOUND.value(),
                "Not found username or old password not valid", false, null));
    }

}
