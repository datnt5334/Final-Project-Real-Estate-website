package vn.edu.hust.samiestate.api.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hust.samiestate.dto.LandLordDTO;
import vn.edu.hust.samiestate.dto.request.TransactionRequest;
import vn.edu.hust.samiestate.service.ILandLordService;

@RestController
@RequestMapping("/api/landlord")
public class LandLordAPI {

    private final ILandLordService landLordService;

    public LandLordAPI(ILandLordService landLordService) {
        this.landLordService = landLordService;
    }

    @PostMapping
    public LandLordDTO createLandLord(@RequestBody LandLordDTO landLordDTO) {
        try {
            return landLordService.save(landLordDTO);
        } catch (Exception e) {
            throw e;
        }
    }

    @PutMapping("/{id}")
    public LandLordDTO updateLandLord(@RequestBody LandLordDTO landLordDTO) {
        try {
            return landLordService.save(landLordDTO);
        } catch (Exception e) {
            throw e;
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteLandLord(@RequestBody long[] idList) {
        if (idList.length > 0) {
            landLordService.delete(idList);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{landLordId}/transaction")
    public void createTransaction(@PathVariable("landLordId") Long landLordId,
                                  @RequestBody TransactionRequest request) {
        try {
            landLordService.createTransaction(landLordId, request);
        } catch (Exception e) {
            throw e;
        }
    }
}
