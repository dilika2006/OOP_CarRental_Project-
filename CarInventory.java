import java.util.ArrayList;
import java.util.List;

public class CarInventory {
    private List<Car> cars = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<Rental> rentals = new ArrayList<>();
    private List<Payment> payments = new ArrayList<>();

    private int nextCarId = 1;
    private int nextCusId = 1;
    private int nextRentalId = 1;
    private int nextPaymentId = 1;

    // ---- Car management ----
    public void addElectricCar(String brand, String model, int year, double dailyRate ) {
        ElectricCar c = new ElectricCar(nextCarId++, brand, model, year, dailyRate );
        cars.add(c);
    }

    public void addGasCar(String brand, String model, int year, double dailyRate) {
        GasCar c = new GasCar(nextCarId++, brand, model, year, dailyRate);
        cars.add(c);
    }

    public void addCustomer(String cusName, int cusPhoneNum) {
        Customer c = new Customer(nextCusId++, cusName, cusPhoneNum);
        customers.add(c);
    }

    public void removeCar(int carId) {
        for (Rental r : rentals) {
            if (r.getCarId() == carId && "active".equals(r.getStatus())) {
                throw new IllegalStateException("Cannot remove car: active rental exists");
            }
        }
        Car toRemove = null;
        for (Car c : cars) if (c.getId() == carId) { toRemove = c; break; }
        if (toRemove != null) cars.remove(toRemove);
    }

    // ---- Rental management ----
    // rentCarNow: create a rental that starts immediately and lasts numDays.
    public Rental rentCarNow(int customerId, int carId, int numDays) {
        // Validate customer
        Customer cust = null;
        for (Customer c : customers) if (c.getId() == customerId) { cust = c; break; }
        if (cust == null) throw new IllegalArgumentException("Customer not found: " + customerId);

        // Validate car
        Car car = null;
        for (Car c : cars) if (c.getId() == carId) { car = c; break; }
        if (car == null) throw new IllegalArgumentException("Car not found: " + carId);

        // Check availability (only immediate rentals supported)
        if (!car.isAvailable()) throw new IllegalStateException("Car not available right now");

        // Fee calculation uses number-of-days (polymorphic)
        double fee = car.calculateRentalFee(numDays);

        // Mock payment
         Payment pay = Payment.mockSuccess(nextPaymentId++, fee);
        payments.add(pay);

        // Create rental
        Rental r = new Rental(nextRentalId++, carId, customerId, numDays, fee, pay.getId());
        rentals.add(r);

        // Mark car unavailable while it has an active rental
        car.setIsAvailable(false);
        return r;
    }
     // returnCar now just marks returned and sets availability if no other active rentals
     public void returnCar(int rentalId) {
        Rental rent = null;
        for (Rental r : rentals) if (r.getId() == rentalId) { rent = r; break; }
        if (rent == null) throw new IllegalArgumentException("Rental not found: " + rentalId);
        if (!"active".equals(rent.getStatus())) throw new IllegalStateException("Rental not active");

        Car car = null;
        for (Car c : cars) if (c.getId() == rent.getCarId()) { car = c; break; }
        if (car == null) throw new IllegalArgumentException("Car for rental not found");

        rent.markReturned();

        // if no other active rentals for this car, mark available
        if (!carHasActiveRentals(car.getId())) car.setIsAvailable(true);
    }
     public boolean carHasActiveRentals(int carId) {
        for (Rental r : rentals) if (r.getCarId() == carId && "active".equals(r.getStatus())) return true;
        return false;
    }
     public void returnCarByCarId(int carId) {
        Rental found = null;
        for (Rental r : rentals) {
            if (r.getCarId() == carId && "active".equals(r.getStatus())) { found = r; break; }
        }
        if (found == null) throw new IllegalArgumentException("No active rental found for car id: " + carId);
        found.markReturned();

        // mark car available (there should be no other active rental for same car)
        for (Car c : cars) {
            if (c.getId() == carId) { c.setIsAvailable(true); break; }
        }
    }

    // ---- Printing helpers for CLI ----
    public void printAllCars() {
        if (cars.isEmpty()) { System.out.println("(no cars)"); return; }
        for (Car c : cars) System.out.println(c.summary());
    }
    
    public void printAvailableCars() {
        boolean found = false;
        for (Car c : cars) {
            if (!c.isAvailable()) continue;
            System.out.println(c.summary());
            found = true;
        }
        if (!found) System.out.println("(no available cars right now)");
    }

   public void printSearch(String brand, String fuelType) {
        boolean found = false;
        for (Car c : cars) {
            if (brand != null && !brand.isEmpty() && !c.getBrand().equals(brand)) continue; // exact match
            if (fuelType != null && !fuelType.isEmpty() && !c.getFuelType().equals(fuelType)) continue; // exact match
            System.out.println(c.summary());
            found = true;
        }
        if (!found) System.out.println("(no matches)");
    }

    public void printCustomers() {
        if (customers.isEmpty()) { System.out.println("(no customers)"); return; }
        for (Customer c : customers) System.out.println(c.summary());
    }

    public void printActiveRentals() {
        boolean found = false;
        for (Rental r : rentals) {
            if ("active".equals(r.getStatus())) { System.out.println(r.summary()); found = true; }
        }
        if (!found) System.out.println("(no active rentals)");
    }

    public void printAllRentals() {
        if (rentals.isEmpty()) { System.out.println("(no rentals)"); return; }
        for (Rental r : rentals) System.out.println(r.summary());
    }

    public int customersCount() { return customers.size(); }

    //  Sample data
    public void createSampleData() {
        addElectricCar("Tesla", "Model 3", 2021, 70.0);
        addGasCar("Toyota", "Corolla", 2019, 35.0);
        addCustomer("Alice Example", 5550101);
    }
}
