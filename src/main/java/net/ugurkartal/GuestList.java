package net.ugurkartal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GuestList {
    List<Guest> guests = new ArrayList<>();
    private final Path FILE_PATH = Path.of("guests.csv");

    public List<Guest> getGuests() {
        try {
            return Files.lines(FILE_PATH)
                    .filter(line -> !line.isBlank())
                    .map(line -> {return new Guest(line);})
                    .collect(Collectors.toList());
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setGuests(List<Guest> guests) {
        try {
            Files.write(FILE_PATH, guests.stream()
                    .map(guest -> guest.getName())
                    .collect(Collectors.toList()));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Guest> getGuests(Path of) throws IOException {
            return Files.lines(of)
                    .filter(line -> !line.isBlank())
                    .map(line -> {return new Guest(line);})
                    .collect(Collectors.toList());
    }

    public void addGuest(Guest guest) throws IOException {
        Files.write(FILE_PATH, List.of(guest.getName()), StandardOpenOption.APPEND);
    }
}
