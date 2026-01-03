import java.util.Scanner;

public class CarRentalSystem{
     public static void main(String[] args) {
        CarInventory inv = new CarInventory();
        inv.createSampleData();

        Scanner sc = new Scanner(System.in);

        System.out.println("Very simple Car Rental System.");
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1) List all cars");
            System.out.println("2) Add car");
            System.out.println("3) Remove car");
            System.out.println("4) Add customer");
            System.out.println("5) Rent car (starts now) — enter number of days only");
            System.out.println("6) Return car (by car id)");
            System.out.println("7) Search cars (brand/fuel)");
            System.out.println("8) Show rentals");
            System.out.println("0) Exit");
            System.out.print("Choose: ");
            String choice = sc.nextLine();

            try {
                switch (choice) {
                    case "1":
                        inv.printAllCars();
                        break;
                    case "2": {
                        System.out.print("Brand: "); String brand = sc.nextLine();
                        System.out.print("Model: "); String model = sc.nextLine();
                        System.out.print("Year (e.g. 2021): "); int year = Integer.parseInt(sc.nextLine());
                        System.out.print("Fuel (type exactly 'electric' or 'gas9' ): ");
                        String fuel = sc.nextLine();
                        System.out.print("Daily rate (e.g. 40.0): "); double rate = Double.parseDouble(sc.nextLine());
                        if ("electric".equals(fuel)) {
                            inv.addElectricCar(brand, model, year, rate);
                        } else {
                            inv.addGasCar(brand, model, year, rate);
                        }
                        System.out.println("Car added.");
                        break;
                    }
                    case "3": {
                        System.out.print("Car id to remove: ");
                        int id = Integer.parseInt(sc.nextLine());
                        inv.removeCar(id);
                        System.out.println("Removed if existed and had no active rentals.");
                        break;
                    }
                    case "4": {
                        System.out.print("Customer name: "); String cusName = sc.nextLine();
                        System.out.print("Phone: "); int cusPhoneNum = sc.nextInt();
                        inv.addCustomer(cusName, cusPhoneNum);
                        System.out.println("Customer added.");
                        break;
                    }
                    case "5": {
                        if (inv.customersCount() == 0) { System.out.println("No customers — add one first."); break; }
                        System.out.println("Customers:");
                        inv.printCustomers();
                        System.out.print("Customer id: "); int custId = Integer.parseInt(sc.nextLine());
                        System.out.println("Cars:");
                        inv.printAllCars();
                        System.out.print("Car id: "); int carId = Integer.parseInt(sc.nextLine());
                        System.out.print("Number of days to rent (starts now): "); int numDays = Integer.parseInt(sc.nextLine());
                        Rental r = inv.rentCarNow(custId, carId, numDays);
                        System.out.println("Rented! Rental id: " + r.getId() +  " | Total fee: $" + r.getTotalFee());
                        break;
                    }
                    case "6": {
                        System.out.println("Cars:");
                        inv.printAllCars();
                        System.out.print("Enter car id to return: ");
                        int carId = Integer.parseInt(sc.nextLine());
                        inv.returnCarByCarId(carId);
                        System.out.println("Returned car " + carId);
                        break;
                    }
                    case "7": {
                        System.out.print("Brand filter: "); String brand = sc.nextLine();
                        System.out.print("Fuel filter (8type exactly 'electric' or 'gas'): "); String fuel = sc.nextLine();
                        inv.printSearch(brand.isEmpty() ? null : brand, fuel.isEmpty() ? null : fuel);
                        break;
                    }
                    case "8":
                        inv.printAllRentals();
                        break;
                    case "0":
                        System.out.println("Goodbye");
                        sc.close();
                        return;
                    default:
                        System.out.println("Unknown choice.");
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }
}

     