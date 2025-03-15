package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CSVActorReader {
    private final Map<String, Actor> actors = new HashMap<>();

    public void readCSV(String filePath) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                String actorName = nextLine[0];
                String movieName = nextLine[1];
                int startDay = Integer.parseInt(nextLine[2]);
                int startMonth = Integer.parseInt(nextLine[3]);
                int startYear = Integer.parseInt(nextLine[4]);
                int endDay = Integer.parseInt(nextLine[5]);
                int endMonth = Integer.parseInt(nextLine[6]);
                int endYear = Integer.parseInt(nextLine[7]);

                Date startDate = new Date(startDay, startMonth, startYear);
                Date endDate = new Date(endDay, endMonth, endYear);

                Actor actor = actors.getOrDefault(actorName, new Actor(actorName));

                actor.addStarring(new Starring(movieName, startDate, endDate));

                actors.put(actorName, actor);
            }
        } catch (IOException| CsvValidationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Map<String, Actor> getActors() {
        return actors;
    }
}