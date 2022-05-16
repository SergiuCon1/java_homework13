package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ticket implements Comparable<Ticket> {
    private int id;
    private int priceTicket;
    private String airportFrom;
    private String airportTo;
    private int wayTime;

    @Override
    public int compareTo(Ticket o) {
        return this.priceTicket - o.priceTicket;
    }
}
