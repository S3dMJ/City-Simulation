package application;

import java.util.ArrayList;

public class Route {
    int NumberOfTrips;
    ArrayList<Trip> ArrayOfTrips = new ArrayList<>();
    private String name;
    double distance = 0;
    Station currentStation;
    Station nextStation;

    public Route(String name, Station currentStation, Station nextStation) {
        this.currentStation = currentStation;
        this.nextStation = nextStation;
        distance = new java.util.Random().nextInt(30) + 10;
        NumberOfTrips = new java.util.Random().nextInt(5) + 3;
        this.setName(name);
        int c = 65;
        char ch[] = new char[NumberOfTrips];
        int i = 0;
        while (i < NumberOfTrips) {
            ch[i] = ((char) (c + i));
            i++;
        }
        for (int j = 0; j < NumberOfTrips; j++) {
            ArrayOfTrips.add(new Trip(ch[j], distance));
        }


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
