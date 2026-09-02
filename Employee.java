// EMPLOYEE CRUD OPERATION
import java.util.Scanner;
import java.util.ArrayList;

public class Employee {
    public static void main(String[] args) {
        ArrayList<EmployeeEntity> empDetails = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        operation(empDetails, s);
    }

    public static class EmployeeEntity {
        public int id;
        public String name;
        public String email; 
        public Long contact;
        public String department;
        public String city;

        // constructor
        EmployeeEntity(int id, String name, String email, Long contact, String department, String city) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.contact = contact;
            this.department = department;
            this.city = city;
        }

        public String toString() {
            return " Id:" + id + " Name:" + name + " Email:" + email + " Contact:" + contact + " Department:"
                    + department + " City:" + city + "\n";
        }
    }

    // operation
    public static void operation(ArrayList<EmployeeEntity> empDetails, Scanner s) {
        boolean running = true;
        while (running) {
            System.out.println("----Employee Management-----");
            System.out.println("\n1.Add: \n2.Show \n3.Delete \n4.Update \n5.Search \n6.Sort \n7.Exit");
            System.out.print("\n-----Enter Your Choice:");
            int choice = 0;
            String choices = s.nextLine(); 
            try {
                if (!choices.isEmpty()) {
                    choice = Integer.parseInt(choices);
                }
            } catch (NumberFormatException e) {}
            switch (choice) {
                case 1-> addData(empDetails, s);
                case 2-> showData(empDetails);
                case 3-> deleteData(empDetails, s);
                case 4-> updateData(empDetails, s);
                case 5-> searchData(empDetails, s);
                case 6-> sortData(empDetails, s);
                case 7 -> {running = false;exitProgram();}
                default-> {System.out.println("Retry Invalid Choice!"); break;}
            }
        }
    }

    public static void addData(ArrayList<EmployeeEntity> empDetails, Scanner s) {
        while (true) {
            int id1 = 0;
            while (true) {
                System.out.print("Id (numeric): ");
                String id = s.nextLine();
                try {
                    if (!id.isEmpty()) {
                        id1 = Integer.parseInt(id);
                        break;
                    } else {
                        System.out.println("Id cannot be empty!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid! Please enter a numeric Id.");
                }
            }

            String name = "";
            while (true) {
                System.out.print("Name: ");
                name = s.nextLine().trim();
                if (!name.isEmpty() && name.matches("[a-zA-Z ]+")) {
                    break;
                } else {
                    System.out.println("Invalid! Name must contain only letters and cannot be empty.");
                }
            }

            String email = "";
            while (true) {
                System.out.print("Email: ");
                email = s.nextLine().trim();
                if (!email.isEmpty() && email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
                    break;
                } else {
                    System.out.println("Invalid email format.");
                }
            }

            long contact1 = 0;
            while (true) {
                System.out.print("Contact (numeric): ");
                String contact = s.nextLine();
                try {
                    if (!contact.isEmpty()) {
                        contact1 = Long.parseLong(contact);
                        if (String.valueOf(contact1).length() >= 10 && String.valueOf(contact1).length() <= 15) {
                            break;
                        } else {
                            System.out.println("Contact number must be between 10 to 15 digits.");
                        }
                    } else {
                        System.out.println("Contact cannot be empty!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid! Please enter numeric contact.");
                }
            }

            String department = "";
            while (true) {
                System.out.print("Department: ");
                department = s.nextLine().trim();
                try {
                if (!department.isEmpty() && department.matches("[a-zA-Z ]+")) {
                    break;
                } else {
                    System.out.println("Department cannot be empty.");
                }
            }
            catch(NumberFormatException e){
                System.out.println("Invalid Department name!");
            }
            }

            String city = "";
            while (true) {
                System.out.print("City: ");
                city = s.nextLine().trim();
                try{
                if (!city.isEmpty() && city.matches("[a-zA-Z ]+")) {
                    break;
                } else {
                    System.out.println("City cannot be empty.");
                }
            }
            catch(NumberFormatException e) {
                System.out.println("Inavlid city name!");
            }
            }

            // Add employee to list
            empDetails.add(new EmployeeEntity(id1, name, email, contact1, department, city));

            System.out.print("Type 'exit' to stop or press 'Enter' to continue: ");
            String choice = s.nextLine();
            if (choice.equalsIgnoreCase("exit")) {
                break;
            }
        }
    }

    public static void showData(ArrayList<EmployeeEntity> empDetails) {
        if (empDetails.isEmpty()) {
            System.out.println("No Data Found!");
        } else {
            System.out.printf("%-5s %-15s %-25s %-15s %-15s %-15s%n",
                    "ID", "Name", "Email", "Contact", "Department", "City");
            System.out.println("-----------------------------------------------------------------------------------------");

            for (EmployeeEntity emp : empDetails) {
                System.out.printf("%-5d %-15s %-25s %-15s %-15s %-15s%n",
                        emp.id,
                        emp.name,
                        emp.email,
                        String.valueOf(emp.contact),
                        emp.department,
                        emp.city);
            }
        }
    }

    // deleteEmployeeDetails
    public static void deleteData(ArrayList<EmployeeEntity> empDetails, Scanner s) {
        if (empDetails.isEmpty()) {
            System.out.println("No Data Found!");
        } else {
            boolean running = true;
            while (running) {
                System.out.print("Enter 'id' to delete Data");
                int id = s.nextInt();
                s.nextLine();

                boolean found = false;

                for (int i = 0; i < empDetails.size(); i++) {
                    if (empDetails.get(i).id == id) {
                        empDetails.remove(i);    // safe in indexed loop
                        System.out.println("Data Deleted!");
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Invalid ID");
                }

                running = false;
            }
        }
    }

    // updateEmployeeDetails
    public static void updateData(ArrayList<EmployeeEntity> empDetails, Scanner s) {
        if (empDetails.isEmpty()) {
            System.out.println("No Data Found");
            return;
        }

        System.out.print("Enter Employee ID to update: ");
        int eId = s.nextInt();
        s.nextLine();

        boolean found = false;

        for (EmployeeEntity emp : empDetails) {
            if (emp.id == eId) {
                System.out.println(emp);
                found = true;

                System.out.println("\n1. Update Id");
                System.out.println("2. Update Name");
                System.out.println("3. Update Email");
                System.out.println("4. Update Contact");
                System.out.println("5. Update Department");
                System.out.println("6. Update City");
                System.out.print("Enter your choice: ");
                int opt = s.nextInt();
                s.nextLine();

                switch (opt) {
                    case 1:
                        System.out.print("Update ID (" + emp.id + "): ");
                        int uId = s.nextInt();
                        s.nextLine();
                        if (uId >= 0) {
                            emp.id = uId;
                            System.out.println("Successfully updated ID.");
                        } else {
                            System.out.println("Invalid ID.");
                        }
                        break;

                    case 2:
                        System.out.print("Update Name (" + emp.name + "): ");
                        String uName = s.nextLine();
                        if (!uName.isEmpty()) {
                            emp.name = uName;
                            System.out.println("Successfully updated Name.");
                        }
                        break;

                    case 3:
                        System.out.print("Update Email (" + emp.email + "): ");
                        String uEmail = s.nextLine();
                        if (!uEmail.isEmpty()) {
                            emp.email = uEmail;
                            System.out.println("Successfully updated Email.");
                        }
                        break;

                    case 4:
                        System.out.print("Update Contact (" + emp.contact + "): ");
                        String contactInput = s.nextLine();
                        if (!contactInput.isEmpty()) {
                            try {
                                long uContact = Long.parseLong(contactInput);
                                emp.contact = uContact;
                                System.out.println("Successfully updated Contact.");
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid contact number.");
                            }
                        }
                        break;

                    case 5:
                        System.out.print("Update Department (" + emp.department + "): ");
                        String uDepartment = s.nextLine();
                        if (!uDepartment.isEmpty()) {
                            emp.department = uDepartment;
                            System.out.println("Successfully updated Department.");
                        }
                        break;

                    case 6:
                        System.out.print("Update City (" + emp.city + "): ");
                        String uCity = s.nextLine();
                        if (!uCity.isEmpty()) {
                            emp.city = uCity;
                            System.out.println("Successfully updated City.");
                        }
                        break;

                    default:
                        System.out.println("Invalid Choice!");
                        break;
                }

                break; // exit loop
            }
        }

        if (!found) {
            System.out.println("Employee ID not found.");
        }
    }

    // searchEmployeeDetails
    public static void searchData(ArrayList<EmployeeEntity> empDetails, Scanner s) {
        if (empDetails.isEmpty()) {
            System.out.println("No Data Found!");
        } else {
            boolean running = true;
            while (running) {
                System.out.print("Enter 'id' to search Data");
                int id = s.nextInt();
                s.nextLine();

                boolean found = false;

                for (EmployeeEntity emp : empDetails) {
                    if (emp.id == id) {
                        System.out.println(emp);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Invalid ID");
                }

                running = false;
            }
        }
    }

    // sortEmployeeDetails
    public static void sortData(ArrayList<EmployeeEntity> empDetails, Scanner s) {
        if (empDetails.isEmpty()) {
            System.out.println("No Data Found!");
        } else {
            System.out.println("Sort based on: \n1. Name \n2. City \n3. Department");
            System.out.print("Enter Your Choice: ");

            int choice = 0;
            String input = s.nextLine();
            if (!input.isEmpty()) {
                try {
                    choice = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    return;
                }
            }

            switch (choice) {
                case 1:
                    empDetails.sort((e1, e2) -> e1.name.compareToIgnoreCase(e2.name));
                    break;
                case 2:
                    empDetails.sort((e1, e2) -> e1.city.compareToIgnoreCase(e2.city));
                    break;
                case 3:
                    empDetails.sort((e1, e2) -> e1.department.compareToIgnoreCase(e2.department));
                    break;
                default:
                    System.out.println("Invalid Choice");
                    return;
            }
            System.out.println("Sorted Employee Details:");
            for (EmployeeEntity emp : empDetails) {
                System.out.println(emp);
            }
        }
    }

    // exitProgram
    public static void exitProgram() {
        System.out.println("Exiting Program.....");
    }
}
