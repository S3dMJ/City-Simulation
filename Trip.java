package application;


import java.util.ArrayList;
import java.util.Random;

public class Trip {
    double Pollution;
    double P_of_CONV;
    private char name;
    double cost = 0;
    double time = 0;
    int NumberOfSteps;
    double TotalFuel;

    ArrayList<CommuteMethods> ArrayOfSteps = new ArrayList<>();
    //CommuteMethods ArrayOfSteps;
//double [] TimePerStep;
//double [] CostPerStep;
    double distancePerStep;
    double conv;

    public Trip(char name, double distance) {
        
        this.setName(name);
        //int r= new java.util.Random().nextInt(3)+1;
        NumberOfSteps = new java.util.Random().nextInt(4) +1;
        int c = 65;
        char ch[] = new char[NumberOfSteps];
        int i = 0;
        distancePerStep = distance / (double) NumberOfSteps;
        while (i < NumberOfSteps) {
            ch[i] = ((char) (c + i));
            i++;
        }
        for (int j = 0; j < NumberOfSteps; j++) {
            ArrayOfSteps.add(generateSteps(new java.util.Random().nextInt(6) + 1, ch[j], distancePerStep));
        }
//		checking for repetition
        for (int i1 = 0; i1 < ArrayOfSteps.size() - 1; i1++) {

            if (ArrayOfSteps.get(i1).toString().contains(ArrayOfSteps.get(i1 + 1).toString().split("")[0]) && ArrayOfSteps.get(i1).toString().contains(ArrayOfSteps.get(i1 + 1).toString().split("")[1])) {

                {
                    ArrayOfSteps.remove(i1 + 1);
                    ArrayOfSteps.add(generateSteps(new java.util.Random().nextInt(6) + 1, ch[i1 + 1], distancePerStep));
                }

                --i1;
            }

        }
        for (int j = 0; j < ArrayOfSteps.size(); j++) {
            cost += ArrayOfSteps.get(j).Cost;
            time += ArrayOfSteps.get(j).timePerStep;
            Pollution += ArrayOfSteps.get(j).P_of_CO2;
            TotalFuel += ArrayOfSteps.get(j).FuelConsumptions;
        }
        this.P_of_CONV = convenience();
    }


    public CommuteMethods generateSteps(int r, char name, double distance) {
        CommuteMethods Method = null;
        switch (r) {
            case 1:
            case 5:
            case 6:
                Method = new Car(name, distance);
                break;
            case 2:
                Method = new Bus(name, distance);
                break;
            case 3:
                Method = new Bicycle(name, distance);
                break;
            case 4:
                Method = new Metro(name, distance);
                break;
            default:
                break;
        }
        return Method;
    }

    //	calculating convenience
    public double convenience() {


        for (CommuteMethods i : ArrayOfSteps) {
            if (i instanceof Car) {
                conv += 80;

            } else if (i instanceof Metro) {
                conv += 50;

            } else if (i instanceof Bus) {
                conv += 35;

            } else if (i instanceof Bicycle) {
                conv += 15;
              
            }


        }
        return (double)((conv /(ArrayOfSteps.size()*100))*100)-5*ArrayOfSteps.size();
        //(double)((conv /(ArrayOfSteps.size()*100))*100)
    }


    public String toString() {
        return " ";
    }

    public char getName() {
        return name;
    }

    public void setName(char name) {
        this.name = name;
    }
}
