class Customer {
    private int id;
    private String cusName;
    private int cusPhoneNum;

    public Customer(int id, String cusName, int cusPhoneNum){
        this.id = id;
        this.cusName = cusName;
        this.cusPhoneNum = cusPhoneNum;
     }
    public String summary(){
        return "[" + id + "]" + cusName +   " phone: " + cusPhoneNum;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){ this.id = id; }
    public String getCusName(){
        return cusName;
    }
    public void setCusName(String cusName){ this.cusName = cusName;}
    public int getCusPhoneNum(){
        return cusPhoneNum;
    }
    public void setCusPhoneNum(int cusPhoneNum){this.cusPhoneNum = cusPhoneNum; }
}

