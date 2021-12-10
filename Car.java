package application;



public class Car extends CommuteMethods {

    public Car(char c, double distance) {
        name = c;

        FuelConsumptions = (13.4 / 100) * distance;
        //Litter of petrol per 1 km

        P_of_CO2 = 2.31 * FuelConsumptions;
        // Kg per 1 Litter of petrol

        hindranceChance = 1 / 108.0;
        // deaths per accident

        double CostPerKm = 1.75;
        // in riyals
        //Changed from Cost per minute to Cost per km while assuming that the efficiency is 10.64
        // has to be multiplied by the distance to find actual cost

        //double distance = distance;
        //temporary variable should be route.getDistance()

        this.distance = distance;
        Cost = CostPerKm * distance;
        // actual cost in riyals

        timePer1km = 0.6 + (rand.nextInt(6) / 10.0);
        timePerStep = timePer1km * distance;
    }

    //    @Override
//    public void start() {
//
//    }
    public String toString() {
        return String.format("Car %5s", " ");
    }
}
