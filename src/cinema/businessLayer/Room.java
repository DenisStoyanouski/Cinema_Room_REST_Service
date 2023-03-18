package cinema.businessLayer;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class Room {
    @JsonProperty("total_rows")
    private int totalRows = 9;

    @JsonProperty("total_columns")
    private int totalColumns = 9;

    @JsonProperty("available_seats")
    private List<Seat> availableSeats = new ArrayList<>();

    public Room() {
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
        return availableSeats.stream().filter(seat -> !seat.isTaken()).collect(Collectors.toList());
    }

    public Seat getSeatByRowAndColumn(int row, int column) {
        return availableSeats.stream()
                .filter(seat -> seat.getRow() == row && seat.getColumn() == column)
                .findFirst().get();
    }

    public void setAvailableSeats(List<Seat> availableSeats) {
        this.availableSeats = availableSeats;
    }
}
