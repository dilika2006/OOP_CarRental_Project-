public  class ElectricCar extends Car {
    public ElectricCar(int carId, String brand, String model, int year, double dailyRate){
        super(carId, brand, model, year,"electric", dailyRate);
    }

    @Override
    public double calculateRentalFee(int numDays){
        return baseFee(numDays);
    }
    
}
