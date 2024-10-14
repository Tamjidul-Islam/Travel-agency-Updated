import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Welcome to the Travel Agency System");

            System.out.println("Login as: ");
            System.out.println("1. Admin");
            System.out.println("2. Employee");

            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                AdminFunctionality adminFunctionality = new AdminFunctionality();
                adminFunctionality.startAdminSession();
                AdminFunctionality admin = new AdminFunctionality();
                admin.adminMenu();
            } else if (choice == 2) {
                EmployeeFunctionality employee = new EmployeeFunctionality();
                employee.employeeMenu();
            } else {
                System.out.println("Invalid choice. Exiting...");
                System.exit(0);
            }
        }
    }
}

class AdminFunctionality {
    private Admin admin;
    private EmployeeManager employeeManager;
    private PackageManager packageManager;
    private UserManager userManager;
    private RecordManager recordManager;

    public AdminFunctionality() {
        admin = new Admin("tamjid", "mohim123");
        employeeManager = new EmployeeManager();
        packageManager = new PackageManager();
        userManager = new UserManager();
        recordManager = new RecordManager();
    }
    public void startAdminSession() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Admin Login Required.");
        System.out.print("Enter Username: ");
        String inputUsername = scanner.nextLine();

        System.out.print("Enter Password: ");
        String inputPassword = scanner.nextLine();

