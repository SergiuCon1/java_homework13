package ru.netology.manager;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

@NoArgsConstructor
@Data
public class TicketManager {
    private TicketRepository repository = new TicketRepository();

    Ticket[] tickets = new Ticket[0];

    public void addProduct(Ticket ticket) {
        repository.saveTicket(ticket);
    }

    public Ticket[] searchBy(String from, String to) {
        int length = tickets.length + 1;
        Ticket[] result = new Ticket[length];// тут будем хранить подошедшие запросу продукты
        for (Ticket ticket : repository.findAll()) {
            if (matchesFrom(ticket, from)) {
                if (matchesTo(ticket, to)) {
                    if (result.length < length) {
                        int lengthMore = tickets.length + 1;
                        Ticket[] moreThanOne = new Ticket[lengthMore];
                        System.arraycopy(tickets, 0, moreThanOne, 0, tickets.length);
                        int lastIndexMore = moreThanOne.length - 1;
                        moreThanOne[lastIndexMore] = ticket;
                        tickets = moreThanOne;
                        length++;
                        lengthMore++;
                    }
                    if (result.length == length) {
                        int lastIndex = result.length - 1;
                        result[lastIndex] = ticket; // "добавляем в конец" массива result билет
                        length++;
                        tickets = result;
                    }
                }
            }
        }
        return tickets;
    }

    // метод определения соответствия билета запросу airportFrom
    public boolean matchesFrom(Ticket ticket, String from) {
        if (ticket.getAirportFrom().contains(from)) {
            return true;
        } else {
            return false;
        }
    }

    // метод определения соответствия билета запросу airportTo
    public boolean matchesTo(Ticket ticket, String to) {
        if (ticket.getAirportTo().contains(to)) {
            return true;
        } else {
            return false;
        }
    }
}
