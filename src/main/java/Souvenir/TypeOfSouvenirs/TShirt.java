package Souvenir.TypeOfSouvenirs;

import Souvenir.Souvenir;
import Manufacturer.Manufacturer;

import java.io.Serializable;
import java.time.LocalDate;

public class TShirt extends Souvenir implements Serializable {
    public TShirt(String name, LocalDate dateOfRelease, double price, Manufacturer manufacturer) {
        super(name, dateOfRelease, price, manufacturer);
    }
}