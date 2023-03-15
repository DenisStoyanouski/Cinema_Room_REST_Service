package cinema.presentation;

import cinema.businessLayer.Room;
import cinema.businessLayer.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomController {
    @Autowired
    RoomService roomService;

    @GetMapping("/seats")
    public Room getRoom() {
        return roomService.getRoom();
    }
}
