public class CinemaTheatre {
    private int[][][] seats;

    public CinemaTheatre() {
        this.seats = new int[5][10][20];
        initializeSeats();
    }

    private void initializeSeats() {
        for (int hall = 0; hall < 5; hall++) {
            for (int row = 0; row < 10; row++) {
                for (int seat = 0; seat < 20; seat++) {
                    seats[hall][row][seat] = 0; 
                }
            }
        }
    }

    public void bookSeats(int hallNumber, int row, int[] seatNumbers) {
        for (int seat : seatNumbers) {
            if (seats[hallNumber - 1][row - 1][seat - 1] == 0) {
                seats[hallNumber - 1][row - 1][seat - 1] = 1; 
            } else {
                System.out.println("Seat " + seat + " in Row " + row + " of Hall " + hallNumber + " is already booked.");
            }
        }
    }

    public void cancelBooking(int hallNumber, int row, int[] seatNumbers) {
        for (int seat : seatNumbers) {
            if (seats[hallNumber - 1][row - 1][seat - 1] == 1) {
                seats[hallNumber - 1][row - 1][seat - 1] = 0; 
            } else {
                System.out.println("Seat " + seat + " in Row " + row + " of Hall " + hallNumber + " is not booked.");
            }
        }
    }

    public boolean checkAvailability(int hallNumber, int numSeats) {
        for (int row = 0; row < 10; row++) {
            int consecutiveSeats = 0;
            for (int seat = 0; seat < 20; seat++) {
                if (seats[hallNumber - 1][row][seat] == 0) {
                    consecutiveSeats++;
                    if (consecutiveSeats == numSeats) {
                        return true;
                    }
                } else {
                    consecutiveSeats = 0;
                }
            }
        }
        return false;
    }

   public void printSeatingArrangement(int hallNumber) {
    System.out.println("Seating Arrangement for Theater Hall " + hallNumber + ":");
    for (int row = 0; row < 10; row++) {
        System.out.print("Row " + (row + 1) + ": ");
        for (int seat = 0; seat < 20; seat++) {
            if (seats[hallNumber - 1][row][seat] == 0) {
                System.out.print("O ");
            } else {
                System.out.print("X "); 
            }
        }
        System.out.println();
    }
}

    public static void main(String[] args) {
        CinemaTheatre cinema = new CinemaTheatre();
        
        // Бронювання
        cinema.bookSeats(1, 4, new int[]{5, 6, 7, 8});
        cinema.cancelBooking(1, 4, new int[]{6});
        cinema.printSeatingArrangement(1);
    }
}
