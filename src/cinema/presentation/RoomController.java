package cinema.presentation;

import cinema.businessLayer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.NoSuchElementException;

@RestController
public class RoomController {
    @Autowired
    RoomService roomService;

    @GetMapping("/seats")
    public Room getRoom() {
        return roomService.getRoom();
    }

    @PostMapping(path = "/purchase", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity makePurchase(@RequestBody Seat requestSeat) {
        try {
            int row = requestSeat.getRow();
            int column = requestSeat.getColumn();
            if (row < 1 || row > roomService.getRows() || column < 1 || column > roomService.getColumns()) {
                return ResponseEntity.badRequest().body(new Message("The number of a row or a column is out of bounds!"));
            }
            SeatDTO seatDTO = roomService.getSeat(row, column);
            roomService.takeSeat(requestSeat.getRow(), requestSeat.getColumn());
            return ResponseEntity.ok(roomService.printTicket(seatDTO));
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(new Message("The ticket has been already purchased!"));
        }
    }

    @PostMapping(path = "/return",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity returnTicket(@RequestBody Map<String, String> payload) {
        Map<String, Ticket> upload = Map.of("returned_ticket", roomService.getTicketByToken(payload.get("token")));
        return ResponseEntity.ok(upload);
    }


}

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
class TicketPurchased extends RuntimeException {
    public TicketPurchased(String cause) {
        super(cause);
    }
}

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
class NumberOutOfBounds extends RuntimeException {
    public NumberOutOfBounds(String cause) {
        super(cause);
    }
}