#SafeAuto Project/
#By: Oscar Gandara/
A Project to read driver data from a text file, gives the user functionality to manipulate it, and it updated the text file accordingly./

To move in to proper directory: cd SafeAuto/src/main/java/
To compile in terminal: javac *.java/
To run in terminal: java main/

### User Functions ###


Creating a new ConsoleApp class:/
  ConsoleApp c = new ConsoleApp("TripsAndDriver.txt")/
This will read all data from given text file and will save drivers and trip data. It will also calculate valid total miles driven and average velocity for the drivers./

Functions:/
Adding new drivers:/
  c.AddDriver(String name)/
 Adds new driver and updates text file./
 
 Removing drivers:/
    c.RemoveDriver(String name)/
 Removes the driver after searching by name and updates text file./
 
 Adding Trips:/
  c.AddTrip(String driverName, String startTime, StringEndTime, int miles)/
 Adds trips to the driver given. Calculates the drivers total miles driven and average velocity if it's valid and it updated text file./
 
 Removing Trips:/
  c.RemoveTrip(int id)/
 Searches for a trip by using the automatically generated trip id then removes it. It will update the drivers total miles and average velocity if applicable./
 Then update text file./
 
 Report:/
  c.Report()/
 Gives a report of the all the drivers with their total miles driven and average velocity, ordered by total miles driven.
    
    
   
