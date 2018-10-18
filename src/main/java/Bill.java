public class Bill {

    private BillType type ;
    private String billId ;
    private String paymentId ;
    private long amount ;

    public Bill(){

    }

    public String getBillId() {
        return billId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public BillType getType() {
        return type;
    }

    public long getAmount() {
        return amount;
    }

    public Bill setBillId(String billId) {
        this.billId = billId;
        return this;
    }

    public Bill setPaymentId(String paymentId) {
        this.paymentId = paymentId;
        return this ;
    }

    public Bill setType(BillType type) {
        this.type = type;
        return this ;
    }

    public Bill setAmount(long amount) {
        this.amount = amount;
        return this ;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "type=" + type.toString() +
                ", billId='" + billId + '\'' +
                ", paymentId='" + paymentId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
