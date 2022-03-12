package vn.edu.hust.samiestate.dto.request;

public class TransactionRequest {

    private String note;
    private String transactionCode;


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }
}
