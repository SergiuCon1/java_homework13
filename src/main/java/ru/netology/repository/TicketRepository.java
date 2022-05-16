package ru.netology.repository;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.domain.Ticket;

@NoArgsConstructor
@Data
public class TicketRepository {
    Ticket[] tickets = new Ticket[0];

    public void saveTicket(Ticket ticket) {
        int length = tickets.length + 1;
        Ticket[] tmp = new Ticket[length];
        System.arraycopy(tickets, 0, tmp, 0, tickets.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = ticket;
        tickets = tmp;
    }

    public Ticket[] findAll() {
        return tickets;
    }

    public void removeById(int id) {
        int length = tickets.length - 1;
        Ticket[] exist = new Ticket[length];
        int index = 0;
        for (Ticket product : tickets) {
            if (product.getId() != id) {
                exist[index] = product;
                index++;
            }
            tickets = exist;
        }
    }
}
