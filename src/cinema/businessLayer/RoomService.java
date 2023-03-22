package cinema.businessLayer;

;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoomService {

    private final Room room;

    @Autowired
    public RoomService(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public int getRows() {
        return room.getTotalRows();
    }

    public int getColumns() {
        return room.getTotalColumns();
    }

    public List<SeatDTO> getAvailableSeats() {
        return room.getAvailableSeats();
    }

    public int getCurrentIncome() {
        return room.getSeats().stream().filter(Seat::isTaken).mapToInt(Seat::getPrice).sum();
    }

    public int getNumberOfPurchasedTickets() {
        return room.getSeats().size() - room.getAvailableSeats().size();
    }

    public void takeSeat(int row, int column) {
        room.getSeatByRowAndColumn(row, column).setTaken();
        room.updateAvailableSeats();
    }

    public Ticket printTicket(SeatDTO seatDTO) {
        return room.createTicket(seatDTO);
    }

    public Ticket getTicketByToken(String token) {
        return room.getTicketByToken(token);
    }

    public boolean isSeatTaken(int row, int column) {
        return room.getSeatByRowAndColumn(row, column).isTaken();
    }

    public SeatDTO getSeat(int row, int column) {
        return room.getAvailableSeatDTO(row, column);
    }


}
