package vn.edu.hust.samiestate.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import vn.edu.hust.samiestate.dto.request.TransactionRequest;
import vn.edu.hust.samiestate.dto.response.TransactionResponse;
import vn.edu.hust.samiestate.entity.CustomerTransactionEntity;
import vn.edu.hust.samiestate.entity.LandLordTransactionEntity;
import vn.edu.hust.samiestate.utils.DateUtils;

@Component
public class TransactionConverter {

    private final ModelMapper modelMapper;

    public TransactionConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TransactionResponse convertToCustomerResponse(CustomerTransactionEntity entity) {

        TransactionResponse result = modelMapper.map(entity, TransactionResponse.class);

        //createdDate
        result.setCreatedDate(DateUtils.convertDateToString(entity.getCreatedDate()));

        //staffInfo
        String staffInfo = String.format("%s - %s", entity.getUser().getFullName(), entity.getUser().getEmployeeCode());
        result.setStaffInfo(staffInfo);

        //transactionCode
        result.setTransactionCode(entity.getTransactionType().getCode());

        return result;
    }

    public TransactionResponse convertToLandLordResponse(LandLordTransactionEntity entity) {

        TransactionResponse result = modelMapper.map(entity, TransactionResponse.class);

        //createdDate
        result.setCreatedDate(DateUtils.convertDateToString(entity.getCreatedDate()));

        //staffInfo
        String staffInfo = String.format("%s - %s", entity.getUser().getFullName(), entity.getUser().getEmployeeCode());
        result.setStaffInfo(staffInfo);

        //transactionCode
        result.setTransactionCode(entity.getTransactionType().getCode());

        return result;
    }

    public CustomerTransactionEntity convertToCustomerEntity(TransactionRequest request) {

        CustomerTransactionEntity result = modelMapper.map(request, CustomerTransactionEntity.class);

        return result;
    }

    public LandLordTransactionEntity convertToLandLordEntity(TransactionRequest request) {

        LandLordTransactionEntity result = modelMapper.map(request, LandLordTransactionEntity.class);

        return result;
    }
}
