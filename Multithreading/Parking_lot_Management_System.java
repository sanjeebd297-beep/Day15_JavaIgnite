/*
A shopping mall has:

Available Parking Slots = 3

Multiple cars arrive simultaneously.

Each car is represented by a separate thread.

Requirements
Create a shared ParkingLot class.
Initially:
slots = 3
When a car arrives:
If a slot is available:
Park the car
Reduce available slots
Print:
Car 1 Parked
Available Slots: 2
Otherwise:
No Parking Available for Car 4
Use synchronization to prevent incorrect slot allocation.
Create at least 6 car threads.

Instructions - 
Create Class ParkingLot

    availableSlots ← 3

    synchronized Method parkCar(carName)

        IF availableSlots > 0

            Print carName + " Parked"

            availableSlots ← availableSlots - 1

            Print "Available Slots: " + availableSlots

        ELSE

            Print "No Parking Available for " + carName

        END IF


Create Class CarThread

    ParkingLot parkingLot
    carName

    Method run()

        parkingLot.parkCar(carName)


Main Method

    Create ParkingLot object

    Create Car1 thread till Car6 thread
    Start Car1 till  Car6


*/
package JavaIgniteDay15;
class ParkingLot
{
	int availableSlots = 3;
	
	synchronized public void parkCar(String carName)
	{
		if(availableSlots>0)
		{
			System.out.println(carName+" parked");
			availableSlots-=1;
			
			System.out.println("Available slots: "+availableSlots);
		}
		else
		{
			System.out.println("No parking available for "+carName);
		}
	}
}

class CarThread extends Thread
{
	ParkingLot parkinglot;
	CarThread(ParkingLot parkinglot)
	{
		this.parkinglot = parkinglot;
	}
	public void run()
	{
		parkinglot.parkCar(Thread.currentThread().getName());
	}
	
}

public class Parking_Lot_Management {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ParkingLot park = new ParkingLot();
		CarThread car1 = new CarThread(park);
		CarThread car2 = new CarThread(park);
		CarThread car3 = new CarThread(park);
		CarThread car4 = new CarThread(park);
		CarThread car5 = new CarThread(park);
		CarThread car6 = new CarThread(park);
		
		car1.setName("Bugati");
		car2.setName("BMW");
		car3.setName("Ferrari");
		car4.setName("Porsche");
		car5.setName("Tata");
		car6.setName("Tesla");
		
		car1.start();
		car2.start();
		car3.start();
		car4.start();
		car5.start();
		car6.start();
		
	}

}
