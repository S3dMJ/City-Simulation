package application;



public class Bus extends CommuteMethods {

    public Bus(char c, double distance) {
        name = c;

        FuelConsumptions = ((27.8 / 100) * distance) / 40;
        //Litter of diesel per 1 km

        P_of_CO2 = 2.31 * FuelConsumptions;
        // Kg per 1 km per person

        hindranceChance = 1 / 1000.0;
        // deaths per accident

        Cost = 0.92;
        //saptco price
        //average daily membership cost

        this.distance = distance;
        timePer1km = 1.5 + (rand.nextInt(5) / 10.0);
        timePerStep = timePer1km * distance;

    }

    //    @Override
//    public void start() {
//
//
//    }
    public String toString() {
        return String.format("Bus %5s", " ");
    }

}
