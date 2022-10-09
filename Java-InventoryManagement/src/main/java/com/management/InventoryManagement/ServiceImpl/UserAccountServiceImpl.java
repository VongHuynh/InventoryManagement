package com.management.InventoryManagement.ServiceImpl;

import com.management.InventoryManagement.DTO.UserAccountDTO;
import com.management.InventoryManagement.Entity.EmailDetails;
import com.management.InventoryManagement.Entity.UserAccount;
import com.management.InventoryManagement.Entity.UserDetailsImpl;
import com.management.InventoryManagement.Reposistory.UserAccountReposistory;
import com.management.InventoryManagement.Service.EmailService;
import com.management.InventoryManagement.Service.UserAccountService;
import com.management.InventoryManagement.Utils.Convert;
import com.management.InventoryManagement.Utils.RandomPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserAccountServiceImpl implements UserAccountService {
    @Autowired
    private UserAccountReposistory userAccountReposistory;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private Convert convert;
    @Autowired
    private RandomPassword randomPassword;
    @Autowired
    private EmailService emailService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserAccount findByUserName(String userName) {
        return userAccountReposistory.findByUserNameEquals(userName);
    }

    @Override
    public UserAccountDTO findByUserId(Integer userId) {
        Optional<UserAccount> account = userAccountReposistory.findById(userId);
        if (account.isPresent() && account.get().isDeleted() == false) {
            return convert.toDto(account.get(), UserAccountDTO.class);
        }
        return null;
    }

    @Override
    public List<UserAccountDTO> findUsersByIsDeleted() {
        List<UserAccount> userAccountList = userAccountReposistory.findAllByIsDeleted(true);
        List<UserAccountDTO> userAccountDTOList = userAccountList.stream().
                map(user -> convert.toDto(user, UserAccountDTO.class)).collect(Collectors.toList());
        return userAccountDTOList;
    }

    @Override
    public List<UserAccountDTO> findUsersByIsNotDeleted() {
        List<UserAccount> userAccountList = userAccountReposistory.findAllByIsDeleted(false);
        List<UserAccountDTO> userAccountDTOList = userAccountList.stream().
                map(user -> convert.toDto(user, UserAccountDTO.class)).collect(Collectors.toList());
        return userAccountDTOList;
    }

    @Override
    public List<UserAccountDTO> findAllAccounts() {
        return null;
    }

    @Override
    public int totalItem() {
        return 0;
    }

    @Override
    public UserAccountDTO registerAccount(UserAccountDTO account) {
        UserAccountDTO userAccountDTO = new UserAccountDTO();
        userAccountDTO.setUserName(account.getUserName());
        userAccountDTO.setEmail(account.getEmail());
        userAccountDTO.setFullName(account.getFullName());
        userAccountDTO.setPhoneNumber(account.getPhoneNumber());
        String password = randomPassword.alphaNumericString();
        userAccountDTO.setPassword(passwordEncoder.encode(password));
        UserAccount userAccount = convert.toEntity(userAccountDTO, UserAccount.class);
        UserAccountDTO userAccountDTOResponse = convert.toDto(userAccountReposistory.save(userAccount),
                UserAccountDTO.class);

        EmailDetails emailDetails = new EmailDetails(userAccount.getEmail(), "Your Password: "
                + password, "Password App");
        emailService.sendSimpleMail(emailDetails);

        return userAccountDTOResponse;
    }

    @Override
    public UserAccountDTO updateAccount(UserAccountDTO account) {
        UserAccount userAccount = userAccountReposistory.findById(account.getUserID()).orElse(null);
        userAccount.setUserID(account.getUserID());
        userAccount.setEmail(account.getEmail());
        userAccount.setFullName(account.getFullName());
        userAccount.setPhoneNumber(account.getPhoneNumber());
        return convert.toDto(userAccountReposistory.save(userAccount), UserAccountDTO.class);
    }

    @Override
    public Boolean updatePassword(String username, String oldPassword, String newPassword) {
        UserAccount account = userAccountReposistory.findByUserNameEquals(username);
        if (account != null && account.isDeleted() == false && passwordEncoder.matches(oldPassword, account.getPassword())) {
            account.setPassword(passwordEncoder.encode(newPassword));
            userAccountReposistory.save(account);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void deleteAccount(Integer id) {
        userAccountReposistory.deleteById(id);
    }

    @Override
    public Boolean isLogin(UserAccountDTO account) {
        UserAccount accountInDB = userAccountReposistory.findByUserNameEquals(account.getUserName());
        if (accountInDB == null) {
            return false;
        } else if (!passwordEncoder.matches(account.getPassword(), accountInDB.getPassword())) {
            return false;
        } else if (accountInDB.isDeleted()) {
            return false;
        }
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount account = userAccountReposistory.findByUserNameEquals(username);
        if (account == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserDetailsImpl(account);
    }

    // check valid username and password
    private boolean checkIfValidOldPassword(String username, String password) {
        UserAccount account = userAccountReposistory.findByUserNameEquals(username);
        if (passwordEncoder.matches(password, account.getPassword())) {
            return true;
        }
        return false;
    }
}
