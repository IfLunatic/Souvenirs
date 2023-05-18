package Souvenir.RealizationOfASouvenirFactory;

import Manufacturer.Manufacturer;
import Souvenir.Souvenir;
import Souvenir.TypeOfSouvenirs.Cup;

import java.time.LocalDate;

public class CupFactory implements SouvenirFactory{
    @Override
    public Souvenir createSouvenir(String name, LocalDate dateOfRelease, double price, Manufacturer manufacturer) {
        return new Cup(name, dateOfRelease, price, manufacturer);
    }
}
