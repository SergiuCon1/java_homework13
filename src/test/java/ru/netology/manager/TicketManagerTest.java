package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TicketManagerTest {

    Ticket ticketOne = new Ticket(1, 150, "KIV", "FRA", 120);
    Ticket ticketTwo = new Ticket(2, 200, "AMS", "NYC", 360);
    Ticket ticketThree = new Ticket(3, 300, "CHI", "MOW", 720);
    Ticket ticketFour = new Ticket(4, 250, "KJA", "LON", 540);
    Ticket ticketFive = new Ticket(5, 100, "CHI", "MOW", 700);

    @Test
    void shouldRemoveOneTicketByIdWhenExistOneTicket() {
        TicketRepository repository = new TicketRepository();

        repository.saveTicket(ticketOne);

        repository.removeById(1);

        Ticket[] expected = {};
        Ticket[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveOneTicketByIdWhenExistSeveralTickets() {
        TicketRepository repository = new TicketRepository();

        repository.saveTicket(ticketOne);
        repository.saveTicket(ticketTwo);
        repository.saveTicket(ticketThree);
        repository.saveTicket(ticketFour);

        repository.removeById(2);

        Ticket[] expected = {ticketOne, ticketThree, ticketFour};
        Ticket[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }


    @Test
    void shouldSearchByAirportFromAndAirportToWhenBothExist() {
        TicketManager manager = new TicketManager();

        manager.addProduct(ticketOne);
        manager.addProduct(ticketTwo);
        manager.addProduct(ticketThree);
        manager.addProduct(ticketFour);

        Ticket[] expected = {ticketThree};
        Ticket[] actual = manager.searchBy("CHI", "MOW");

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchByAirportFromAndAirportToWhenFirstExist() {
        TicketManager manager = new TicketManager();

        manager.addProduct(ticketOne);
        manager.addProduct(ticketTwo);
        manager.addProduct(ticketThree);
        manager.addProduct(ticketFour);

        Ticket[] expected = {};
        Ticket[] actual = manager.searchBy("CHI", "PFP");

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchByAirportFromAndAirportToWhenSecondExist() {
        TicketManager manager = new TicketManager();

        manager.addProduct(ticketOne);
        manager.addProduct(ticketTwo);
        manager.addProduct(ticketThree);
        manager.addProduct(ticketFour);

        Ticket[] expected = {};
        Ticket[] actual = manager.searchBy("PFP", "MOW");

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchByAirportFromAndAirportToWhenBothExistButDifferentTickets() {
        TicketManager manager = new TicketManager();

        manager.addProduct(ticketOne);
        manager.addProduct(ticketTwo);
        manager.addProduct(ticketThree);
        manager.addProduct(ticketFour);

        Ticket[] expected = {};
        Ticket[] actual = manager.searchBy("KJA", "MOW");

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchByAirportFromAndAirportToWhenExistSeveralTickets() {
        TicketManager manager = new TicketManager();

        manager.addProduct(ticketOne);
        manager.addProduct(ticketTwo);
        manager.addProduct(ticketThree);
        manager.addProduct(ticketFour);
        manager.addProduct(ticketFive);

        Ticket[] expected = {ticketThree, ticketFive};
        Ticket[] actual = manager.searchBy("CHI", "MOW");

        assertArrayEquals(expected, actual);
    }

    @Test
    void sortByPrice() {
        TicketManager manager = new TicketManager();
        TicketRepository repository = new TicketRepository();

        repository.saveTicket(ticketOne);
        repository.saveTicket(ticketTwo);
        repository.saveTicket(ticketThree);
        repository.saveTicket(ticketFour);
        repository.saveTicket(ticketFive);

        Ticket[] expected = {ticketFive, ticketOne, ticketTwo, ticketFour, ticketThree};
        Ticket[] actual = repository.findAll();

        Arrays.sort(actual);

        assertArrayEquals(expected, actual);
    }
}