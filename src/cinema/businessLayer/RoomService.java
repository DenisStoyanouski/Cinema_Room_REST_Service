package cinema.businessLayer;

;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


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

    public void takeSeat(int row, int column) {
        room.getSeatByRowAndColumn(row, column).setTaken();
        room.updateAvailableSeats();
    }

    public boolean isSeatTaken(int row, int column) {
        return room.getSeatByRowAndColumn(row, column).isTaken();
    }

    public SeatDTO getSeat(int row, int column) {
        return room.getAvailableSeatDTO(row, column);
    }


}
