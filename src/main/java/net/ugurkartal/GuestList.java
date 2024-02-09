package net.ugurkartal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GuestList {
    List<Guest> guests = new ArrayList<>();
    Path filePath = Path.of("guests.csv");

    public List<Guest> getGuests() {
        try {
            return Files.lines(filePath)
                    .filter(line -> !line.isBlank())
                    .map(line -> {
                        //String[] parts = line.split(",");
                        return new Guest(line);
                    })
                    .collect(Collectors.toList());
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setGuests(List<Guest> guests) {
        try {
            Files.write(filePath, guests.stream()
                    .map(guest -> guest.getName())
                    .collect(Collectors.toList()));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
