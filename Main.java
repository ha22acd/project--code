import java.util.*;

// Enum for Fitness Lesson Types
enum FitnessLessonType {
    SPIN, YOGA, BODYSCULPT, ZUMBA; // Add more lesson types as needed
}

// Class for Fitness Lesson
class FitnessLesson {
    private String lessonID;
    private FitnessLessonType lessonType;
    private String day;
    private String time;
    private int maxCapacity;
    private int currentCapacity;
    private double price;
    private List<Customer> bookedCustomers;
    private Map<Customer, Integer> ratings;

    public FitnessLesson(String lessonID, FitnessLessonType lessonType, String day, String time, int maxCapacity, double price) {
        this.lessonID = lessonID;
        this.lessonType = lessonType;
        this.day = day;
        this.time = time;
        this.maxCapacity = maxCapacity;
        this.currentCapacity = 0;
        this.price = price;
        this.bookedCustomers = new ArrayList<>();
        this.ratings = new HashMap<>();
    }

    public String getLessonID() {
        return lessonID;
    }

    public FitnessLessonType getLessonType() {
        return lessonType;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public double getPrice() {
        return price;
    }

    public List<Customer> getBookedCustomers() {
        return bookedCustomers;
    }

    public Map<Customer, Integer> getRatings() {
        return ratings;
    }

    public boolean isFull() {
        return currentCapacity >= maxCapacity;
    }

    public void addCustomer(Customer customer) {
        if (!isFull()) {
            bookedCustomers.add(customer);
            currentCapacity++;
        } else {
            System.out.println("Lesson is already full. Booking failed.");
        }
    }

    public void removeCustomer(Customer customer) {
        if (bookedCustomers.contains(customer)) {
            bookedCustomers.remove(customer);
            currentCapacity--;
        } else {
            System.out.println("Customer not found in booked customers. Cancellation failed.");
        }
    }

    public void addRating(Customer customer, int rating) {
        if (bookedCustomers.contains(customer)) {
            ratings.put(customer, rating);
        } else {
            System.out.println("Customer not found in booked customers. Rating failed.");
        }
    }

    public double getAverageRating() {
        if (ratings.isEmpty()) {
            return 0.0;
        }
        double totalRating = 0;
        for (int rating : ratings.values()) {
            totalRating += rating;
        }
        return totalRating / ratings.size();
    }
}

// Class for Customer
class Customer {
    private String customerID;
    private String name;

    public Customer(String customerID, String name) {
        this.customerID = customerID;
        this.name = name;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }
}

// Class for Fitness Club
class FitnessClub {
    private List<FitnessLesson> lessons;
    private List<Customer> customers;

