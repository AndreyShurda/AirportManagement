package com.andrey.main.bl.operations;

import com.andrey.main.dl.models.Ticket;

import java.util.List;
import java.util.function.Predicate;

public interface SearchTicket {
    List<Ticket> byPrice(Predicate<Ticket> price);
}
