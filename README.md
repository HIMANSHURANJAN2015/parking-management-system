# parking-management-system
A console-based Parking Management System built in Java, adhering to SOLID principles, design patterns, and the Controller-Service-Repository-Model architecture for readable, scalable, maintainable and testable code. Designed to simplify parking operations and demonstrate clean coding practices.

PRD
1. **_Register a new parking lot with all its details_**:
2. **_Print the parking lot_**:
3. **_We want to create an API, which will display the capacity of the given parking lot_**. The API should be flexible to give the overall capacity of the parking lot, or the capacity of a specific parking floor or for specific vehicle type or combination of all of these. The request for this api will always get a parking lot id. List of parking floor ids is optional, when given, the response should contain the capacity of the given parking floors. List of vehicle types is optional, when given, the response should contain details about given vehicle types.
4. **_We want to create a functionality using which customers can generate a ticket while entering the parking lot_**. Tickets can be generated only at entry gates. 
    1. If If a customer tries to generate a ticket at any exit gate, then the system should throw an exception.
   2. Generate ticket request will contain gate id, vehicle number, and vehicle type. 
   3. If the vehicle doesn't exist in our database, we need to create a new vehicle entry in the database.
   4. As a part of ticket generation we also need to assign a spot to the vehicle.
5. **Following are the rules for assigning a spot to a vehicle.** 
    1. When a vehicle arrives, the system should assign it to the floor with the least number of available spots for that vehicle type. 
   2. If there are multiple floors with same number of available spots, the system should assign the vehicle to the floor with the lowest floor number. 
   3. If a floor is operational, then only it should be considered, otherwise the system should ignore that floor. 
   4. Once a floor has been selected, the system should assign the vehicle to the nearest available spot of that vehicle type on that floor. 
   5. If there are no available spots on any floor, the system should not issue a ticket.
6.  a
7. 
