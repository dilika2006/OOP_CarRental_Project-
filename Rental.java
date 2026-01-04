class Rental {
    private int id;
    private int carId;
    private int cusId;
    private int days;
    private double totalFee;
    private int paymentId;
    private String status;

    public Rental(int id, int carId, int cusId, int days, double totalFee, int paymentId){
       this.id = id; this.carId = carId; this.cusId = cusId; this.totalFee = totalFee; 
          this.paymentId = paymentId; this.status = "active";
    } 

    public String summary(){
        return "[" + id + "] car=" + carId + " cust=" + cusId + " days=" + days +" status=" + status + " fee=$" + totalFee;
    }

    public void markReturned(){
        this.status= "returned";
    }
    public void markCancelled(){
        this.status = "cancelled";
    }
    public int getId() { return id; }
    public void setId(int id){this.id = id;}
    public int getCarId() { return carId; }
    public int getCusId() { return cusId; }
    public int getDays() { return days; }
    public double getTotalFee() { return totalFee; }
    public int getPaymentId() { return paymentId; }
    public String getStatus() { return status; }
}