    public FitnessClub() {
        this.lessons = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    public void addLesson(FitnessLesson lesson) {
        lessons.add(lesson);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void bookLesson(Customer customer, FitnessLesson lesson) {
        if (customers.contains(customer) && lessons.contains(lesson)) {
            if (!lesson.isFull() && !lesson.getBookedCustomers().contains(customer)) {
                lesson.addCustomer(customer);
                System.out.println("Lesson booked successfully for " + customer.getName());
            } else {
                System.out.println("Lesson is already full or customer has already booked this lesson.");
            }
        } else {
            System.out.println("Customer or lesson not found. Booking failed.");
        }
    }

    public void cancelLesson(Customer customer, FitnessLesson lesson) {
        if (customers.contains(customer) && lessons.contains(lesson)) {
            if (lesson.getBookedCustomers().contains(customer)) {
                lesson.removeCustomer(customer);
                System.out.println("Lesson cancelled successfully for " + customer.getName());
            } else {
                System.out.println("Customer not found in booked customers. Cancellation failed.");
            }
        } else {
            System.out.println("Customer or lesson not found. Cancellation failed.");
        }
    }

    public void rateLesson(Customer customer, FitnessLesson lesson, int rating) {
        if (customers.contains(customer) && lessons.contains(lesson)) {
            if (lesson.getBookedCustomers().contains(customer)) {
                lesson.addRating(customer, rating);
                System.out.println("Rating added successfully for " + customer.getName());
            } else {
                System.out.println("Customer not found in booked customers. Rating failed.");
            }
        } else {
            System.out.println("Customer or lesson not found. Rating failed.");
        }
    }

    public void displayLessons() {
        System.out.println("Available Lessons:");
        for (FitnessLesson lesson : lessons) {
            System.out.println("Lesson ID: " + lesson.getLessonID());
            System.out.println("Lesson Type: " + lesson.getLessonType());
            System.out.println("Day: " + lesson.getDay());
            System.out.println("Time: " + lesson.getTime());
            System.out.println("Max Capacity: " + lesson.getMaxCapacity());
            System.out.println("Current Capacity: " + lesson.getCurrentCapacity());
            System.out.println("Price: " + lesson.getPrice());
            System.out.println("Booked Customers: " + lesson.getBookedCustomers().size());
            System.out.println("Average Rating: " + lesson.getAverageRating());
            System.out.println("------------------------------");
        }
    }

    public void getHighestIncomeLessonType() {
        Map<FitnessLessonType, Double> lessonTypeIncomeMap = new HashMap<>();
        for (FitnessLesson lesson : lessons) {
            FitnessLessonType lessonType = lesson.getLessonType();
            double lessonIncome = lesson.getPrice() * lesson.getBookedCustomers().size();
            if (lessonTypeIncomeMap.containsKey(lessonType)) {
                lessonTypeIncomeMap.put(lessonType, lessonTypeIncomeMap.get(lessonType) + lessonIncome);
            } else {
                lessonTypeIncomeMap.put(lessonType, lessonIncome);
            }
        }
        FitnessLessonType highestIncomeLessonType = null;
        double highestIncome = 0.0;
        for (Map.Entry<FitnessLessonType, Double> entry : lessonTypeIncomeMap.entrySet()) {
            if (entry.getValue() > highestIncome) {
                highestIncome = entry.getValue();
                highestIncomeLessonType = entry.getKey();
            }
        }
        System.out.println("The type of fitness lesson which has generated the highest income is: " + highestIncomeLessonType);
    }
    public void get_customers_and_rating_per_lesson() {
        System.out.println("Customers and Ratings per Lesson:");
        Map<String, Map<String, Pair<Integer, Double>>> report = new HashMap<>(); // Map of day -> Map of lesson type -> Pair of total customers and total rating

        // Loop through each lesson and update the report accordingly
        for (FitnessLesson lesson : lessons) {
            String day = lesson.getDay();
            String lessonType = lesson.getLessonType().toString();
            int numCustomers = lesson.getBookedCustomers().size();
            double rating = lesson.getAverageRating();

            // If the day doesn't exist in the report, create a new map for it
            if (!report.containsKey(day)) {
                report.put(day, new HashMap<>());
            }

            // If the lesson type doesn't exist for this day in the report, create a new pair for it
            if (!report.get(day).containsKey(lessonType)) {
                report.get(day).put(lessonType, new Pair<>(0, 0.0));
            }

            // Update the pair for this lesson type and day with the new customer and rating values
            Pair<Integer, Double> pair = report.get(day).get(lessonType);
            int totalCustomers = pair.getFirst() + numCustomers;
            double totalRating = pair.getSecond() + rating;
            report.get(day).put(lessonType, new Pair<>(totalCustomers, totalRating));
        }

        // Print out the final report
        for (String day : report.keySet()) {
            System.out.println(day + ":");
            Map<String, Pair<Integer, Double>> lessonsForDay = report.get(day);
            for (String lessonType : lessonsForDay.keySet()) {
                Pair<Integer, Double> pair = lessonsForDay.get(lessonType);
                int totalCustomers = pair.getFirst();
                double averageRating = totalCustomers > 0 ? pair.getSecond() / totalCustomers : 0.0; // Calculate the average rating
                System.out.println("- " + lessonType + ": " + totalCustomers + " customers, " + averageRating + " average rating");
            }
        }
    }


    public void displayLessons_by_day(String lesson_day) {
        System.out.println("Available Lessons:");
        for (FitnessLesson lesson : lessons) {
            if (lesson_day.equals(lesson.getDay())) {
                System.out.println("Lesson ID: " + lesson.getLessonID());
                System.out.println("Lesson Type: " + lesson.getLessonType());
                System.out.println("Day: " + lesson.getDay());
                System.out.println("Time: " + lesson.getTime());
                System.out.println("Max Capacity: " + lesson.getMaxCapacity());
                System.out.println("Current Capacity: " + lesson.getCurrentCapacity());
                System.out.println("Price: " + lesson.getPrice());
                System.out.println("Booked Customers: " + lesson.getBookedCustomers().size());
                System.out.println("Average Rating: " + lesson.getAverageRating());
                System.out.println("------------------------------");
            }}
    }

    public void displayLessons_by_type(String lesson_type) {
        System.out.println("Available Lessons:");
        for (FitnessLesson lesson : lessons) {
            if (lesson_type.equals(lesson.getLessonType().toString())) {
                System.out.println("Lesson ID: " + lesson.getLessonID());
                System.out.println("Lesson Type: " + lesson.getLessonType());
                System.out.println("Day: " + lesson.getDay());
                System.out.println("Time: " + lesson.getTime());
                System.out.println("Max Capacity: " + lesson.getMaxCapacity());
                System.out.println("Current Capacity: " + lesson.getCurrentCapacity());
                System.out.println("Price: " + lesson.getPrice());
                System.out.println("Booked Customers: " + lesson.getBookedCustomers().size());
                System.out.println("Average Rating: " + lesson.getAverageRating());
                System.out.println("------------------------------");
            }
        }
    }

    public void displayCustomers() {
        System.out.println("Registered Customers:");
        for (Customer customer : customers) {
            System.out.println("Customer ID: " + customer.getCustomerID());
            System.out.println("Name: " + customer.getName());
            System.out.println("--------------------------");
        }
    }

    public List<FitnessLesson> getLessons() {
        return lessons;
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}

public class Main {
    public static void main(String[] args) {
        // Create FitnessClub object
        FitnessClub fitnessClub = new FitnessClub();

        // Create FitnessLesson objects
        List<FitnessLesson> timetable = new ArrayList<>();

        timetable.add(new FitnessLesson("L001", FitnessLessonType.SPIN, "Saturday", "04-01-2023 18:00", 5, 15.0));
        timetable.add(new FitnessLesson("L002", FitnessLessonType.YOGA, "Saturday", "04-01-2023 09:00", 5, 12.0));
        timetable.add(new FitnessLesson("L003", FitnessLessonType.BODYSCULPT, "Sunday", "04-02-2023 20:30", 5, 18.0));
        timetable.add(new FitnessLesson("L004", FitnessLessonType.ZUMBA, "Sunday", "04-02-2023 17:30", 5, 10.0));

        timetable.add(new FitnessLesson("L005", FitnessLessonType.YOGA, "Saturday", "04-08-2023 10:00", 5, 12.0));
        timetable.add(new FitnessLesson("L006", FitnessLessonType.ZUMBA, "Saturday", "04-08-2023 12:00", 5, 10.0));
        timetable.add(new FitnessLesson("L007", FitnessLessonType.BODYSCULPT, "Sunday", "04-09-2023 09:30", 5, 18.0));
        timetable.add(new FitnessLesson("L008", FitnessLessonType.YOGA, "Sunday", "04-09-2023 16:00", 5, 12.0));

        timetable.add(new FitnessLesson("L009", FitnessLessonType.YOGA, "Saturday", "04-15-2023 14:00", 5, 12.0));
        timetable.add(new FitnessLesson("L010", FitnessLessonType.ZUMBA, "Saturday", "04-15-2023 18:00", 5, 10.0));
        timetable.add(new FitnessLesson("L011", FitnessLessonType.ZUMBA, "Sunday", "04-16-2023 10:30", 5, 10.0));
        timetable.add(new FitnessLesson("L012", FitnessLessonType.BODYSCULPT, "Sunday", "04-16-2023 17:00", 5, 18.0));

        timetable.add(new FitnessLesson("L013", FitnessLessonType.SPIN, "Saturday", "04-22-2023 09:00", 5, 15.0));
        timetable.add(new FitnessLesson("L014", FitnessLessonType.YOGA, "Saturday", "04-22-2023 11:00", 5, 12.0));
        timetable.add(new FitnessLesson("L015", FitnessLessonType.YOGA, "Sunday", "04-23-2023 14:30", 5, 12.0));
        timetable.add(new FitnessLesson("L016", FitnessLessonType.BODYSCULPT, "Sunday", "04-23-2023 19:00", 5, 18.0));

        timetable.add(new FitnessLesson("L017", FitnessLessonType.BODYSCULPT, "Saturday", "04-29-2023 16:00", 5, 18.0));
        timetable.add(new FitnessLesson("L018", FitnessLessonType.ZUMBA, "Saturday", "04-29-2023 19:00", 5, 10.0));
        timetable.add(new FitnessLesson("L019", FitnessLessonType.SPIN, "Sunday", "04-30-2023 11:30", 5, 15.0));
        timetable.add(new FitnessLesson("L020", FitnessLessonType.SPIN, "Sunday", "04-30-2023 15:30", 5, 15.0));
        timetable.add(new FitnessLesson("L021", FitnessLessonType.YOGA, "Saturday", "05-06-2023 10:00", 5, 12.0));
        timetable.add(new FitnessLesson("L022", FitnessLessonType.ZUMBA, "Saturday", "05-06-2023 12:00", 5, 10.0));
        timetable.add(new FitnessLesson("L023", FitnessLessonType.BODYSCULPT, "Sunday", "05-07-2023 09:30", 5, 18.0));
        timetable.add(new FitnessLesson("L024", FitnessLessonType.YOGA, "Sunday", "05-07-2023 16:00", 5, 12.0));
        timetable.add(new FitnessLesson("L025", FitnessLessonType.YOGA, "Saturday", "05-13-2023 14:00", 5, 12.0));
        timetable.add(new FitnessLesson("L026", FitnessLessonType.ZUMBA, "Saturday", "05-13-2023 18:00", 5, 10.0));
        timetable.add(new FitnessLesson("L027", FitnessLessonType.ZUMBA, "Sunday", "05-14-2023 10:30", 5, 10.0));
        timetable.add(new FitnessLesson("L028", FitnessLessonType.BODYSCULPT, "Sunday", "05-14-2023 17:00", 5, 18.0));

        timetable.add(new FitnessLesson("L029", FitnessLessonType.SPIN, "Saturday", "05-20-2023 09:00", 5, 15.0));
        timetable.add(new FitnessLesson("L030", FitnessLessonType.YOGA, "Saturday", "05-20-2023 11:00", 5, 12.0));
        timetable.add(new FitnessLesson("L031", FitnessLessonType.YOGA, "Sunday", "05-21-2023 14:30", 5, 12.0));
        timetable.add(new FitnessLesson("L032", FitnessLessonType.BODYSCULPT, "Sunday", "05-21-2023 19:00", 5, 18.0));


                // Add FitnessLesson objects to FitnessClub
        for (FitnessLesson lesson: timetable){
            fitnessClub.addLesson(lesson);
        }

        // Create Customer objects

        List<Customer> customers = new ArrayList<>();
        Customer customer1 = new Customer("C001", "John");
        Customer customer2 = new Customer("C002", "Jane");
        Customer customer3 = new Customer("C003", "Alice");
        Customer customer4 = new Customer("C004", "Bob");
        Customer customer5 = new Customer("C005", "Ron");
        Customer customer6 = new Customer("C006", "Harry");
        Customer customer7 = new Customer("C007", "Draco");
        Customer customer8 = new Customer("C008", "Ginny");
        Customer customer9 = new Customer("C009", "Malfoy");

        // Add Customer objects to FitnessClub
        fitnessClub.addCustomer(customer1);
        fitnessClub.addCustomer(customer2);
        fitnessClub.addCustomer(customer3);
        fitnessClub.addCustomer(customer4);
        fitnessClub.addCustomer(customer5);
        fitnessClub.addCustomer(customer6);
        fitnessClub.addCustomer(customer7);
        fitnessClub.addCustomer(customer8);
        fitnessClub.addCustomer(customer9);

        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);
        customers.add(customer4);
        customers.add(customer5);
        customers.add(customer6);
        customers.add(customer7);
        customers.add(customer8);
        customers.add(customer9);

        // Book lessons for customers
        fitnessClub.bookLesson(customer1, timetable.get(0));
        fitnessClub.bookLesson(customer2, timetable.get(1));
        fitnessClub.bookLesson(customer3, timetable.get(2));
        fitnessClub.bookLesson(customer4, timetable.get(3));
        fitnessClub.bookLesson(customer4, timetable.get(0)); // Trying to book the same lesson again

        // Cancel lessons for customers
        fitnessClub.cancelLesson(customer1, timetable.get(0));
        fitnessClub.cancelLesson(customer2, timetable.get(1));

        // Rate lessons by customers
        fitnessClub.rateLesson(customer1, timetable.get(0), 5);
        fitnessClub.rateLesson(customer2, timetable.get(1), 4);
        fitnessClub.rateLesson(customer3, timetable.get(2), 3);
        fitnessClub.rateLesson(customer4, timetable.get(3), 2);

        // Display available lessons and registered customers
        while(true) {// Create a scanner to get user input
            Scanner scanner = new Scanner(System.in);
            // Print the menu
            System.out.println("Welcome to the fitness center booking system!");
            System.out.println("Please choose an option:");
            System.out.println("1. View the timetable by day");
            System.out.println("2. View the timetable by fitness type");
            System.out.println("3. Book a lesson");
            System.out.println("4. Cancel a booking");
            System.out.println("5. Write a review");
            System.out.println("6. Print the timetable");
            System.out.println("7. A report containing the number of customers per lesson on each day, along with the average rating of each lesson;");
            System.out.println("8. A report containing the type of fitness lessons which has generated the highest income, counting all the same type of lessons together.");
            System.out.println("9. Add a customer");
            System.out.println("10. Exit");

            // Get the user's choice
            int choice = scanner.nextInt();

            if (choice == 1){
                System.out.print("Enter a day Saturday or Sunday: ");
                scanner.nextLine();
                String lesson_day = scanner.nextLine();
                fitnessClub.displayLessons_by_day(lesson_day);
            }

            if (choice == 2){
                System.out.print("Enter lesson type: SPIN, YOGA, ZUMBA,BODYSCUPLT");
                scanner.nextLine();
                String lesson_type = scanner.nextLine();
                fitnessClub.displayLessons_by_type(lesson_type);
            }

            if (choice == 3){
                System.out.println("Please select customer number:");
                for (int i = 0; i < customers.size(); i++) {
                    System.out.println((i + 1) + ". " + customers.get(i).getName());
                }
                int customerChoice2 = scanner.nextInt();
                Customer customer_2 = customers.get(customerChoice2 - 1);

                System.out.println("Please choose lesson number you want to book:");
                for (int i = 0; i < timetable.size(); i++) {
                    System.out.println((i + 1) + ". " + timetable.get(i).getLessonType() + "Lesson day" + timetable.get(i).getTime());
                }
                int lessonChoice2 = scanner.nextInt();
                FitnessLesson bookedLesson = timetable.get(lessonChoice2 - 1);
                fitnessClub.bookLesson(customer_2, bookedLesson);
            }

            if (choice == 4){
                System.out.println("Please select customer number:");
                for (int i = 0; i < customers.size(); i++) {
                    System.out.println((i + 1) + ". " + customers.get(i).getName());
                }
                int customerChoice3 = scanner.nextInt();
                Customer customer_3 = customers.get(customerChoice3 - 1);

                System.out.println("Please choose lesson number you want to cancel:");
                for (int i = 0; i < timetable.size(); i++) {
                    System.out.println((i + 1) + ". " + timetable.get(i).getLessonType() + "Lesson day" + timetable.get(i).getTime());
                }
                int lessonChoice3 = scanner.nextInt();
                FitnessLesson bookedLesson1 = timetable.get(lessonChoice3 - 1);
                fitnessClub.cancelLesson(customer_3, bookedLesson1);
            }

            if (choice == 5){
                System.out.println("Please select customer number:");
                for (int i = 0; i < customers.size(); i++) {
                    System.out.println((i + 1) + ". " + customers.get(i).getName());
                }
                int customerChoice4 = scanner.nextInt();
                Customer customer_4 = customers.get(customerChoice4 - 1);

                System.out.println("Please choose lesson number you want to rate:");
                for (int i = 0; i < timetable.size(); i++) {
                    System.out.println((i + 1) + ". " + timetable.get(i).getLessonType() + "Lesson day" + timetable.get(i).getTime());
                }

                System.out.println("Please input rating from 1 to 5:");
                int rating = scanner.nextInt();

                FitnessLesson bookedLesson2 = timetable.get(rating - 1);
                fitnessClub.rateLesson(customer_4, bookedLesson2, rating);
            }

            if (choice == 6){
                fitnessClub.displayLessons();
            }
            if (choice == 7){
                fitnessClub.getHighestIncomeLessonType();
            }
            if (choice == 8){
                fitnessClub.get_customers_and_rating_per_lesson();
            }
            if (choice == 9){
                System.out.print("Enter customer id: ");
                scanner.nextLine();
                String customer_id = scanner.nextLine();
                System.out.print("Enter customer name: ");
                scanner.nextLine();
                String customer_name = scanner.nextLine();
                Customer customer10 = new Customer(customer_id, customer_name);

                // Add Customer objects to FitnessClub
                fitnessClub.addCustomer(customer10);
                customers.add(customer10);
            }
            if (choice == 10){
                break;
            }

        }
    }
}