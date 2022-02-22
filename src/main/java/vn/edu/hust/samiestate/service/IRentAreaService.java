package vn.edu.hust.samiestate.service;

import vn.edu.hust.samiestate.dto.RentAreaDTO;

public interface IRentAreaService {

    RentAreaDTO getRentAreaById(Long rentAreaId);
    void saveRentArea(RentAreaDTO rentAreaDTO);
    void delete(long[] ids);
}