        if (admin.login(inputUsername, inputPassword)) {
            System.out.println("Login successful. Welcome, " + inputUsername + "!");
            adminMenu();
        } else {
            System.out.println("Invalid username or password. Access denied.");
        }
    }

    public void adminMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Admin Menu:");
            System.out.println("1. Manage Employees");
            System.out.println("2. Add or Delete Packages");
            System.out.println("3. Add or Delete Users");
            System.out.println("4. Review Records and Actions");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    manageEmployees();
                    break;
                case 2:
                    managePackages();
                    break;
                case 3:
                    manageUsers();
                    break;
                case 4:
                    reviewRecords();
                    break;
                case 5:
                    System.out.println("Logging out of Admin Menu...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    public class Admin {
        private String username;
        private String password;

        public Admin(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public boolean login(String inputUsername, String inputPassword) {
            return inputUsername.equals(username) && inputPassword.equals(password);
        }
    }

    public class Employee {
        private int id;
        private String name;
        private String role;

        public Employee(int id, String name, String role) {
            this.id = id;
            this.name = name;
            this.role = role;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getRole() {
            return role;
        }

        public String toString() {
            return "Employee ID: " + id + ", Name: " + name + ", Role: " + role;
        }
    }
    public class EmployeeManager {

        private ArrayList<Employee> employees;
        private Scanner scanner;

        public EmployeeManager() {
            employees = new ArrayList<>();
            scanner = new Scanner(System.in);
        }

        public void manageEmployeesMenu() {
            while (true) {
                System.out.println("\nManage Employees:");
                System.out.println("1. Add Employee");
                System.out.println("2. Delete Employee");
                System.out.println("3. View All Employees");
                System.out.println("4. Exit");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addEmployee();
                        break;
                    case 2:
                        deleteEmployee();
                        break;
                    case 3:
                        viewAllEmployees();
                        break;
                    case 4:
                        System.out.println("Exiting employee management...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }

        // Add a new employee
        private void addEmployee() {
            System.out.println("Enter Employee ID: ");
            int id = scanner.nextInt();

            scanner.nextLine(); // Consume newline
            System.out.println("Enter Employee Name: ");
            String name = scanner.nextLine();

            System.out.println("Enter Employee Role: ");
            String role = scanner.nextLine();

            Employee employee = new Employee(id, name, role);
            employees.add(employee);
            System.out.println("Employee added successfully!");
        }

        // Delete an employee by ID
        private void deleteEmployee() {
            System.out.println("Enter Employee ID to delete: ");
            int id = scanner.nextInt();

            boolean found = false;
            for (Employee emp : employees) {
                if (emp.getId() == id) {
                    employees.remove(emp);
                    System.out.println("Employee with ID " + id + " deleted.");
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Employee with ID " + id + " not found.");
            }
        }

        // View all employees
        private void viewAllEmployees() {
            if (employees.isEmpty()) {
                System.out.println("No employees found.");
            } else {
                System.out.println("List of Employees:");
                for (Employee emp : employees) {
                    System.out.println(emp);
                }
            }
        }
    }

    public class Package {
        private int packageId;
        private String packageName;
        private double price;

        public Package(int packageId, String packageName, double price) {
            this.packageId = packageId;
            this.packageName = packageName;
            this.price = price;
        }

        public int getPackageId() {
            return packageId;
        }

        public String getPackageName() {
            return packageName;
        }

        public double getPrice() {
            return price;
        }

        public String toString() {
            return "Package ID: " + packageId + ", Name: " + packageName + ", Price: $" + price;
        }
    }

    public class PackageManager {

        private ArrayList<Package> packages;
        private Scanner scanner;

        public PackageManager() {
            packages = new ArrayList<>();
            scanner = new Scanner(System.in);
        }

        public void managePackagesMenu() {
            while (true) {
                System.out.println("\nManage Travel Packages:");
                System.out.println("1. Add Package");
                System.out.println("2. Delete Package");
                System.out.println("3. View All Packages");
                System.out.println("4. Exit");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addPackage();
                        break;
                    case 2:
                        deletePackage();
                        break;
                    case 3:
                        viewAllPackages();
                        break;
                    case 4:
                        System.out.println("Exiting package management...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }

        // Add a new travel package
        private void addPackage() {
            System.out.println("Enter Package ID: ");
            int packageId = scanner.nextInt();

            scanner.nextLine(); // Consume newline
            System.out.println("Enter Package Name: ");
            String packageName = scanner.nextLine();

            System.out.println("Enter Package Price: ");
            double price = scanner.nextDouble();

            Package travelPackage = new Package(packageId, packageName, price);
            packages.add(travelPackage);
            System.out.println("Package added successfully!");
        }

        // Delete a travel package by ID
        private void deletePackage() {
            System.out.println("Enter Package ID to delete: ");
            int packageId = scanner.nextInt();

            boolean found = false;
            for (Package pkg : packages) {
                if (pkg.getPackageId() == packageId) {
                    packages.remove(pkg);
                    System.out.println("Package with ID " + packageId + " deleted.");
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Package with ID " + packageId + " not found.");
            }
        }

        // View all available travel packages
        private void viewAllPackages() {
            if (packages.isEmpty()) {
                System.out.println("No packages found.");
            } else {
                System.out.println("List of Packages:");
                for (Package pkg : packages) {
                    System.out.println(pkg);
                }
            }
        }
    }

    public class User {
        private int userId;
        private String userName;
        private String email;

        public User(int userId, String userName, String email) {
            this.userId = userId;
            this.userName = userName;
            this.email = email;
        }

        public int getUserId() {
            return userId;
        }

        public String getUserName() {
            return userName;
        }

        public String getEmail() {
            return email;
        }

        public String toString() {
            return "User ID: " + userId + ", Name: " + userName + ", Email: " + email;
        }
    }
    public class UserManager {

        private ArrayList<User> users;
        private Scanner scanner;

        public UserManager() {
            users = new ArrayList<>();
            scanner = new Scanner(System.in);
        }

        public void manageUsersMenu() {
            while (true) {
                System.out.println("\nManage Users:");
                System.out.println("1. Add User");
                System.out.println("2. Delete User");
                System.out.println("3. View All Users");
                System.out.println("4. Exit");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addUser();
                        break;
                    case 2:
                        deleteUser();
                        break;
                    case 3:
                        viewAllUsers();
                        break;
                    case 4:
                        System.out.println("Exiting user management...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }

        // Add a new user
        private void addUser() {
            System.out.println("Enter User ID: ");
            int userId = scanner.nextInt();

            scanner.nextLine(); // Consume newline
            System.out.println("Enter User Name: ");
            String userName = scanner.nextLine();

            System.out.println("Enter User Email: ");
            String email = scanner.nextLine();

            User user = new User(userId, userName, email);
            users.add(user);
            System.out.println("User added successfully!");
        }

        // Delete a user by ID
        private void deleteUser() {
            System.out.println("Enter User ID to delete: ");
            int userId = scanner.nextInt();

            boolean found = false;
            for (User user : users) {
                if (user.getUserId() == userId) {
                    users.remove(user);
                    System.out.println("User with ID " + userId + " deleted.");
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("User with ID " + userId + " not found.");
            }
        }

        // View all users
        private void viewAllUsers() {
            if (users.isEmpty()) {
                System.out.println("No users found.");
            } else {
                System.out.println("List of Users:");
                for (User user : users) {
                    System.out.println(user);
                }
            }
        }
    }

    public class Record {
        private String action;
        private String userType; // Admin or Employee
        private LocalDateTime timestamp;

        public Record(String action, String userType) {
            this.action = action;
            this.userType = userType;
            this.timestamp = LocalDateTime.now();
        }

        public String getAction() {
            return action;
        }

        public String getUserType() {
            return userType;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public String toString() {
            return "[" + timestamp + "] " + userType + ": " + action;
        }
    }

    public class RecordManager {

        private ArrayList<Record> records;

        public RecordManager() {
            records = new ArrayList<>();
        }

        // Add a record to the log
        public void addRecord(String action, String userType) {
            Record record = new Record(action, userType);
            records.add(record);
        }

        // Review all records
        public void reviewRecords() {
            if (records.isEmpty()) {
                System.out.println("No records to display.");
            } else {
                System.out.println("Reviewing Records:");
                for (Record record : records) {
                    System.out.println(record);
                }
            }
        }
    }





    private void manageEmployees() {
        employeeManager.manageEmployeesMenu();
    }

    private void managePackages() {
        packageManager.managePackagesMenu();
    }

    private void manageUsers() {
        userManager.manageUsersMenu();
    }

    private void reviewRecords() {
        recordManager.reviewRecords();
    }
}

class EmployeeFunctionality {

    public void employeeMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Employee Menu:");
            System.out.println("1. Booking");
            System.out.println("2. Services");
            System.out.println("3. Customer Management");
            System.out.println("4. Transitions");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    bookingMenu();
                    break;
                case 2:
                    servicesMenu();
                    break;
                case 3:
                    customerManagement();
                    break;
                case 4:
                    transitions();
                    break;
                case 5:
                    System.out.println("Exiting Employee Menu...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void bookingMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Booking Options:");
        System.out.println("1. Hotel");
        System.out.println("2. Transport");

        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println("Booking Hotel...");
            // Logic for hotel booking
        } else if (choice == 2) {
            System.out.println("Booking Transport...");
            // Logic for transport booking
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private void servicesMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Services Options:");
        System.out.println("1. Room Service");
        System.out.println("2. Food");

        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println("Room Service...");
            // Logic for room service
        } else if (choice == 2) {
            System.out.println("Ordering Food...");
            // Logic for food service
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private void customerManagement() {
        System.out.println("Managing Customers...");
        // Logic for customer management
    }

    private void transitions() {
        System.out.println("Handling Transitions...");
        // Logic for transitions
    }
}
