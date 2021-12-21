package tdd;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CinemaTest {

    @Ignore
    @Test
    public void whenBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2021, Calendar.DECEMBER, 21, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Ignore
    @Test
    public void whenFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(new Session3D())));
    }

    @Ignore
    @Test
    public void whenSessionEnding() {
        Session session = new Session3D();
        assertThat(session, is(new Session3D()));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenDateNotExist() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(20212, Calendar.DECEMBER, 42, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenWrongRowAndColumn() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2021, Calendar.DECEMBER, 21, 23, 00);
        Ticket ticket = cinema.buy(account, 505, -3, date);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenTicketUse() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2021, Calendar.DECEMBER, 21, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        Ticket ticketTwo = cinema.buy(account, 1, 1, date);
    }
}