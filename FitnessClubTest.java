import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class FitnessClubTest {

    @Test
    public void testAddLesson() {
        FitnessLessonType type = FitnessLessonType.YOGA;
        FitnessLesson lesson = new FitnessLesson("L001", type, "Monday", "9:00AM", 10, 10.0);
        FitnessClub club = new FitnessClub();
        club.addLesson(lesson);
        assertTrue(club.getLessons().contains(lesson));
    }

    @Test
    public void testAddCustomer() {
        Customer customer = new Customer("C001", "John Doe");
        FitnessClub club = new FitnessClub();
        club.addCustomer(customer);
        assertTrue(club.getCustomers().contains(customer));
    }

    @Test
    public void testBookLesson() {
        Customer customer = new Customer("C001", "John Doe");
        FitnessLessonType type = FitnessLessonType.YOGA;
        FitnessLesson lesson = new FitnessLesson("L001", type, "Monday", "9:00AM", 10, 10.0);
        FitnessClub club = new FitnessClub();
        club.addCustomer(customer);
        club.addLesson(lesson);
        club.bookLesson(customer, lesson);
        assertTrue(lesson.getBookedCustomers().contains(customer));
    }

    @Test
    public void testCancelLesson() {
        Customer customer = new Customer("C001", "John Doe");
        FitnessLessonType type = FitnessLessonType.YOGA;
        FitnessLesson lesson = new FitnessLesson("L001", type, "Monday", "9:00AM", 10, 10.0);
        FitnessClub club = new FitnessClub();
        club.addCustomer(customer);
        club.addLesson(lesson);
        club.bookLesson(customer, lesson);
        club.cancelLesson(customer, lesson);
        assertFalse(lesson.getBookedCustomers().contains(customer));
    }

    @Test
    public void testRateLesson() {
        Customer customer = new Customer("C001", "John Doe");
        FitnessLessonType type = FitnessLessonType.YOGA;
        FitnessLesson lesson = new FitnessLesson("L001", type, "Monday", "9:00AM", 10, 10.0);
        FitnessClub club = new FitnessClub();
        club.addCustomer(customer);
        club.addLesson(lesson);
        club.bookLesson(customer, lesson);
        club.rateLesson(customer, lesson, 5);
        assertTrue(lesson.getRatings().get(customer) == 5);
    }

    @Test
    public void testGetAverageRating() {
        Customer customer1 = new Customer("C001", "John Doe");
        Customer customer2 = new Customer("C002", "Jane Smith");
        FitnessLessonType type = FitnessLessonType.YOGA;
        FitnessLesson lesson = new FitnessLesson("L001", type, "Monday", "9:00AM", 10, 10.0);
        FitnessClub club = new FitnessClub();
        club.addCustomer(customer1);
        club.addCustomer(customer2);
        club.addLesson(lesson);
        club.bookLesson(customer1, lesson);
        club.bookLesson(customer2, lesson);
        club.rateLesson(customer1, lesson, 4);
        club.rateLesson(customer2, lesson, 5);
        assertTrue(lesson.getAverageRating() == 4.5);
    }
}
