package vn.edu.hust.samiestate.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.hust.samiestate.dto.request.TransactionRequest;
import vn.edu.hust.samiestate.dto.response.TransactionResponse;
import vn.edu.hust.samiestate.entity.TransactionEntity;
import vn.edu.hust.samiestate.utils.DateUtils;

@Component
public class TransactionConverter {

    @Autowired
    private ModelMapper modelMapper;

    public TransactionResponse convertToResponse(TransactionEntity entity) {

        TransactionResponse result = modelMapper.map(entity, TransactionResponse.class);

        //createdDate
        result.setCreatedDate(DateUtils.convertDateToString(entity.getCreatedDate()));

        //staffName
        result.setStaffName(entity.getUser().getFullName());

        //transactionCode
        result.setTransactionCode(entity.getTransactionType().getCode());

        return result;
    }

    public TransactionEntity convertToEntity(TransactionRequest request) {

        TransactionEntity result = modelMapper.map(request, TransactionEntity.class);

        return result;
    }
}
