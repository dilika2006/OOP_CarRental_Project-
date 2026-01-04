
public abstract class Car {
    private int id;
    private String brand;
    private String model;
    private int year;
    private String fuelType;
    private double dailyRate;
    private boolean isAvailable = true;
    public Car(int id, String brand, String model, int year, String fuelType, double dailyRate){
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.fuelType = fuelType;
        this.dailyRate = dailyRate;
    }
        
    public int getId(){return id;}
    public void setId(int id){ this.id = id; }
    public String getBrand(){ return brand;}
    public void setBrand(String brand){ this.brand = brand;}
    public String getModel(){ return model; }
    public void setModel(String model){ this.model = model;}
    public int getYear(){ return year; }
    public void setYear(int year){ this.year = year; }
    public String getFuelType(){ return fuelType; }
    public void setFuelType(String fuelType){this.fuelType = fuelType; }
    public double getDailyRate(){ return dailyRate; }
    public void setDailyRate(double dailyRate){this.dailyRate = dailyRate; }
    public boolean isAvailable(){
        return isAvailable;
    }
    public void setIsAvailable(boolean isAvailable){
        this.isAvailable = isAvailable;
    }
    
    // Subclasses must implement fee calculation given numbers of days
    public abstract double calculateRentalFee(int numDays);

    // numdays must be at least 1 .
    public double baseFee(int numDays){
        if(numDays <1) numDays= 1;
        return dailyRate*numDays;
    }
    
    public String summary(){
        return "[" + id + "]" + brand + " " + model + " (" + year + ") -" + fuelType + " -$" + dailyRate
            + "per day - available: " + isAvailable;
    }
    
}
