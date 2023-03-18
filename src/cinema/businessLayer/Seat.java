package cinema.businessLayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

public class Seat {
    private int row;
    private int column;
    private final int price;
    private boolean isTaken;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = row <= 4 ? 10 : 8;
        this.isTaken = false;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken() {
        isTaken = true;
    }

    @Override
    public String toString() {
        return "{" +
                "row:" + row +
                ", column:" + column +
                ", price:" + price +
                '}';
    }


}
