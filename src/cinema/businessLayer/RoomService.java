package cinema.businessLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
