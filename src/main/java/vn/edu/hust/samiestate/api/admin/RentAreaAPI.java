package vn.edu.hust.samiestate.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hust.samiestate.dto.RentAreaDTO;
import vn.edu.hust.samiestate.service.IRentAreaService;

@RestController
@RequestMapping("/api/rentarea")
public class RentAreaAPI {

    @Autowired
    private IRentAreaService rentAreaService;

    @GetMapping("/{id}")
    public RentAreaDTO getRentArea(@PathVariable("id") Long id) {
        try {
            return rentAreaService.getRentAreaById(id);
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping
    public void createRentArea(@RequestBody RentAreaDTO rentAreaDTO) {
        try {
            rentAreaService.saveRentArea(rentAreaDTO);
        } catch (Exception e) {
            throw e;
        }
    }

    @PutMapping
    public void updateRentArea(@RequestBody RentAreaDTO rentAreaDTO) {
        try {
            rentAreaService.saveRentArea(rentAreaDTO);
        } catch (Exception e) {
            throw e;
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteRentArea(@RequestBody long[] idList) {
        if (idList.length > 0) {
            rentAreaService.delete(idList);
        }
        return ResponseEntity.noContent().build();
    }
}
