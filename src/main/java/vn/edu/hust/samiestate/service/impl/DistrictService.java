package vn.edu.hust.samiestate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hust.samiestate.entity.DistrictEntity;
import vn.edu.hust.samiestate.repository.DistrictRepository;
import vn.edu.hust.samiestate.service.IDistrictService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DistrictService implements IDistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public Map<String, String> getDistrict() {
        Map<String, String> results = new HashMap<>();

        List<DistrictEntity> districtEntityList = districtRepository.findAll();

        for (DistrictEntity item: districtEntityList) {
            results.put(item.getCode(), item.getName());
        }

        return results;
    }
}
