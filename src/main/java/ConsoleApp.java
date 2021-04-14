import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import java.util.*;


public class ConsoleApp {


    HashMap<String, Driver> drivers = new HashMap();
    String fileLocation;

    ConsoleApp(String fileName) {


        try {
            File myFile = new File(fileName);
            Scanner myReader = new Scanner(myFile);
            this.fileLocation = fileName;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                if (data.charAt(0) == 'D') {
                    String[] arrOfStr = data.split(",", 2);


                    if (!this.drivers.containsKey(arrOfStr[1])) {
                        this.drivers.put(arrOfStr[1], new Driver(arrOfStr[1]));
                    } else {
                        throw new Error("Driver already exists.");
                    }
                } else {

                    String[] arrOfStr = data.split(",", 6);
                    Driver currDriver = this.drivers.get(arrOfStr[2]);
                    if(currDriver != null){
                    currDriver.AddTrip(arrOfStr[2], arrOfStr[3], arrOfStr[4], Integer.parseInt(arrOfStr[5]));
                }
                    else {

                        System.out.println("Driver for this trip is not registered."); }

                    }
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File is not found.");
        }
    }

    public HashMap<String, Driver> getDrivers() {
        return drivers;
    }

    public void AddDriver(String name){

        this.drivers.put(name, new Driver(name));
        this.refresh();


    }

    public int AddTrip(String name,String startTime, String endTime, int miles){

        int i = this.drivers.get(name).AddTrip(name,startTime,endTime,miles);
        this.refresh();
        return i;


    }

    public void RemoveDriver(String name){
        this.drivers.remove(name);
        this.refresh();



    }

    public void RemoveTrip(int id){

        for(Driver currDriver : this.drivers.values()) {
            if (currDriver.RemoveTrip(id)) {
                this.refresh();
                return;
            }
        }

        System.out.println("Trip not Found.");
    }

    public void refresh(){


        try {
            FileWriter myWriter = new FileWriter(this.fileLocation);
            for(String currDriver : this.drivers.keySet()) {
                myWriter.write("Driver," + currDriver+ "\n");
            }
            for(Driver currDriver : this.drivers.values()){
                for(Trip currTrip: currDriver.getMyTrips()){
                    myWriter.write(currTrip.printString() + "\n");
                }
            }
            myWriter.close();
            } catch (IOException e){
            System.out.println("Error occurred.");
        }

        }

    public void Report(){
        System.out.println("Driver Report");
        ArrayList<Driver> driversPrint = new ArrayList();

        for (Driver currDriver: this.drivers.values()){
            driversPrint.add(currDriver);
        }

        Collections.sort(driversPrint, Collections.reverseOrder());


        for (Driver currDriver : driversPrint){

            if(currDriver.getTotalMilesDriven() > 0) {
                System.out.println(currDriver.getName() + " drove " + String.valueOf(currDriver.getTotalMilesDriven()) +
                        " miles at an average of " + String.valueOf(currDriver.getAverageVelocity()) + " mph");
            }
            else{
                System.out.println(currDriver.getName() + " drove 0 miles");
            }

        }
    }






}
