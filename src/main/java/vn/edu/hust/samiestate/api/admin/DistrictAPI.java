package vn.edu.hust.samiestate.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hust.samiestate.dto.DistrictDTO;
import vn.edu.hust.samiestate.service.impl.DistrictService;

@RestController
@RequestMapping("/api/district")
public class DistrictAPI {

    @Autowired
    private DistrictService districtService;

    @PostMapping
    public void createDistrict(@RequestBody DistrictDTO districtDTO) {
        try {
            districtService.save(districtDTO);
        } catch (Exception e) {
            throw e;
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteDistrict(@RequestBody long[] idList) {
        if (idList.length > 0) {
            districtService.delete(idList);
        }
        return ResponseEntity.noContent().build();
    }
}
