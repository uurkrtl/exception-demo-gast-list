package net.ugurkartal;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
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

        List<Guest> expected = List.of(new Guest("Karl"), new Guest("Ute"));
        guestList.setGuests(expected);

        List<Guest> actual = guestList.getGuests();

        assertEquals(expected, actual);
    }

    @Test
    void shouldWriteToFileSystem() {
        GuestList guestList = new GuestList();

        List<Guest> expected = List.of(new Guest("Theodor"), new Guest("Anette"));
        guestList.setGuests(expected);

        List<Guest> actual = guestList.getGuests();

        assertEquals(expected, actual);
    }

    @Test
    void shouldReadFromFileSystem() {
        GuestList guestList = new GuestList();

        List<Guest> expected = List.of(new Guest("Stephan"), new Guest("Max"));
        guestList.setGuests(expected);

        List<Guest> actual = guestList.getGuests();

        assertEquals(expected, actual);
    }

    @Test
    void shouldReadFromFileSystem_whenFileNotExist() {
        GuestList guestList = new GuestList();

        List<Guest> expected = List.of(new Guest("Stephan"), new Guest("Max"));
        guestList.setGuests(expected);

        assertThrows(NoSuchFileException.class, () -> guestList.getGuests(Path.of("notExist.csv")));
    }

    @Test
    void testAddGuest() throws IOException {
        GuestList guestList = new GuestList();
        guestList.setGuests(List.of(new Guest("Stephan"), new Guest("Max")));

        List<Guest> expected = List.of(new Guest("Stephan"), new Guest("Max"), new Guest("Ugur"));

        Guest newGuest = new Guest("Ugur");
        guestList.addGuest(newGuest);

        List<Guest> actual = guestList.getGuests();

        assertEquals(expected, actual);
    }
}