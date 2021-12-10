package application;


public class Metro extends CommuteMethods {

    public Metro(char c, double distance) {
        name = c;

        FuelConsumptions = 0;

        P_of_CO2 = 0.0087 * distance;

        hindranceChance = 3 / 1_948.0;
        // deaths per accident

        this.distance = distance;
        if (distance<=6)
            Cost = 1.55;
        else if(distance<=11)
        	Cost=1.82;
        else if(distance<=19)
        	Cost=2.28;
        else if(distance<=27)
        	Cost=2.64;
        else if(distance>27)
        	Cost=2.92;

        
        //Cost per swipe

        timePer1km = 1.5 + (rand.nextInt(5) / 10.0);
        timePerStep = timePer1km * distance;
    }

    //    @Override
//    public void start() {
//
//    }
    public String toString() {
        return String.format("Metro %3s", " ");
    }
}
