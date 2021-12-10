package application;

import java.util.ArrayList;

public class Commuter {
    static int[] index = new int[4];
    static ArrayList<String> All_Ids = new ArrayList<>();
    String ID;
    double ETA;
    double costs;
    double WeeklyBudget;
    double Priority;
    Route route;
    int TripChoice = 0;
    double timeOfCreation;

    public Commuter(Route route,double time) {
    	timeOfCreation=time;
        ID = generate_ID(index);
        this.route = route;

        this.ID = String.format("%s", ID);
        WeeklyBudget = new java.util.Random().nextInt(3000) + 500;
        
        
   TripChoice = new java.util.Random().nextInt(route.NumberOfTrips);

//       for (int i=0; i<route.NumberOfTrips;i++) {
//   		if(route.ArrayOfTrips.get(i).conv>Conv) {
//   			TripChoice=i;
//   			Conv=route.ArrayOfTrips.get(TripChoice).conv;}}
       
       
//       while(true){
//       TripChoice = new java.util.Random().nextInt(route.NumberOfTrips);
//       if(route.ArrayOfTrips.get(TripChoice).P_of_CONV<40)
//        TripChoice = new java.util.Random().nextInt(route.NumberOfTrips);
//       else
//       	break;}
        ETA = route.ArrayOfTrips.get(TripChoice).time;
        costs = route.ArrayOfTrips.get(TripChoice).cost;
    }

    static String generate_ID(int[] gen_id) {
        StringBuffer sb = new StringBuffer();
        String id = "";
        String past = "";
        for (int i = gen_id.length - 1; i > 0; i--) {
            if (gen_id[i] == 9) {
                gen_id[i] = 0;
                continue;
            } else {
                gen_id[i] += 1;
                break;
            }
        }
        for (int j = 0; j < gen_id.length; j++) {
            sb.append(Integer.toString(gen_id[j]));
        }
        All_Ids.add(sb.toString());
        return sb.toString();

    }

}
