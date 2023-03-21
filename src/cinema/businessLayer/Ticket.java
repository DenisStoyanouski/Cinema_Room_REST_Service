package cinema.businessLayer;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Ticket {
    @JsonProperty("token")
    String uuid;
    @JsonProperty("ticket")
    SeatDTO seatDTO;

    Ticket(SeatDTO seatDTO) {
        this.uuid = UUID.randomUUID().toString();
        this.seatDTO = seatDTO;
    }


}
