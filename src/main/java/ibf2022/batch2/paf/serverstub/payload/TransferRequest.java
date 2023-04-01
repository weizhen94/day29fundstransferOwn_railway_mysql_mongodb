package ibf2022.batch2.paf.serverstub.payload;

public class TransferRequest {

    private Integer accountFrom; 

    private Integer accountTo; 

    private Float amount;

    public TransferRequest() {
    }

    public TransferRequest(Integer accountFrom, Integer accountTo, Float amount) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
    }

    public Integer getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(Integer accountFrom) {
        this.accountFrom = accountFrom;
    }

    public Integer getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Integer accountTo) {
        this.accountTo = accountTo;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    // Add a static factory method to parse amount from string
    public static TransferRequest fromFormParams(Integer accountFrom, Integer accountTo, Float amount) {
        TransferRequest request = new TransferRequest();
        request.setAccountFrom(accountFrom);
        request.setAccountTo(accountTo);
        request.setAmount(amount);
        return request;
    }
    
}
