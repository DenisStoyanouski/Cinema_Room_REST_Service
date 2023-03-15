package cinema.businessLayer;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class Room {
    private int totalRows = 9;
    private int totalColumns = 9;
    private List<Seat> availableSeats = new ArrayList<>();

    public Room() {
        /*this.totalRows = totalRows;
        this.totalColumns = totalColumns;*/
        for (int i = 1; i <= totalRows; i++) {
            for(int j = 1; j <= totalColumns; j++) {
                availableSeats.add(new Seat(i, j));
            }
        };
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

    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(List<Seat> availableSeats) {
        this.availableSeats = availableSeats;
    }
}