
import java.util.ArrayList;
import java.util.Scanner;

abstract class Employee{

    private String name;
    private int id;

    public Employee(String name, int id){
        this.name=name;
        this.id=id;
    }

    public String getName(){
        return name;
    }

    public int getID(){
        return id;
    }

    public abstract double calculateSalary();

    @Override
    public String toString(){
        return "Employee[name="+name+", id ="+id+", salary="+calculateSalary()+"]";
    }
}

class FullTimeEmployee extends Employee{

    private double monthlySalary;

    public FullTimeEmployee(String name, int id, double monthlySalary){
        super(name, id);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateSalary(){
        return monthlySalary;
    }
}

class partTimeEmployee extends Employee{
    private int hoursWorked;
    private double hourlyRate;

    public partTimeEmployee(String name, int id, int hoursWorked, double hourlyRate){
        super(name, id);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary(){
        return hourlyRate*hoursWorked;
    }
}

class payRollSystem{
    private ArrayList<Employee> employeeList;

    public payRollSystem(){
        employeeList = new ArrayList<>();

    }

    public void addEmployee(Employee employee){
        employeeList.add(employee);
    }

    public void removeEmployee(int id){
        Employee employeeToRemove = null;
        for(Employee employee : employeeList){
            if(employee.getID()==id){
                employeeToRemove = employee;
                break;
            }
        }    
        if(employeeToRemove!=null){
                employeeList.remove(employeeToRemove);
        }
    }

    public void displayEmployees(){
        for(Employee employee: employeeList){
            System.out.println(employee);
        }
    }

}

public class App {


    public static void main(String[] args) throws Exception {
        payRollSystem payRollSystem = new payRollSystem();
        FullTimeEmployee emp1 = new FullTimeEmployee("Vikas Sharma", 1, 70000);
        partTimeEmployee emp2 = new partTimeEmployee("Khushi Varshney", 2, 40, 500);

        payRollSystem.addEmployee(emp1);
        payRollSystem.addEmployee(emp2);

        System.out.println("Employee Details : \t");
        payRollSystem.displayEmployees();
        System.out.println();

        Scanner sc = new Scanner(System.in);
        System.out.print("Add an Employee('1' for yes or '0' for no) : ");
        int wantsToAdd = sc.nextInt();
        while(wantsToAdd==1){
            Scanner in =new Scanner(System.in);
            System.out.print("Enter the name of Employee : ");
            String name =in.nextLine();
            System.out.print("Enter the id of employee : ");
            int id = sc.nextInt();

            System.out.println("Press '1' for FullTimeEmployee else '0' for PartTimeEmployee");
            int empType=sc.nextInt();
            if(empType==1){
                System.out.print("Enter the monthly salary of Employee : ");
                int monSal = sc.nextInt();
                FullTimeEmployee emp3 = new FullTimeEmployee(name, id, monSal);
                payRollSystem.addEmployee(emp3);
            }else{
                System.out.print("Enter the Hours employee worked for : ");
                int houWork = sc.nextInt();
                System.out.print("Enter the hourlyRate of Employee : ");
                int houRate = sc.nextInt();
                partTimeEmployee emp3 = new partTimeEmployee(name, id, houWork, houRate);
                payRollSystem.addEmployee(emp3);
            }
            System.out.println("Wants to add more employee -- ('0 for no' or '1 for yes') : ");
            wantsToAdd=sc.nextInt();
        }

        System.out.println();
        payRollSystem.displayEmployees();
        System.out.println();

        System.out.print("Want to Remove any employee (1 for 'yes' or 0 for 'no') :  ");
        int wantsToRemove = sc.nextInt();

        while(wantsToRemove==1){
            System.out.print("Enter the id of the employee to remove : ");
            int isToRemove = sc.nextInt();
            payRollSystem.removeEmployee(isToRemove);
            System.out.println();
            System.out.print("Want to Remove any employee (1 for 'yes' or 0 for 'no') :  ");
            wantsToRemove = sc.nextInt();           
        }
        System.out.println();
        System.out.println("Remaining Employees : \t");
        payRollSystem.displayEmployees();
        System.out.println("\n\tThank You");
        
        System.out.println();
    }
}
