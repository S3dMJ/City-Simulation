package application;


public class Bicycle extends CommuteMethods {

//    @Override
//    public void start() {
//
//    }

    public Bicycle(char c, double distance) {
        name = c;
        FuelConsumptions = 0;
        //PassengersCapacity = 0;
        P_of_CO2 = 0;

        hindranceChance = 1 / 71.0;
        // deaths per accident
        this.distance = distance;
        Cost = 0 * distance;
        timePer1km = 2 + (rand.nextInt(5) / 10.0);
        timePerStep = timePer1km * distance;
    }

    public String toString() {
        return String.format("Bycicle  ", name);
    }

}
