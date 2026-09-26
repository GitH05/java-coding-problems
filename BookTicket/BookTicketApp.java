import java.util.*;
class User {
    String name;
    String email;
    String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
class Train {
    int id;
    String source;
    String destination;
    String date;
    String time;
    int seats;
    double fare;

    public Train(int id, String source, String destination, String date, String time, int seats, double fare) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.seats = seats;
        this.fare = fare;
    }

    public String toString() {
        return "Train ID: " + id + " | " + source + " -> " + destination + " | " + date + " " + time +
               " | Seats: " + seats + " | Fare: ₹" + fare;
    }
}

class Booking {
    static int counter = 1000;
    int pnr;
    String userEmail;
    int trainId;
    String passengerName;

    public Booking(String userEmail, int trainId, String passengerName) {
        this.pnr = counter++;
        this.userEmail = userEmail;
        this.trainId = trainId;
        this.passengerName = passengerName;
    }

    public String toString() {
        return "PNR: " + pnr + " | Passenger: " + passengerName + " | Train ID: " + trainId + " | Booked by: " + userEmail;
    }
}

public class BookTicketApp {
    static List<User> users = new ArrayList<>();
    static List<Train> trains = new ArrayList<>();
    static List<Booking> bookings = new ArrayList<>();
    static final String adminEmail = "admin@123gmail.com";
    static final String adminPass = "admin";

    public static void main(String[] args) {
        seedData();

        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n=== BookTicket System ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Admin Login");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> registerUser(sc);
                case 2 -> loginUser(sc);
                case 3 -> adminLogin(sc);
                case 0 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    static void seedData() {
        trains.add(new Train(1, "Delhi", "Mumbai", "2025-04-10", "10:00", 50, 1500));
        trains.add(new Train(2, "Delhi", "Kolkata", "2025-04-11", "12:30", 40, 1800));
        trains.add(new Train(3, "Mumbai", "Bangalore", "2025-04-12", "09:15", 60, 1600));
    }

    static void registerUser(Scanner sc) {
        sc.nextLine();
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();

        users.add(new User(name, email, pass));
        System.out.println("Registration successful!");
    }

    static void loginUser(Scanner sc) {
        sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();

        for (User u : users) {
            if (u.email.equals(email) && u.password.equals(pass)) {
                System.out.println("Welcome, " + u.name + "!");
                userMenu(sc, u);
                return;
            }
        }
        System.out.println("Invalid credentials.");
    }

    static void userMenu(Scanner sc, User user) {
        int choice;
        while (true) {
            System.out.println("\n--- User Menu ---");
            System.out.println("1. Search Trains");
            System.out.println("2. Book Ticket");
            System.out.println("3. View My Bookings");
            System.out.println("4. Cancel Ticket");
            System.out.println("0. Logout");
            System.out.print("Choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> searchTrains(sc);
                case 2 -> bookTicket(sc, user.email);
                case 3 -> viewBookings(user.email);
                case 4 -> cancelTicket(sc, user.email);
                case 0 -> { return; }
                default -> System.out.println("Invalid.");
            }
        }
    }

    static void adminLogin(Scanner sc) {
        sc.nextLine();
        System.out.print("Admin email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();

        if (email.equals(adminEmail) && pass.equals(adminPass)) {
            System.out.println("Admin login success!");
            adminMenu(sc);
        } else {
            System.out.println("Invalid admin credentials.");
        }
    }

    static void adminMenu(Scanner sc) {
        int choice;
        while (true) {
            System.out.println("\n--- Admin Panel ---");
            System.out.println("1. View All Bookings");
            System.out.println("2. Add Train");
            System.out.println("3. View Trains");
            System.out.println("0. Logout");
            System.out.print("Choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> bookings.forEach(System.out::println);
                case 2 -> addTrain(sc);
                case 3 -> trains.forEach(System.out::println);
                case 0 -> { return; }
                default -> System.out.println("Invalid.");
            }
        }
    }

    static void addTrain(Scanner sc) {
        System.out.print("Train ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Source: ");
        String src = sc.nextLine();
        System.out.print("Destination: ");
        String dest = sc.nextLine();
        System.out.print("Date (yyyy-mm-dd): ");
        String date = sc.nextLine();
        System.out.print("Time: ");
        String time = sc.nextLine();
        System.out.print("Seats: ");
        int seats = sc.nextInt();
        System.out.print("Fare: ");
        double fare = sc.nextDouble();

        trains.add(new Train(id, src, dest, date, time, seats, fare));
        System.out.println("Train added.");
    }

    static void searchTrains(Scanner sc) {
        sc.nextLine();
        System.out.print("From: ");
        String src = sc.nextLine();
        System.out.print("To: ");
        String dest = sc.nextLine();

        boolean found = false;
        for (Train t : trains) {
            if (t.source.equalsIgnoreCase(src) && t.destination.equalsIgnoreCase(dest)) {
                System.out.println(t);
                found = true;
            }
        }
        if (!found) System.out.println("No trains found.");
    }

    static void bookTicket(Scanner sc, String email) {
        System.out.print("Train ID: ");
        int trainId = sc.nextInt();
        sc.nextLine(); 
        System.out.print("Passenger Name: ");
        String name = sc.nextLine();

        for (Train t : trains) {
            if (t.id == trainId && t.seats > 0) {
                bookings.add(new Booking(email, trainId, name));
                t.seats--;
                System.out.println("Ticket booked successfully! 🎟️ PNR generated.");
                return;
            }
        }
        System.out.println("Train full or not found.");
    }

    static void viewBookings(String email) {
        boolean found = false;
        for (Booking b : bookings) {
            if (b.userEmail.equals(email)) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) System.out.println("No bookings found.");
    }

    static void cancelTicket(Scanner sc, String email) {
        System.out.print("Enter PNR to cancel: ");
        int pnr = sc.nextInt();

        Booking toCancel = null;
        for (Booking b : bookings) {
            if (b.pnr == pnr && b.userEmail.equals(email)) {
                toCancel = b;
                break;
            }
        }

        if (toCancel != null) {
            bookings.remove(toCancel);
            for (Train t : trains) {
                if (t.id == toCancel.trainId) {
                    t.seats++;
                    break;
                }
            }
            System.out.println("Booking cancelled. 💸 Refund will be processed.");
        } else {
            System.out.println("PNR not found or unauthorized.");
        }
    }
}
