package net.ugurkartal;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class GuestListTest {
    @Test
    void shouldBeEmptyInitially() {
        GuestList guestList = new GuestList();
        guestList.setGuests(new ArrayList<>());

        List<Guest> expected = new ArrayList<>();
        List<Guest> actual = guestList.getGuests();

        assertEquals(expected, actual);
    }

    @Test
    void shouldReadSameGuestsAsWrittenBefore() {
        GuestList guestList = new GuestList();
        guestList.setGuests(List.of(new Guest("Karl"), new Guest("Ute")));

        List<Guest> expected = List.of(new Guest("Karl"), new Guest("Ute"));
        List<Guest> actual = guestList.getGuests();

        assertEquals(expected, actual);
    }

    @Test
    void shouldWriteToFileSystem() {
        GuestList guestList = new GuestList();
        guestList.setGuests(List.of(new Guest("Theodor"), new Guest("Anette")));
        guestList.writeToFile();

        GuestList guestListFromFile = new GuestList();
        guestListFromFile.readFromFile();

        List<Guest> expected = List.of(new Guest("Theodor"), new Guest("Anette"));
        List<Guest> actual = guestListFromFile.getGuests();

        assertEquals(expected, actual);
    }

    @Test
    void shouldReadFromFileSystem() {
        GuestList guestList = new GuestList();
        guestList.setGuests(List.of(new Guest("Stephan"), new Guest("Max")));
        guestList.writeToFile();

        List<Guest> expected = List.of(new Guest("Stephan"), new Guest("Max"));
        guestList.readFromFile();
        List<Guest> actual = guestList.getGuests();

        assertEquals(expected, actual);
    }
}