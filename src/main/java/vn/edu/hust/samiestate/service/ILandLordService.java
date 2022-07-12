package vn.edu.hust.samiestate.service;

import org.springframework.data.domain.Pageable;
import vn.edu.hust.samiestate.dto.LandLordDTO;
import vn.edu.hust.samiestate.dto.request.LandLordSearchRequest;
import vn.edu.hust.samiestate.dto.request.TransactionRequest;
import vn.edu.hust.samiestate.dto.response.LandLordSearchResponse;
import vn.edu.hust.samiestate.dto.response.StaffAssignResponse;
import vn.edu.hust.samiestate.dto.response.TransactionResponse;

import java.util.List;
import java.util.Map;

public interface ILandLordService {
    LandLordDTO save(LandLordDTO landLordDTO);
    LandLordDTO findLandLordById(Long id);
    Map<String, String> getLandLordStatus();
    List<StaffAssignResponse> getStaffsOfLandLord(Long landLordId);
    List<LandLordSearchResponse> getLandlords(LandLordSearchRequest request, Pageable pageable);
    int getTotalItems(LandLordSearchRequest request);
    void delete(long[] ids);
    List<TransactionResponse> getTransactionsOfLandLord(Long landLordId);
    void createTransaction(Long landLordId, TransactionRequest request);
}
