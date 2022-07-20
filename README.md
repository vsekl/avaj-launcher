# avaj-launcher

Implementation of a minimal aircraft simulation program.  
Program takes one and only one argument from the command line.  
This argument represents the name of a text file that will contain the scenario that needs to be
simulated.  
Example of scenario.txt:    

25  
Baloon B1 2 3 20  
Baloon B2 1 8 66  
JetPlane J1 23 44 32  
Helicopter H1 654 33 20  
Helicopter H2 22 33 44  
Helicopter H3 98 68 99  
Baloon B3 102 22 34  
JetPlane J2 11 99 768  
Helicopter H4 223 23 54  
 
So format is: first string is a number of weather changes.  
Then TYPE NAME LONGITUDE LATITUDE HEIGHT of an aircrafts presented.    
Possible aircrafts are only that were used in example. All integers should be positive.  
In a case of invalid input a custom exception will be thrown.  
When weather changes, the aircraft should change its coordinates according to randomly generated weather at its current position.  
If height drops below zero - the simulation for this aircraft stops.  
Executing the program will generate a file simulation.txt that describes the outcome
of the simulation. 

Used patterns: Observer, Singleton, Factory.  
UML diagram:
![avajlauncher_uml](https://user-images.githubusercontent.com/91872235/180080718-dad5f8c9-2029-4f92-8e16-99df92466916.png)
