/*
Classroom Attendance System

Teacher thread:

Takes attendance

Student thread:

Waits until attendance starts
Requirements
Students should not proceed immediately.
Students must wait.
Teacher gives signal.
Students continue after signal.

Expected Flow:

Student Waiting...

Teacher Started Attendance

Student Marked Present
Concepts Tested
wait()
notifyAll()


Instructions - 
Create Class Classroom

    Variable:
        attendanceStarted = false

    synchronized method waitForAttendance()

        While attendanceStarted is false

            Print:
                "Student Waiting..."

            wait()

        Print:
            "Student Marked Present"


    synchronized method startAttendance()

        attendanceStarted = true

        Print:
            "Teacher Started Attendance"

        notifyAll()


Create Class TeacherThread

    Classroom classroom

    run()

        classroom.startAttendance()


Create Class StudentThread

    Classroom classroom

    run()

        classroom.waitForAttendance()


Main Method

    Create Classroom object

    Create multiple Student threads

    Create Teacher thread

    Start Student threads

    Wait for a few seconds

    Start Teacher thread
Thread Coordination
Synchronization
*/
package JavaIgniteDay15;

class Classroom
{
	boolean attendance = false;
	
	synchronized public void waitForAttendance(String name)
	{
		while(attendance==false)
		{
			System.out.println("Student "+name+" waiting....");
			try 
			{
				wait();
			} 
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Student "+name+" Marked Present");
	}
	
	synchronized public void startAttendance()
	{
		attendance = true;
		
		System.out.println("\nTeacher Taking Attendance\n");
		
		notifyAll();
	}
}

class TeacherThread extends Thread
{
	Classroom classroom;
	TeacherThread(Classroom classroom)
	{
		this.classroom = classroom;
	}
	public void run()
	{
		classroom.startAttendance();
	}
}

class StudentThread extends Thread
{
	Classroom classroom;
	StudentThread(Classroom classroom)
	{
		this.classroom = classroom;
	}
	public void run()
	{
		classroom.waitForAttendance(Thread.currentThread().getName());
	}
}

public class Classroom_Attendance {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Classroom attendance = new Classroom();
		StudentThread student1 = new StudentThread(attendance);
		StudentThread student2 = new StudentThread(attendance);
		StudentThread student3 = new StudentThread(attendance);
		StudentThread student4 = new StudentThread(attendance);
		StudentThread student5 = new StudentThread(attendance);
		
		TeacherThread teacher = new TeacherThread(attendance);
		
		student1.setName("Sanjeeb");
		student2.setName("Ayush");
		student3.setName("Asmit");
		student4.setName("Nandini");
		student5.setName("Satya");
		
		student1.start();
		student2.start();
		student3.start();
		student4.start();
		student5.start();
		
		try 
		{
		    Thread.sleep(3000); 
		}
		catch (InterruptedException e)
		{
		    e.printStackTrace();
		}
		
		teacher.start();

	}

}

