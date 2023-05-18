package Souvenir.RealizationOfASouvenirFactory;

import Manufacturer.Manufacturer;
import Souvenir.Souvenir;

import java.time.LocalDate;

public interface SouvenirFactory {
    Souvenir createSouvenir(String name, LocalDate dateOfRelease, double price, Manufacturer manufacturer);
}
