package Souvenir.RealizationOfASouvenirFactory;

import Manufacturer.Manufacturer;
import Souvenir.Souvenir;
import Souvenir.TypeOfSouvenirs.Cap;

import java.time.LocalDate;

public class CapFactory implements SouvenirFactory{
    @Override
    public Souvenir createSouvenir(String name, LocalDate dateOfRelease, double price, Manufacturer manufacturer) {
        return new Cap(name, dateOfRelease, price, manufacturer);
    }
}

