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
5. **_Following are the rules for assigning a spot to a vehicle_.** 
    1. When a vehicle arrives, the system should assign it to the floor with the least number of available spots for that vehicle type. 
   2. If there are multiple floors with same number of available spots, the system should assign the vehicle to the floor with the lowest floor number. 
   3. If a floor is operational, then only it should be considered, otherwise the system should ignore that floor. 
   4. Once a floor has been selected, the system should assign the vehicle to the nearest available spot of that vehicle type on that floor. 
   5. If there are no available spots on any floor, the system should not issue a ticket.
6. **_Additional Services_** : A customer will opt in for additional services when they are getting the ticket. The customer will pay for the additional services when they are exiting the parking lot along with the parking fee. All the additional services have a fixed fee. Each additional service has its own set of supported vehicle types. Meaning a car wash can only be done for cars and not for bikes. Details for the addtionals services are as follows:

   1. Car Wash: Supported Vehicle Types: [Car, Suv] Fee: 200 
   2. Bike Wash:  Supported Vehicle Types: [Bike, EV bike] Fee: 100 
   3. Car detailing: Supported Vehicle Types: [Car, Suv] Fee: 500 
   4. EV bike charging: Supported Vehicle Types: [EV bike] Fee: 100 
   5. EV car charging: Supported Vehicle Types: [EV car] Fee: 200 
   6. If a vehicle type is not supported for a particular service, then the service should not be offered to the customer i.e. ticket should not be generated.
7. **_Invoice Generation_** :We want to generate an invoice for the customer when they are exiting the parking lot. The invoice should include the parking fee and the additional services fee. Additional services pricing is fixed and is mentioned above. For parking fee we have 2 models: 
   1. asas
   2. Weekday pricing: In this model we will have a fixed fee for all vehicle types which is 10 per hour. Eg. If a vehicle enters the parking lot at 10:00 AM and exits at 11:30 AM then the parking fee will be 20. If a vehicle enters the parking lot at 10:00 AM and exits at 11:00 AM then the parking fee will be 10. 
   3. Weekend pricing: In this model we will have slab based pricing which will differ as per vehicle type. E.g. Slab based pricing for cars: 
         1. 0-2 hours: Rs. 20 per hour
      2. 2-4 hours: Rs. 30 per hour 
      3. 4-6 hours: Rs. 40 per hour 
      4. 6-(-1) hours: Rs. 50 per hour (-1 indicates that there is no upper limit for the slab)
      5. If a car is parked for 1 hour then the parking fee will be 20. If a car is parked for 3 hours then the parking fee will be 70 (40 for first 2 hours and 30 for next 1 hour). If a car is parked for 5 hours then the parking fee will be 140 (40 for first 2 hours, 60 for next 2 hours and 40 for next 1 hour). If a car is parked for 7 hours then the parking fee will be 230 (40 for first 2 hours, 60 for next 2 hours, 80 for next 2 hours and 50 for next 1 hour). Slab related data will be stored in a table called as slabs. 
      6. If a vehicle has opted for additional services then the parking fee will be calculated first and then the additional services fee will be added to it. If no additional services are opted then only the parking fee will be charged. To achieve these requirements we need to do 2 things: - Modify the already implemented ticket generation logic to include additional services. I.e. if a customer is opting for additional services then details related to the additional services should be stored in the ticket. - Implement functionality for generating invoice, it should include the parking fee and the additional services fee.
