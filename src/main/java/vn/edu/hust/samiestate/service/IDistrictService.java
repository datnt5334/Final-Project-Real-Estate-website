package vn.edu.hust.samiestate.service;

import vn.edu.hust.samiestate.dto.DistrictDTO;

import java.util.List;
import java.util.Map;

public interface IDistrictService {

    Map<String, String> getDistrict();
    List<DistrictDTO> getDistrictList();
    void save(DistrictDTO districtDTO);
    void delete(long[] ids);
}
