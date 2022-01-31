package vn.edu.hust.samiestate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hust.samiestate.converter.RoleConverter;
import vn.edu.hust.samiestate.dto.RoleDTO;
import vn.edu.hust.samiestate.entity.RoleEntity;
import vn.edu.hust.samiestate.repository.RoleRepository;
import vn.edu.hust.samiestate.service.IRoleService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleConverter roleConverter;

    @Override
    public Map<String, String> getRoles() {
        Map<String,String> result = new HashMap<>();
        List<RoleEntity> roleEntity = roleRepository.findAll();
        roleEntity.forEach(entity -> {
            RoleDTO roleDTO = roleConverter.convertToDto(entity);
            result.put(roleDTO.getCode(), roleDTO.getName());
        });
        return result;
    }
}
