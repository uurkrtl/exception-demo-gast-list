package net.ugurkartal;

import lombok.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class GuestList {
    List<Guest> guests = new ArrayList<>();
    Path filePath = Path.of("guests.csv");

    public void writeToFile() {
        try {
            Files.write(filePath, this.getGuests().stream()
                    .map(guest -> guest.getName())
                    .collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromFile() {
        try {
            this.setGuests(Files.lines(filePath)
                    //.skip(1) // Skip the header
                    .filter(line -> !line.isBlank()) // Remove empty lines
                    .map(line -> {
                        //String[] parts = line.split(",");
                        return new Guest(line); // Convert each line into the class Student
                    })
                    //.distinct() // Remove duplicates
                    .collect(Collectors.toList()) // Collect the output to a list
            );
            //students.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
