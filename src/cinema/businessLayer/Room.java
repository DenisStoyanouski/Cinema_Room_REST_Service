package cinema.businessLayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Component
public class Room {
    @JsonProperty("total_rows")
    private int totalRows = 9;

    @JsonProperty("total_columns")
    private int totalColumns = 9;

    @JsonIgnore
    private List<Seat> seats = new ArrayList<>();
    @JsonProperty("available_seats")
    private List<SeatDTO> availableSeats;

    @JsonIgnore
    private final List<Ticket> tickets = new ArrayList<>();


    public Room() {
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 1; j <= totalColumns; j++) {
                seats.add(new Seat(i, j));
            }
        }
        availableSeats = seats.stream()
                .filter(seat -> !seat.isTaken())
                .map(this::convertUserToDTO)
                .collect(Collectors.toList());

    }

    public SeatDTO convertUserToDTO(Seat seat) {
        SeatDTO dto = new SeatDTO(seat.getRow(), seat.getColumn(), seat.getPrice());
        return dto;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }

    public List<Seat> getSeats() {
        return seats;
    }


    public Seat getSeatByRowAndColumn(int row, int column) {
        return seats.stream()
                .filter(seat -> seat.getRow() == row && seat.getColumn() == column)
                .findFirst().get();
    }

    public List<SeatDTO> getAvailableSeats() {
        return availableSeats;
    }

    public void updateAvailableSeats() {
        availableSeats = seats.stream()
                .filter(seat -> !seat.isTaken())
                .map(this::convertUserToDTO)
                .collect(Collectors.toList());
    }

    public SeatDTO getAvailableSeatDTO(int row, int column) {
        return availableSeats.stream()
                .filter(seat -> seat.getRow() == row && seat.getColumn() == column)
                .findFirst().orElseThrow();
    }

    public Ticket createTicket(SeatDTO seatDTO) {
        Ticket ticket = new Ticket(seatDTO);
        tickets.add(ticket);
        return ticket;
    }

    public Ticket getTicketByToken(String token) {
        return tickets.stream().filter(ticket -> Objects.equals(token, ticket.getToken())).findFirst().orElseThrow();
    }

}
