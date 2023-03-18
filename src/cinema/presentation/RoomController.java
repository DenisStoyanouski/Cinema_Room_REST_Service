package cinema.presentation;

import cinema.businessLayer.Message;
import cinema.businessLayer.Room;
import cinema.businessLayer.RoomService;
import cinema.businessLayer.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
public class RoomController {
    @Autowired
    RoomService roomService;

    @GetMapping("/seats")
    public Room getRoom() {
        return roomService.getRoom();
    }

    @PostMapping(value = "/purchase", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object makePurchase(int row, int column) {
        try {
            Seat seat = roomService.getRoom().getSeatByRowAndColumn(row, column);
            seat.setTaken();
            return seat;
        } catch (IndexOutOfBoundsException e) {
            return new Message("error", "The number of a row or a column is out of bounds!");
        } catch (NoSuchElementException e) {
            return new Message("error", "The ticket has been already purchased!");
        }
    }
}
