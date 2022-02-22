package vn.edu.hust.samiestate.service;

import org.springframework.data.domain.Pageable;
import vn.edu.hust.samiestate.dto.PasswordDTO;
import vn.edu.hust.samiestate.dto.UserDTO;
import vn.edu.hust.samiestate.dto.response.UserSearchResponse;
import vn.edu.hust.samiestate.exception.PasswordFailException;

import java.util.List;
import java.util.Map;

public interface IUserService {

    UserDTO findOneByUserNameAndStatus(String name, int status);
    List<UserSearchResponse> getUsers(String searchValue, Pageable pageable);
    int getTotalItems(String searchValue);
    UserDTO insert(UserDTO newUser);
    UserDTO update(Long id, UserDTO updatedUser);
    UserDTO findUserById(long id);
    UserDTO findOneByUserName(String userName);
    UserDTO updateProfileOfUser(String userName, UserDTO userDTO);
    void updatePassword(String userName, PasswordDTO passwordDTO) throws PasswordFailException;
    void delete(long[] ids);
    UserDTO resetPassword(long id);
    Map<Long, String> getStaffMaps();
}
