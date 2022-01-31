package vn.edu.hust.samiestate.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hust.samiestate.constant.SystemConstant;
import vn.edu.hust.samiestate.dto.PasswordDTO;
import vn.edu.hust.samiestate.dto.UserDTO;
import vn.edu.hust.samiestate.exception.PasswordFailException;
import vn.edu.hust.samiestate.service.IUserService;

@RestController
@RequestMapping("/api/user")
public class UserAPI {

    @Autowired
    private IUserService userService;

    @PostMapping
    public UserDTO createUsers(@RequestBody UserDTO newUser) {
        try {
            return userService.insert(newUser);
        } catch (Exception e) {
            throw e;
        }
    }

    @PutMapping("/{id}")
    public UserDTO updateUsers(@PathVariable("id") long id, @RequestBody UserDTO userDTO) {
        try {
            return userService.update(id, userDTO);
        } catch (Exception e) {
            throw e;
        }
    }

    @PutMapping("/profile/{username}")
    public UserDTO updateProfileOfUser(@PathVariable("username") String userName, @RequestBody UserDTO userDTO) {
        try {
            return userService.updateProfileOfUser(userName, userDTO);
        }catch (Exception e) {
            throw e;
        }
    }

    @PutMapping("/password/{id}/reset")
    public ResponseEntity<UserDTO> resetPassword(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.resetPassword(id));
    }

    @PutMapping("/change-password/{username}")
    public ResponseEntity<String> changePasswordUser(@PathVariable("username") String userName, @RequestBody PasswordDTO passwordDTO) {
        try {
            userService.updatePassword(userName, passwordDTO);
            return ResponseEntity.ok(SystemConstant.UPDATE_SUCCESS);
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUsers(@RequestBody long[] ids) {
        if (ids.length > 0) {
            userService.delete(ids);
        }
        return ResponseEntity.noContent().build();
    }

}
