package application;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;
import javafx.stage.Stage;

public class City_with_GUI extends Application {
    static ArrayList<Commuter> cTotal_Gen = new ArrayList<>();
    int phase=1;
    int Type=1;
   static Station A = new Station("A");
   static  Station B = new Station("B");
   static ArrayList<Route> Routes = new ArrayList<>();
   static Route route1 = new Route("First  ", A, B);
   static Route route2 = new Route("Second ", A, B);
   static  Route route3 = new Route("Third  ", B, A);
   static Route route4 = new Route("Fourth ", A, B);
   static Route route5 = new Route("Fifth  ", B, A);
   static Route route6 = new Route("Sixth  ", A, B);
   static Route route7 = new Route("Seventh", B, A);
   static Route route8 = new Route("Eighth ", B, A);
   static  Route route9 = new Route("Ninth  ", A,B);
   static ArrayList<Double> avgPol=new ArrayList<>();
   
    public void start(Stage primaryStage) throws Exception {
        Label time = new Label("Enter time:");
        time.setTranslateX(20);
        time.setTranslateY(10);
        TextField field = new TextField();
        field.setPrefSize(750,40);
        field.setTranslateX(10);
        field.setTranslateY(30);
        
        CheckBox chkbt= new CheckBox("Phase2");
        chkbt.setTranslateX(640);
        chkbt.setTranslateY(75);
        chkbt.setPrefSize(100,55);
 
        
        ChoiceBox chbx= new ChoiceBox();
    	chbx.setTranslateX(710);
    	chbx.setTranslateY(90);
    	chbx.setPrefSize(100, 15);
    	chbx.setDisable(true);
    	chbx.getItems().addAll("Moral","Smart");
    	
    	
        TextArea display = new TextArea();
        display.setPrefSize(800,450);
        display.setTranslateX(10);
        display.setTranslateY(130);
        display.setEditable(false);

        TextArea display_commuter = new TextArea();
        display_commuter.setPrefSize(800,450);
        display_commuter.setTranslateX(10);
        display_commuter.setTranslateY(130);
        display_commuter.setEditable(false);

        Button bOK = new Button("OK");
        bOK.setPrefSize(40, 30);
        bOK.setTranslateX(765);
        bOK.setTranslateY(35);
        chkbt.setOnAction(value ->{
        	chbx.setDisable(false);
        	phase=2;
        });
        chbx.setOnAction((value) ->{
        	if(chbx.getSelectionModel().getSelectedItem().equals("Moral")) {
        		Type=1;}
        	if(chbx.getSelectionModel().getSelectedItem().equals("Smart")) {
        		Type=2;}
        });
        bOK.setOnAction(value ->{
            int s = 0;
            for(int i = 0;i<field.getText().length();i++){
                {
                    if(field.getText().equals("")){
                        s=2;
                        break;
                    }
                    if((Character.isLetter(field.getText().charAt(i))))
                    {
                        s=0;
                        break;
                    }
                    else{s=1;}
                }}
            if(s==1){
                double min = Double.parseDouble(field.getText());
                display.setText(mainDetails(min,phase,Type));
            }
            else{JOptionPane.showMessageDialog(null, "Wrong input", "Warning",JOptionPane.WARNING_MESSAGE);}
        });

        Button bCom = new Button("Serach For Commuter");
        bCom.setPrefSize(150,50);
        bCom.setTranslateX(10);
        bCom.setTranslateY(75);


        TextField field1 = new TextField();
        field1.setPrefSize(325,25);
        field1.setPromptText("Enter an ID to Search(Example: 0003)");
        field1.setTranslateX(10);
        field1.setTranslateY(10);

        TextArea display1 = new TextArea();
        display1.setPrefSize(680,250);
        display1.setTranslateX(10);
        display1.setTranslateY(40);
        display1.setEditable(false);

        Button bOK1 = new Button("OK");
        bOK1.setPrefSize(50,25);
        bOK1.setTranslateX(340);
        bOK1.setTranslateY(10);
        bOK1.setOnAction(value ->{
            int a =0;
            for(int i=0;i<cTotal_Gen.size();i++){
                if(cTotal_Gen.get(i).ID.compareTo(field1.getText())==0){
                    a=1;
                    display1.setText(searchID(field1.getText(),cTotal_Gen));
                    break;
                }
                else{
                    a=5;
                    continue;
                }
            }
            if(a==5){
                JOptionPane.showMessageDialog(null, "Wrong input Or Commuter Not Found", "Warning",JOptionPane.WARNING_MESSAGE);}
        });
        Pane com = new Pane();
        com.getChildren().addAll(bOK1,field1,display1);
        Scene view_com = new Scene(com,700,300);
        Stage commuter_Stage = new Stage();
        commuter_Stage.setScene(view_com);
        commuter_Stage.setTitle("Search");
        commuter_Stage.setResizable(false);
        bCom.setOnAction(value ->{
            commuter_Stage.show();
        });

        TextArea display2 = new TextArea();
        display2.setPrefSize(680,450);
        display2.setTranslateX(10);
        display2.setTranslateY(10);
        display2.setEditable(false);


        Pane statics = new Pane();
        Button bStat = new Button("Show Statistics");
        bStat.setPrefSize(150,50);
        bStat.setTranslateX(165);
        bStat.setTranslateY(75);


        statics.getChildren().addAll(display2);
        Scene view_sta = new Scene(statics,700,500);
        Stage state_Stage = new Stage();
        state_Stage.setScene(view_sta);
        state_Stage.setTitle("Statistics");
        state_Stage.setResizable(false);
        bStat.setOnAction(value ->{
        	
            display2.setText(Stats(cTotal_Gen)+CommuteStatitcs(cTotal_Gen));
            state_Stage.show();
        });

        Button bExit = new Button("Exit");
        bExit.setPrefSize(150,50);
        bExit.setTranslateX(475);
        bExit.setTranslateY(75);
        bExit.setOnAction(value ->{
            System.exit(0);
        });
        Button bReset = new Button("Clear");
        bReset.setPrefSize(150,50);
        bReset.setTranslateX(320);
        bReset.setTranslateY(75);
        bReset.setOnAction(value ->{
        	Commuter.All_Ids.clear();
        	cTotal_Gen.clear();
            display.setText("");
            display1.setText("");
            display2.setText("");
        });
  
        
        
        
        

        Pane sim = new Pane();
        sim.getChildren().addAll(field,time,bOK,bCom,bReset,bStat,bExit,chkbt,chbx,display);

        Scene view = new Scene(sim,815,600);

        primaryStage.setScene(view);
        primaryStage.setTitle("City simulator");
        primaryStage.setResizable(false);
        primaryStage.show();

    }
    public static String mainDetails(double TimeInMinutes,int phase,int Type){
        String main="";
        Routes.clear();
        Routes.add(route1);;
        Routes.add(route2);
        Routes.add(route3);
        Routes.add(route4);
        Routes.add(route5);
        Routes.add(route6);
        Routes.add(route7);
        Routes.add(route8);
        Routes.add(route9);
        
        Commuter.All_Ids.clear();
        cTotal_Gen.clear();
     
    
        
        ArrayList<Commuter> cTotal = new ArrayList<>();
        cTotal.clear();
        int NumberOfCommutersInRoute = new java.util.Random().nextInt(1) + 3;
        int counter=0;
        ArrayList<Double> Time = new ArrayList<>();
        Time.clear();
        for(int t=0; t<=Math.ceil((TimeInMinutes/15.0)); t++) {
            Time.add((double)(15*t));
        }
        main+= String.format("%n%sSimulation Started%s%n%n", star(52), star(52));
        while (TimeInMinutes > 0) {
            for (int k = 0; k < (NumberOfCommutersInRoute); k++) {
            	if (phase==1)
                for (int x = 0; x < Routes.size(); x++) {
                    cTotal.add(new Commuter(Routes.get(x),Time.get(counter)));
                }
            	if (phase==2)
                    for (int x = 0; x < Routes.size(); x++) {
                        cTotal.add(new WisedCommuter(Routes.get(x),Time.get(counter),Type));
                    }
            	
            }
            main+= String.format("total number of commuters: %d %30s", cTotal.size(), " ");
            main+= String.format("time Elapsed: %.2f %20s", Time.get(counter), " ");
            main+= String.format("Time left: %.2f %70s %n%n", TimeInMinutes, " ");
            main+=start();

            for (int x = 0; x < cTotal.size(); x++) {
                main+=startCommuter( cTotal.get(x), Time.get(counter))
                ;
            }
            cTotal_Gen=cTotal;
            TimeInMinutes -= 15;

            if (TimeInMinutes > 0)
                main+=String.format("%n%s 15 minutes has passed %s%n%n", star(50), star(50));
            else
                main+=String.format("%n%s Simulation Ended %s%n%n", star(53), star(52));
            counter++;}

        return main;
    }
    public static String startCommuter(Commuter commuter, double time) {
        String a="";
        int i = 0;
        int j = 0;
        double timeForCommuter=time-commuter.timeOfCreation;
        double timeCheck = timeForCommuter;

        while (j < commuter.route.ArrayOfTrips.get(commuter.TripChoice).NumberOfSteps) {
            timeCheck -= commuter.route.ArrayOfTrips.get(commuter.TripChoice).ArrayOfSteps.get(j).timePerStep;
            if (timeCheck >= 0) {
                j++;
                i = commuter.route.ArrayOfTrips.get(commuter.TripChoice).NumberOfSteps;
            } else if (timeCheck <= 0) {
                i = j;
                break;
            }

        }
        if (i < commuter.route.ArrayOfTrips.get(commuter.TripChoice).NumberOfSteps) {

            a+=String.format("\t%s\t\t", commuter.ID);
            a+=String.format("\t%5s\t\t", commuter.route.getName());
            a+=String.format("%c\t\t\t", commuter.route.ArrayOfTrips.get(commuter.TripChoice).getName());
            a+=String.format("%s\t\t\t", commuter.route.ArrayOfTrips.get(commuter.TripChoice).ArrayOfSteps.get(i).toString());
            a+=String.format("     %05.2f\t\t\t", Math.abs(timeCheck));
            a+=String.format("       %05.2f\t\t\t\t", commuter.ETA - timeForCommuter);
            a+=String.format("%d\n", commuter.route.ArrayOfTrips.get(commuter.TripChoice).NumberOfSteps - (i + 1));

        }
        return a;
    }
    public static String start() {
        String a ="";
        a+=String.format("%s\t\t", "Commuter ID:");
        a+=String.format("%s\t     ", "Route:");
        a+=String.format("%s\t\t", "Trip:");
        a+=String.format("  %s\t\t", "Current step:");
        a+=String.format("%s\t\t", "Time left in this step:");
        a+=String.format("%s\t\t", "ETA:");
        a+=String.format("%s\n", "Number of Steps left:");

        return a;
    }
    public static String searchID(String ID, ArrayList<Commuter> cTotal) throws NullPointerException {
        String a="";
        Commuter cSelected = null;
        for (Commuter c : cTotal)
            if (c.ID.equals(ID))
                cSelected = c;
        a+=String.format("%n%s Search Started %s%n",star(53), star(54));
        a+=String.format("ID: %s %n", cSelected.ID);
        a+=String.format("Route: The %s %10s", cSelected.route.getName(), " ");
        a+=String.format("Start destination: %s%5s", cSelected.route.currentStation.getName(), " ");
        a+=String.format("Final destination: %s %n", cSelected.route.nextStation.getName());
        a+=String.format("Trip: %c%5s", cSelected.route.ArrayOfTrips.get(cSelected.TripChoice).getName(), " ");
        a+=String.format("ETA: %05.2f Minutes%5s", cSelected.ETA, " ");
        a+=String.format("Cost: %05.2f Dollars%5s", cSelected.costs, " ");
        a+=String.format("Co2 Emission: %05.2f Kg%5s %n", cSelected.route.ArrayOfTrips.get(cSelected.TripChoice).Pollution, " ");
        a+=String.format("Fuel Consumption: %05.2f Litters%5s", cSelected.route.ArrayOfTrips.get(cSelected.TripChoice).TotalFuel, " ");
        a+=String.format("Distance: %05.2f Km%5s", cSelected.route.distance, " ");

        a+=String.format("Convince level: %05.2f %n%n", cSelected.route.ArrayOfTrips.get(cSelected.TripChoice).P_of_CONV);
        a+=String.format("%s\t", "Steps:");
        a+=String.format("%s      ","Time:");
        a+=String.format("%s\t  ","Cost:");
        a+=String.format("%s\t","CO2 emission:");
        a+=String.format("%s","Fuel Consumption:");
        a+=String.format("       %s\n", "Distance:");
        for (CommuteMethods step : cSelected.route.ArrayOfTrips.get(cSelected.TripChoice).ArrayOfSteps) {
            a+=String.format("%s\t", step.toString());
            a+=String.format("%05.2f      ", step.timePerStep);
            a+=String.format("%05.2f\t\t", step.Cost);
            a+=String.format("%05.2f\t\t\t", step.P_of_CO2);
            a+=String.format("%05.2f\t\t\t", step.FuelConsumptions);
            a+=String.format("%05.2f\n", step.distance);

        }
        a+=String.format("%n%s Search Ended %s%n",star(54), star(55));
        return a;
    }
    public static String CommuteStatitcs(ArrayList<Commuter> cTotal) {
        String a="";
        int cars=0;
        double totalDistanceForCars=0;
        double totalTimeForCars=0;
        double totalMoneyForCars=0;
        double totalPollutionForCars=0;
        double totalFuelForCars=0;

        int buses=0;
        double totalDistanceForBus=0;
        double totalTimeForBus=0;
        double totalMoneyForBus=0;
        double totalPollutionForBus=0;
        double totalFuelForBus=0;

        int metros=0;
        double totalDistanceForMetro=0;
        double totalTimeForMetro=0;
        double totalMoneyForMetro=0;
        double totalPollutionForMetro=0;
        double totalFuelForMetro=0;

        int bicycles=0;
        double totalDistanceForBicycle=0;
        double totalTimeForBicycle=0;
        double totalMoneyForBicycle=0;
        double totalPollutionForBicycle=0;
        double totalFuelForBicycle=0;
        for(int i=0; i<cTotal.size(); i++) {
            for (CommuteMethods method: cTotal.get(i).route.ArrayOfTrips.get(cTotal.get(i).TripChoice).ArrayOfSteps) {
                if(method instanceof Car) {
                    totalDistanceForCars+= method.distance;
                    totalTimeForCars+= method.timePerStep;
                    totalMoneyForCars+= method.Cost;
                    totalPollutionForCars+= method.P_of_CO2;
                    totalFuelForCars+=method.FuelConsumptions;
                    cars++;}
                else if(method instanceof Bus) {
                    totalDistanceForBus+= method.distance;
                    totalTimeForBus+= method.timePerStep;
                    totalMoneyForBus+= method.Cost;
                    totalPollutionForBus+= method.P_of_CO2;
                    totalFuelForBus+=method.FuelConsumptions;
                    buses++;}
                else if(method instanceof Metro) {
                    totalDistanceForMetro+= method.distance;
                    totalTimeForMetro+= method.timePerStep;
                    totalMoneyForMetro+= method.Cost;
                    totalPollutionForMetro+= method.P_of_CO2;
                    totalFuelForMetro+=method.FuelConsumptions;
                    metros++;}
                else if(method instanceof Bicycle) {
                    totalDistanceForBicycle+= method.distance;
                    totalTimeForBicycle+= method.timePerStep;
                    totalMoneyForBicycle+= method.Cost;
                    totalPollutionForBicycle+= method.P_of_CO2;
                    totalFuelForBicycle+=method.FuelConsumptions;
                    bicycles++;}
            }}
        a+=String.format("%n%s Car Statistics %s%n%n",  star(55), star(56));
        a+=String.format("Number of times Commuters used Cars: %d commuter %n", cars);
        a+=String.format("Total distance Traveled by Cars: %.2f Km %n", totalDistanceForCars);
        a+=String.format("Total Time Spent traveling by Cars: %.2f minutes %n", totalTimeForCars);
        a+=String.format("Money spent for transportation by Cars: %.2f Dollars %n", totalMoneyForCars);
        a+=String.format("Pollution caused by transportation by Cars: %.2f Kg %n",totalPollutionForCars);
        a+=String.format("Fuel Consumed for transportation by Cars: %.2f litters %n%n",totalFuelForCars);
        a+=String.format("Average distance Traveled by Cars: %.2f Km per commuter %n", totalDistanceForCars/(double)cars);
        a+=String.format("Average Time Spent traveling by Cars: %.2f minutes per commuter%n", totalTimeForCars/(double)cars);
        a+=String.format("Average Money spent for transportation by Cars: %.2f Dollars per commuter%n", totalMoneyForCars/(double)cars);
        a+=String.format("Average Pollution caused by transportation by Cars: %.2f Kg per commuter%n",totalPollutionForCars/(double)cars);
        a+=String.format("Average Fuel Consumed for transportation by Cars: %.2f litters per commuter%n",totalFuelForCars/(double)cars);

        a+=String.format("%n%s Bus Statistics %s%n%n",  star(55), star(56));
        a+=String.format("Number of times Commuters used Buses: %d commuter %n", buses);
        a+=String.format("Total distance Traveled by Buses: %.2f Km %n", totalDistanceForBus);
        a+=String.format("Total Time Spent traveling by Buses: %.2f minutes %n", totalTimeForBus);
        a+=String.format("Money spent for transportation by Buses: %.2f Dollars %n", totalMoneyForBus);
        a+=String.format("Pollution caused by transportation by Buses: %.2f Kg %n",totalPollutionForBus);
        a+=String.format("Fuel Consumed for transportation by Buses: %.2f litters %n%n",totalFuelForBus);
        a+=String.format("Average distance Traveled by Buses: %.2f Km per commuter %n", totalDistanceForBus/(double)buses);
        a+=String.format("Average Time Spent traveling by Buses: %.2f minutes per commuter%n", totalTimeForBus/(double)buses);
        a+=String.format("Average Money spent for transportation by Buses: %.2f Dollars per commuter%n", totalMoneyForBus/(double)buses);
        a+=String.format("Average Pollution caused by transportation by Buses: %.2f Kg per commuter%n",totalPollutionForBus/(double)buses);
        a+=String.format("Average Fuel Consumed for transportation by Buses: %.2f litters per commuter%n",totalFuelForBus/(double)buses);


        a+=String.format("%n%s Metro Statistics %s%n%n",  star(55), star(56));
        a+=String.format("Number of times Commuters used Metros: %d commuter %n", metros);
        a+=String.format("Total distance Traveled by Metros: %.2f Km %n", totalDistanceForMetro);
        a+=String.format("Total Time Spent traveling by Metros: %.2f minutes %n", totalTimeForMetro);
        a+=String.format("Money spent for transportation by Metros: %.2f Dollars %n", totalMoneyForMetro);
        a+=String.format("Pollution caused by transportation by Metros: %.2f Kg %n",totalPollutionForMetro);
        a+=String.format("Fuel Consumed for transportation by Metros: %.2f litters %n%n",totalFuelForMetro);
        a+=String.format("Average distance Traveled by Metros: %.2f Km per commuter %n", totalDistanceForBicycle/(double)metros);
        a+=String.format("Average Time Spent traveling by Metros: %.2f minutes per commuter%n", totalTimeForMetro/(double)metros);
        a+=String.format("Average Money spent for transportation by Metros: %.2f Dollars per commuter%n", totalMoneyForMetro/(double)metros);
        a+=String.format("Average Pollution caused by transportation by Metros: %.2f Kg per commuter%n",totalPollutionForMetro/(double)metros);
        a+=String.format("Average Fuel Consumed for transportation by Metros: %.2f litters per commuter%n",totalFuelForMetro/(double)metros);

        a+=String.format("%n%s Bicycle Statistics %s%n%n",  star(55), star(56));
        a+=String.format("Number of times Commuters used Bicycles: %d commuter %n", bicycles);
        a+=String.format("Total distance Traveled by Bicycles: %.2f Km %n", totalDistanceForBicycle);
        a+=String.format("Total Time Spent traveling by Bicycles: %.2f minutes %n", totalTimeForBicycle);
        a+=String.format("Money spent for transportation by Bicycles: %.2f Dollars %n", totalMoneyForBicycle);
        a+=String.format("Pollution caused by transportation by Bicycles: %.2f Kg %n",totalPollutionForBicycle);
        a+=String.format("Fuel Consumed for transportation by Bicycles: %.2f litters %n%n",totalFuelForBicycle);
        a+=String.format("Average distance Traveled by Bicycles: %.2f Km per commuter %n", totalDistanceForBicycle/(double)bicycles);
        a+=String.format("Average Time Spent traveling by Bicycles: %.2f minutes per commuter%n", totalTimeForBicycle/(double)bicycles);
        a+=String.format("Average Money spent for transportation by Bicycles: %.2f Dollars per commuter%n", totalMoneyForBicycle/(double)bicycles);
        a+=String.format("Average Pollution caused by transportation by Bicycles: %.2f Kg per commuter%n",totalPollutionForBicycle/(double)bicycles);
        a+=String.format("Average Fuel Consumed for transportation by Bicycles: %.2f litters per commuter%n",totalFuelForBicycle/(double)bicycles);
        a+=String.format("%n%s Statistics End %s%n%n",star(54), star(53));
        return a;
    }
    public static String Stats(ArrayList<Commuter> cTotal) {
        String a="";
        double TimeSpent = 0;
        double DistanceTraveled = 0;
        double Pollution = 0;
        double TotalCost = 0;
        double TotalFuel = 0;



        for (Commuter commuter : cTotal) {
            TimeSpent += commuter.ETA;
            DistanceTraveled += commuter.route.distance;
            TotalCost += commuter.costs;
            Pollution += commuter.route.ArrayOfTrips.get(commuter.TripChoice).Pollution;
            TotalFuel += commuter.route.ArrayOfTrips.get(commuter.TripChoice).TotalFuel;

        }
        double maxTime = cTotal.get(0).ETA;
        double minTime = cTotal.get(0).ETA;
        String maxTimeID = cTotal.get(0).ID;
        String minTimeID = cTotal.get(0).ID;
        for (Commuter commuter : cTotal) {
            if (maxTime <= commuter.ETA){
                maxTime = commuter.ETA;
                maxTimeID = commuter.ID;}
            if (minTime > commuter.ETA){
                minTime = commuter.ETA;
                minTimeID = commuter.ID;}
        }
        double longestDistance = cTotal.get(0).route.distance;
        double shortestDistance = cTotal.get(0).route.distance;
        String maxDisID = cTotal.get(0).ID;
        String minDisID = cTotal.get(0).ID;
        for (Commuter commuter : cTotal) {
            if (longestDistance <= commuter.route.distance){
                longestDistance = commuter.route.distance;
                maxDisID = commuter.ID;
            }
            if (shortestDistance > commuter.route.distance){
                shortestDistance = commuter.route.distance;
                minDisID = commuter.ID;
            }
        }

        String maxCostID = cTotal.get(0).ID;
        String minCostID = cTotal.get(0).ID;
        double highestCost = cTotal.get(0).costs;
        double leastCost = cTotal.get(0).costs;
        for (Commuter commuter : cTotal) {
            if (highestCost <= commuter.costs){
                highestCost = commuter.costs;
                maxCostID = commuter.ID;
            }
            if (leastCost > commuter.costs){
                leastCost = commuter.costs;
                minCostID = commuter.ID;
            }
        }
        String maxPollID = cTotal.get(0).ID;
        String minPollID = cTotal.get(0).ID;
        double highestPoll = cTotal.get(0).route.ArrayOfTrips.get(0).Pollution;
        double leastPoll = cTotal.get(0).route.ArrayOfTrips.get(0).Pollution;
        for (Commuter commuter : cTotal) {
            if (highestPoll <= commuter.route.ArrayOfTrips.get(commuter.TripChoice).Pollution){
                highestPoll = commuter.route.ArrayOfTrips.get(commuter.TripChoice).Pollution;
                maxPollID = commuter.ID;
            }
            if (leastPoll > commuter.route.ArrayOfTrips.get(commuter.TripChoice).Pollution){
                leastPoll = commuter.route.ArrayOfTrips.get(commuter.TripChoice).Pollution;
                minPollID = commuter.ID;
            }
        }
        String maxFuelID = cTotal.get(0).ID;
        String minFuelID = cTotal.get(0).ID;
        double highestFuel = cTotal.get(0).route.ArrayOfTrips.get(0).TotalFuel;
        double leastFuel = cTotal.get(0).route.ArrayOfTrips.get(0).TotalFuel;
        for (Commuter commuter : cTotal) {
            if (highestFuel <= commuter.route.ArrayOfTrips.get(commuter.TripChoice).TotalFuel){
                highestFuel = commuter.route.ArrayOfTrips.get(commuter.TripChoice).TotalFuel;
                maxFuelID = commuter.ID;
            }
            if (leastFuel > commuter.route.ArrayOfTrips.get(commuter.TripChoice).TotalFuel){
                leastFuel = commuter.route.ArrayOfTrips.get(commuter.TripChoice).TotalFuel;
                minFuelID = commuter.ID;
            }
        }
        a+=String.format("%n%s Statistics %s%n%n" +
                        "Number of Commuters: %d commuter%n" +
                        "Total distance Traveled: %.2f Km%n" +
                        "Total Time Spent traveling: %.2f minutes%n" +
                        "Money spent for transportation: %.2f Dollars%n" +
                        "Pollution caused by transportation: %.2f Kg%n" +
                        "Fuel Consumed for transportation: %.2f litters%n%n" +
                        "Longest Distance Traveled: %.2f Km, by %s%n" +
                        "Shortest Distance Traveled: %.2f Km, by %s%n" +
                        "longest Time Spent traveling: %.2f minutes, by %s%n" +
                        "Shortest Time Spent traveling: %.2f minutes, by %s%n" +
                        "highest Money spent for transportation: %.2f Dollars, by %s%n" +
                        "Least Money spent for transportation: %.2f Dollars, by %s%n" +
                        "Maximum Pollution Emitted: %.2f Kg, by %s%n" +
                        "Minimum Pollution Emitted: %.2f Kg, by %s%n" +
                        "Maximum Fuel Consumed: %.2f Litters, by %s%n" +
                        "Minimum Fuel Consumed: %.2f Litters, by %s%n%n" +
                        "The Average distance traveled: %.2f Km per commuter%n" +
                        "The Average Time Spent traveling: %.2f minutes per commuter%n" +
                        "The Average Money spent for transportation: %.2f Dollars per commuter%n" +
                        "The Average Pollution caused by transportation: %.2f Kg per commuter%n" +
                        "The Average Fuel Consumed for transportation: %.2f litters per commuter%n",
                star(55), star(56), cTotal.size(),DistanceTraveled, TimeSpent, TotalCost, Pollution, TotalFuel
                , longestDistance,maxDisID
                ,shortestDistance,minDisID
                ,maxTime,maxTimeID
                ,minTime,minTimeID
                ,highestCost,maxCostID
                ,leastCost,minCostID
                ,highestPoll,maxPollID
                ,leastPoll,minPollID
                ,highestFuel,maxFuelID
                ,leastFuel,minFuelID
                ,DistanceTraveled/(double) cTotal.size()
                ,TimeSpent/(double)cTotal.size()
                ,TotalCost/(double)cTotal.size()
                ,Pollution/(double)cTotal.size()
                ,TotalFuel/(double)cTotal.size());
        
        avgPol.add(Pollution/(double)cTotal.size());
        if(avgPol.size()==2)
        	a+=String.format("%nPercntege of decrease in CO2 emmision: %.2f %n", (avgPol.get(0)-avgPol.get(1))/avgPol.get(0)*100);
        return a;
    }
    public static String star(int a){
        String star="";
        for(int i=0;i<a;i++){
            star+="*";
        }
        return star;
    }
    public static void main(String []args){
        launch(args);
    }
}

