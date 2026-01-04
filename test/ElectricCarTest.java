package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ElectricCarTest {

    @Test
    public void testElectricCarBaseFee() {
        ElectricCar car = new ElectricCar(
            1,
            "Tesla",
            "Model 3",
            2022,
            200,
            true,
            50
        );

        double fee = car.baseFee(3);
        assertEquals(600, fee);
    }

    @Test
    public void testElectricCarAvailability() {
        ElectricCar car = new ElectricCar(
            2,
            "Tesla",
            "Model Y",
            2023,
            250,
            true,
            60
        );

        assertTrue(car.isAvailable());
    }
}