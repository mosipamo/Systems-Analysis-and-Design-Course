package org.example;

public class Starring {
    private final String movieName;
    private final Date startDate;
    private final Date endDate;

    public Starring(String movieName, Date startDate, Date endDate) {
        if (movieName == null || movieName.isEmpty() || startDate == null || endDate == null) {
            throw new IllegalArgumentException("Invalid arguments for Starring");
        }
        if (startDate.compareTo(endDate) > 0) {
            throw new IllegalArgumentException("Start date must be before or equal to end date");
        }

        this.movieName = movieName;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public String getMovieName() {
        return movieName;
    }

    public boolean overlapsWith(Starring other) {
        return (this.startDate.compareTo(other.endDate) <= 0) &&
               (this.endDate.compareTo(other.startDate) >= 0);
    }

    public int totalDays() {
        int days = 1;
        Date temp = startDate;
        while (!temp.equals(endDate)) {
            temp = temp.nextDay();
            days++;
        }
        return days;
    }

    @Override
    public String toString() {
        return movieName + " (" + startDate + " - " + endDate + ")";
    }
}
