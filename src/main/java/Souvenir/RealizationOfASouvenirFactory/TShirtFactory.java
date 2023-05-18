package Souvenir.RealizationOfASouvenirFactory;

import Manufacturer.Manufacturer;
import Souvenir.Souvenir;
import Souvenir.TypeOfSouvenirs.TShirt;

import java.time.LocalDate;

public class TShirtFactory implements SouvenirFactory{
    @Override
    public Souvenir createSouvenir(String name, LocalDate dateOfRelease, double price, Manufacturer manufacturer) {
        return new TShirt(name, dateOfRelease, price, manufacturer);
    }
}
