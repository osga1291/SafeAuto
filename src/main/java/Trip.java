import java.text.SimpleDateFormat;
import java.util.Date;


public class Trip {

    private static int currId = 0;
    private int id;
    private String driver;
    private String startTime;
    private  String endTime;
    private double velocity;
    private double milesDriven;


    Trip(String name, String startTime, String stopTime, int milesDriven){
        this.id = currId;
        currId++;
        this.driver = name;
        this.startTime = startTime;
        this.endTime = stopTime;
        this.milesDriven = milesDriven;
    }

    public int getId() {
        return id;
    }

    public boolean checkVelocity(){
        try {
            SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy-HH:mm");
            Date startDate = format.parse(this.startTime);
            long epoch = startDate.getTime();
            Date endDate = format.parse(this.endTime);
            long epoch2 = endDate.getTime();
            double timeElapsed = epoch2/1000 - epoch/1000;
            timeElapsed = timeElapsed / 3600;
            this.velocity = milesDriven/timeElapsed;


            if(this.velocity < 5 || this.velocity > 100){
                return false;
            }


        } catch (Exception e){
            System.out.println("Could not parse date");
        }

        return true;

    }

    public double getVelocity() {
        return velocity;
    }

    public double getMilesDriven() {

        return milesDriven;
    }

    public String printString(){

        String trip = "Trip," + String.valueOf(this.id) + "," +  this.driver + "," + this.startTime + "," + this.endTime + "," + Math.round(this.milesDriven);
        return trip;


    }








}
