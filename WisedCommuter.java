package application;

public class WisedCommuter extends Commuter implements SmartCommuter,MoralCommuter {

	double pollutionAffect;
	double economicLoss;
	public WisedCommuter(Route route,double time, int t) {
		super(route,time);
	
	switch(t) {
	case 1: 
		PickMoral();
		break;
	case 0:
		PickSmart();
		break;
	}
	}

	@Override
	public void PickMoral() {
		pollutionAffect=route.ArrayOfTrips.get(0).Pollution;
		
	for (int i=0; i<route.NumberOfTrips;i++) {
		if(pollutionAffect>route.ArrayOfTrips.get(i).Pollution) {
			TripChoice=i;
			pollutionAffect=route.ArrayOfTrips.get(TripChoice).Pollution;}}}
	
	@Override
	public void PickSmart() {
		economicLoss=route.ArrayOfTrips.get(0).cost;
		
		for (int i=0; i<route.NumberOfTrips;i++) {
			if(economicLoss>route.ArrayOfTrips.get(i).cost) {
				TripChoice=i;
				economicLoss=route.ArrayOfTrips.get(TripChoice).cost;}}}
	}

