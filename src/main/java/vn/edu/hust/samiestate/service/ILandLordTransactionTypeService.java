package vn.edu.hust.samiestate.service;

import vn.edu.hust.samiestate.dto.LandLordTransactionTypeDTO;

import java.util.List;
import java.util.Map;

public interface ILandLordTransactionTypeService {
    Map<String, String> getTransactionType();
    List<LandLordTransactionTypeDTO> getTransactionTypeList();
    void save(LandLordTransactionTypeDTO landLordTransactionTypeDTO);
    void delete(long[] ids);
}
