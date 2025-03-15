package org.example;

import java.util.Map;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // 1
        Date today = new Date(18, 11, 1402);
        System.out.println("Today: " + today);

        Date nextDay = today.nextDay();
        System.out.println("Next day: " + nextDay);
        System.out.println("-----------------------------------------------------");



        // 2
        List<Date> dates = new ArrayList<>();
        dates.add(new Date(25, 03, 1383));
        dates.add(new Date(1, 1, 1404));
        dates.add(new Date(29, 12, 1403));
        
        
        for (Date date : dates) {
            System.out.println(date);
        }

        Collections.sort(dates);
        System.out.println("\nDays sorted: ");
        for (Date date : dates) {
            System.out.println(date);
        }
        System.out.println("-----------------------------------------------------");
        
        
        // 3
        Actor actor = new Actor("James Stewart");
        try {
            System.out.print(actor);
            actor.addStarring(new Starring("Rear Window",
                    new Date(1, 3, 1953),
                    new Date(14, 5, 1953)));
            
            String movieName = "Rear Window";
            int daysInMovie = actor.totalDaysActedInMovie(movieName);
            System.out.println("Days acted in " + movieName + ": " + daysInMovie);
            
            actor.addStarring(new Starring("Vertigo",
                    new Date(1, 4, 1957),
                    new Date(31, 5, 1957)));

            movieName = "Vertigo";
            daysInMovie = actor.totalDaysActedInMovie(movieName);
            System.out.println("Days acted in " + movieName + ": " + daysInMovie);
            
            System.out.println("Total days acted in Rear Window and Vertigo: " + actor.totalDaysActed());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("-----------------------------------------------------");
        

        // 4
        try {
            actor.addStarring(new Starring("sina",
                    new Date(1, 7, 1953),
                    new Date(1, 5, 1953)));

            String movieName = "sina";
            int daysInMovie = actor.totalDaysActedInMovie(movieName);
            System.out.println("Total days acted in " + movieName + ": " + daysInMovie);

            System.out.print(actor);
            System.out.println("Total days acted: " + actor.totalDaysActed());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("-----------------------------------------------------");
        System.out.print(actor);
        System.out.println("-----------------------------------------------------");


        // 5
        System.out.println("Read CSV: ");
        CSVActorReader csvReader = new CSVActorReader();
        csvReader.readCSV("/home/mosipamo/Documents/CA0/src/main/java/org/resources/input.csv");

        Map<String, Actor> actors = csvReader.getActors();
        for (Actor temp_Actor : actors.values()) {
            System.out.println(temp_Actor);
            System.out.println("Total days acted: " + temp_Actor.totalDaysActed() + "\n");
        }
    }
}