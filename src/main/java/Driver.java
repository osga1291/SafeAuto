import java.util.ArrayList;


public class Driver implements Comparable<Driver> {

        private int countedTrips = 0;
        private String name;
        private ArrayList<Trip> myTrips = new ArrayList();
        private int totalMilesDriven = 0;
        private int totalVelocities = 0;


        Driver(String name){
            this.name = name;
        }

    public ArrayList<Trip> getMyTrips() {
        return myTrips;
    }

    public String getName() {
        return name;
    }

    public int getAverageVelocity() {

            if(countedTrips == 0){
                return 0;
            }
            return Math.round(totalVelocities/countedTrips);
    }


    public int getTotalMilesDriven() {
        return Math.round(totalMilesDriven);
    }

    public int AddTrip(String name, String startTime, String stopTime, int miles) {

        Trip newTrip = new Trip(name, startTime, stopTime, miles);
        myTrips.add(newTrip);

        if (newTrip.checkVelocity()) {
            totalMilesDriven += newTrip.getMilesDriven();
            totalVelocities += newTrip.getVelocity();
            countedTrips++;
        }

        return newTrip.getId();
    }

        public boolean RemoveTrip(int id) {
            for (Trip i: this.myTrips){
                if(i.getId() == id){
                    if(i.checkVelocity()) {
                        totalMilesDriven -= i.getMilesDriven();
                        totalVelocities -= i.getVelocity();
                        countedTrips--;
                    }
                    myTrips.remove(i);

                    return true;

                } }
            return false;

        }

        @Override
        public int compareTo(Driver d){
            if(this.totalMilesDriven != d.getTotalMilesDriven()){
                return this.totalMilesDriven - d.getTotalMilesDriven();
            }
            return this.name.compareTo(d.getName());
        }


    }
