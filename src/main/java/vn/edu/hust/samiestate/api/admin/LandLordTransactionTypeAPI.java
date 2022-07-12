package vn.edu.hust.samiestate.api.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hust.samiestate.dto.LandLordTransactionTypeDTO;
import vn.edu.hust.samiestate.service.impl.LandLordTransactionTypeService;

@RestController
@RequestMapping("/api/landlord-transactionType")
public class LandLordTransactionTypeAPI {

    private final LandLordTransactionTypeService landLordTransactionTypeService;

    public LandLordTransactionTypeAPI(LandLordTransactionTypeService landLordTransactionTypeService) {
        this.landLordTransactionTypeService = landLordTransactionTypeService;
    }

    @PostMapping
    public void createTransactionType(@RequestBody LandLordTransactionTypeDTO landLordTransactionTypeDTO) {
        try {
            landLordTransactionTypeService.save(landLordTransactionTypeDTO);
        } catch (Exception e) {
            throw e;
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTransactionType(@RequestBody long[] idList) {
        if (idList.length > 0) {
            landLordTransactionTypeService.delete(idList);
        }
        return ResponseEntity.noContent().build();
    }
}
