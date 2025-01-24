//22F-BSE-138
package labexam;

public class MovieBookingApp {
    private int totalSeats = 20; 
    private final Object lock = new Object(); // for synchronization

    public void bookSeats(String user, int seats) {
        synchronized (lock) { 
            try {
                if (seats > totalSeats) {
                    System.out.println(user + " failed to book " + seats + " seats: Not enough seats available.");
                    return;
                }
                totalSeats -= seats;
                System.out.println(user + " successfully booked " + seats + " seats.");
                System.out.println("Seats booked: " + (20 - totalSeats) + ", Remaining seats: " + totalSeats);
            } catch (Exception e) {
                System.out.println(user + " failed to book seats due to an error.");
            }
        }
    }

    public static void main(String[] args) {
        MovieBookingApp bookingApp = new MovieBookingApp();

        Thread userA = new Thread(() -> {
            bookingApp.bookSeats("User A", 10);
        });

        Thread userB = new Thread(() -> {
            bookingApp.bookSeats("User B", 12);
        });

        userA.start();
        userB.start();

        try {
            userA.join();
            userB.join();
        } catch (InterruptedException e) {
            System.out.println("Booking process was interrupted.");
        }
    }
}

System.out.Println("Hello")
