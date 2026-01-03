public class GasCar extends Car{
    public GasCar(int carId, String brand, String model, int year, double dailyRate){
        super(carId, brand, model, year, "gas", dailyRate);
    }

    @Override
    public double calculateRentalFee(int numDays){
        return baseFee(numDays);
    }
    
}
