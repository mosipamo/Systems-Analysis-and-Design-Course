package org.example;

import java.util.ArrayList;
import java.util.List;

public class Actor {
    private final String name;
    public final List<Starring> starringList;

    public Actor(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Actor name cannot be null or empty");
        }
        this.name = name;
        this.starringList = new ArrayList<>();
    }

    public void addStarring(Starring starring) {
        for (Starring existing : starringList) {
            if (existing.overlapsWith(starring)) {
                throw new IllegalArgumentException("Overlapping starring periods are not allowed for the actor");
            }
        }
        starringList.add(starring);
    }

    public int totalDaysActed() {
        int totalDays = 0;
        for (Starring s : starringList) {
            totalDays += s.totalDays();
        }
        return totalDays;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Actor: ").append(name).append("\n");
        for (Starring s : starringList) {
            sb.append("  - ").append(s).append("\n");
        }
        return sb.toString();
    }
    
    public int totalDaysActedInMovie(String movieName) {
        return starringList.stream()
            .filter(starring -> starring.getMovieName().equals(movieName))
            .mapToInt(Starring::totalDays)
            .sum();
    }
}
