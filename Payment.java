public class Payment {
    private int id;
    private double amount;
    private String status;

    public Payment(int id, double amount, String status){
        this.id = id; this.amount = amount; this.status = status;
    }

    public static Payment mockSuccess(int id, double amount){
        return new Payment(id, amount, "succeded");
    }
    public int getId(){ return id; }
    public void setId(int id){this.id = id;}
    public double getAmount(){ return amount; }
    public String getStatus(){ return status; }
}
