package vn.edu.hust.samiestate.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.hust.samiestate.constant.SystemConstant;
import vn.edu.hust.samiestate.converter.UserConverter;
import vn.edu.hust.samiestate.dto.PasswordDTO;
import vn.edu.hust.samiestate.dto.UserDTO;
import vn.edu.hust.samiestate.dto.response.UserSearchResponse;
import vn.edu.hust.samiestate.entity.RoleEntity;
import vn.edu.hust.samiestate.entity.UserEntity;
import vn.edu.hust.samiestate.exception.NotFoundException;
import vn.edu.hust.samiestate.exception.PasswordFailException;
import vn.edu.hust.samiestate.repository.RoleRepository;
import vn.edu.hust.samiestate.repository.UserRepository;
import vn.edu.hust.samiestate.service.IUserService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDTO findOneByUserNameAndStatus(String name, int status) {
        return userConverter.convertToDto(userRepository.findOneByUserNameAndStatus(name, status));
    }

    @Override
    public List<UserSearchResponse> getUsers(String searchValue, Pageable pageable) {
        Page<UserEntity> users = null;
        if (StringUtils.isNotBlank(searchValue)) {
            users = userRepository.findByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseAndStatusNot(searchValue, searchValue, 0, pageable);
        } else {
            users = userRepository.findByStatusNot(0, pageable);
        }
        List<UserSearchResponse> result = users.getContent().stream().map(item -> userConverter.convertToSearchResponse(item))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public int getTotalItems(String searchValue) {
        int totalItem = 0;
        if (StringUtils.isNotBlank(searchValue)) {
            totalItem = (int) userRepository.countByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseAndStatusNot(searchValue, searchValue, 0);
        } else {
            totalItem = (int) userRepository.countByStatusNot(0);
        }
        return totalItem;
    }

    @Override
    @Transactional
    public UserDTO insert(UserDTO newUser) {
        if (Objects.nonNull(newUser)) {
            RoleEntity roleFound = Optional.ofNullable(roleRepository.findOneByCode(newUser.getRoleCode()))
                    .orElseThrow(() -> new NotFoundException(SystemConstant.ROLE_USER_NOT_FOUND));
            UserEntity userEntity = userConverter.convertToEntity(newUser);
            userEntity.setRoles(Stream.of(roleFound).collect(Collectors.toList()));
            userEntity.setStatus(1);
            userEntity.setPassword(passwordEncoder.encode(SystemConstant.PASSWORD_DEFAULT));
            return userConverter.convertToDto(userRepository.save(userEntity));
        }
        return null;
    }

    @Override
    @Transactional
    public UserDTO update(Long id, UserDTO updatedUser) {
        if (Objects.nonNull(updatedUser)) {
            RoleEntity roleFound = Optional.ofNullable(roleRepository.findOneByCode(updatedUser.getRoleCode()))
                    .orElseThrow(() -> new NotFoundException(SystemConstant.ROLE_USER_NOT_FOUND));
            Optional<UserEntity> userFoundOptional = Optional.ofNullable(userRepository.findById(id))
                    .orElseThrow(() -> new NotFoundException(SystemConstant.USER_NOT_FOUND));
            UserEntity userEntity = userConverter.convertToEntity(updatedUser);
            userEntity.setUserName(userFoundOptional.get().getUserName());
            userEntity.setPassword(userFoundOptional.get().getPassword());
            userEntity.setRoles(Stream.of(roleFound).collect(Collectors.toList()));
            return userConverter.convertToDto(userRepository.save(userEntity));
        }
        return null;
    }

    @Override
    public UserDTO findUserById(long id) {
        Optional<UserEntity> userFoundOptional = Optional.ofNullable(userRepository.findById(id))
                .orElseThrow(() -> new NotFoundException(SystemConstant.USER_NOT_FOUND));
        RoleEntity role = userFoundOptional.get().getRoles().get(0);
        UserDTO dto = userConverter.convertToDto(userFoundOptional.get());
        dto.setRoleCode(role.getCode());
        return dto;
    }

    @Override
    public UserDTO findOneByUserName(String userName) {
        UserEntity userFound = userRepository.findOneByUserName(userName);
        UserDTO userDTO = userConverter.convertToDto(userFound);
        return userDTO;
    }

    @Override
    @Transactional
    public UserDTO updateProfileOfUser(String userName, UserDTO userDTO) {
        UserEntity userFound = Optional.ofNullable(userRepository.findOneByUserName(userName))
                .orElseThrow(() -> new NotFoundException(SystemConstant.USER_NOT_FOUND));
        UserEntity userEntity = userConverter.convertToEntity(userDTO);
        userEntity.setUserName(userFound.getUserName());
        userEntity.setRoles(userFound.getRoles());
        userEntity.setStatus(userFound.getStatus());
        userEntity.setPassword(userFound.getPassword());
        return userConverter.convertToDto(userRepository.save(userEntity));
    }

    @Override
    @Transactional
    public void updatePassword(String userName, PasswordDTO passwordDTO) {
        UserEntity userFoundOptional = Optional.ofNullable(userRepository.findOneByUserName(userName))
                .orElseThrow(() -> new NotFoundException(SystemConstant.USER_NOT_FOUND));
        if (passwordEncoder.matches(passwordDTO.getOldPassword(), userFoundOptional.getPassword())
                && passwordDTO.getNewPassword().equals(passwordDTO.getConfirmPassword())) {
            userFoundOptional.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
            userRepository.save(userFoundOptional);
        } else {
            throw new PasswordFailException(SystemConstant.CHANGE_PASSWORD_FAIL);
        }
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        userRepository.deleteByIdIn(ids);
    }

    @Override
    @Transactional
    public UserDTO resetPassword(long id) {
        Optional<UserEntity> userFoundOptional = Optional.ofNullable(userRepository.findById(id))
                .orElseThrow(() -> new NotFoundException(SystemConstant.USER_NOT_FOUND));
        userFoundOptional.get().setPassword(passwordEncoder.encode(SystemConstant.PASSWORD_DEFAULT));
        return userConverter.convertToDto(userRepository.save(userFoundOptional.get()));
    }

}
