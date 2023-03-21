package cinema.businessLayer;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Ticket {
    @JsonProperty("token")
    String token;
    @JsonProperty("ticket")
    SeatDTO seatDTO;

    Ticket(SeatDTO seatDTO) {
        this.token = UUID.randomUUID().toString();
        this.seatDTO = seatDTO;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String uuid) {
        this.token = uuid;
    }

    public SeatDTO getSeatDTO() {
        return seatDTO;
    }

    public void setSeatDTO(SeatDTO seatDTO) {
        this.seatDTO = seatDTO;
    }
}
