import org.junit.jupiter.api.*;

import java.io.File;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
/*
To begin TripsAndDriver.txt has to be the following:
Driver,Dan
Driver,Alex
Driver,Bob
Trip,15,Dan,05-11-2020-18:45,05-12-2020-00:45,215
Trip,15,Dan,05-13-2020-06:00,05-14-2020-06:15,10
Trip,15,Alex,05-14-2020-02:00,05-14-2020-03:00,30
 */


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Tests {

    ConsoleApp console;

    String miles = "The miles traveled of the driver is not correct.";
    String velo = "The average velocity of the driver is not correct.";
    String zero = "The miles travel should be zero.";

    //What I started with.
    private  int numOfDrivers = 3;
    private  int everything = 6;

    @BeforeAll
    void constructor(){
        console = new ConsoleApp("src/main/resources/TripsAndDriver.txt");
    }


    @Test
    void checkHSandLines() {



        assertEquals(numOfDrivers, console.getDrivers().size(),"All drivers are not in hashmap.");

        try {
            int count =0;
            File myFile = new File("src/main/resources/TripsAndDriver.txt");
            Scanner myReader = new Scanner(myFile);

            while (myReader.hasNextLine()) {

                count++;
                myReader.nextLine();
            }
            myReader.close();
            assertEquals(count, everything, "Their is missing lines not written in text file.");

        } catch (Exception e) {
            System.out.println("Cant open");
        }
    }


    @Test
    void ConstructorTest(){

        String text = "Missing trips of the driver.";

        assertAll(
                ()->assertEquals(2,console.getDrivers().get("Dan").getMyTrips().size(),text),
                ()->assertEquals(1,console.getDrivers().get("Alex").getMyTrips().size(),text),
                ()->assertEquals(0,console.getDrivers().get("Bob").getMyTrips().size(),text)
        );

    }


    @Test
    void initializeHashmapTest() {
        //Needs to check if miles and average velocity is added if 5 < velocity < 100

        assertAll(
                ()->assertEquals( 215,console.getDrivers().get("Dan").getTotalMilesDriven(),miles),
                ()->assertEquals( 30,console.getDrivers().get("Alex").getTotalMilesDriven() ,miles),

                ()->assertEquals( 35,console.getDrivers().get("Dan").getAverageVelocity(),velo),
                ()->assertEquals(30,console.getDrivers().get("Alex").getAverageVelocity(),velo),


        //Velocity is not in range so it shouldn't be added.
                ()->assertEquals(0,console.getDrivers().get("Bob").getTotalMilesDriven(),zero),
                ()->assertEquals( 0,console.getDrivers().get("Bob").getTotalMilesDriven(),zero)
        );
    }
    @Test
    void addDriversTest(){
        console.AddDriver("Mike");
        console.AddDriver("Charlie");
        numOfDrivers += 2;
        everything += 2;
        assertTrue( console.getDrivers().containsKey("Bob") && console.getDrivers().containsKey("Charlie"),"Added drivers don't coincide.");
    }

    @Test

    void addTripTest(){
        console.AddDriver("Stacy");
        console.AddDriver("Oscar");

        console.AddTrip("Stacy","05-11-2020-18:45","05-12-2020-00:45",350);
        console.AddTrip("Oscar","05-10-2020-18:45","05-10-2020-20:45",100);
        //This velocity is not in range so it shouldn't be added or the miles.
        console.AddTrip("Stacy","03-11-2020-18:45","05-12-2020-00:45",10);
        assertAll(
                ()->assertEquals(2,console.getDrivers().get("Stacy").getMyTrips().size(),"Added trips don't coincide."),
                ()->assertEquals(1,console.getDrivers().get("Oscar").getMyTrips().size(),"Added trips don't coincide."),
                ()->assertEquals(350,console.getDrivers().get("Stacy").getTotalMilesDriven() ,miles),
                ()->assertEquals(100,console.getDrivers().get("Oscar").getTotalMilesDriven() ,miles),
                ()->assertEquals(58,console.getDrivers().get("Stacy").getAverageVelocity() , velo),
                ()-> assertEquals(50,console.getDrivers().get("Oscar").getAverageVelocity() , velo)

        );

        numOfDrivers += 2;
        everything += 5;

    }

    @Test

    void removeDriverTest(){

        int currSize = console.getDrivers().size();

        console.AddDriver("Luke");
        console.RemoveDriver("Luke");

        assertEquals(currSize, console.getDrivers().size(),"Driver was not removed.");

    }

    @Test

    void removeTripTest(){

        console.AddDriver("John");
        int i = console.AddTrip("John","05-11-2020-18:45","05-12-2020-00:45",350);
        int j = console.AddTrip("John","03-11-2020-18:45","05-12-2020-00:45",10);

        int velocity = console.getDrivers().get("John").getAverageVelocity();
        int miles = console.getDrivers().get("John").getTotalMilesDriven();

        assertEquals(350,velocity ,miles);

        console.RemoveTrip(j);
        //Should not change miles driven or average velocity.
        assertEquals(miles,console.getDrivers().get("John").getTotalMilesDriven());
        assertEquals(velocity,console.getDrivers().get("John").getAverageVelocity());



        console.RemoveTrip(i);

        //Should subtract everything
        assertEquals(0,console.getDrivers().get("John").getTotalMilesDriven());
        assertEquals(0,console.getDrivers().get("John").getAverageVelocity());



        //Remove both trips should have 0 trips left.

        assertEquals(0,console.getDrivers().get("John").getMyTrips().size(),"The number of trips don't coincide after removing trips.");
        console.RemoveDriver("John");
    }



}
