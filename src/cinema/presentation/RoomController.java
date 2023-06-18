package cinema.presentation;

import cinema.businessLayer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
        try {
            SeatDTO seatDTO = roomService.getTicketByToken(payload.get("token")).getSeatDTO();
            roomService.returnTicket(payload.get("token"));
            Map<String, SeatDTO> upload = Map.of("returned_ticket", seatDTO);
            return ResponseEntity.ok(upload);
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(new Message("Wrong token!"));
        }
    }

    @PostMapping(path = "/stats",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getStats(@RequestParam(required = false) String password) {
        if ("super_secret".equals(password)) {
            LinkedHashMap<String, Integer> stats = new LinkedHashMap<>();
            stats.put("current_income", roomService.getCurrentIncome());
            stats.put("number_of_available_seats", roomService.getAvailableSeats().size());
            stats.put("number_of_purchased_tickets", roomService.getNumberOfPurchasedTickets());
            return ResponseEntity.ok(stats);
        }
        return ResponseEntity.status(401).body(new Message("The password is wrong!"));
    }
}


