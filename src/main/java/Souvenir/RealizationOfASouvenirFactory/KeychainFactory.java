package Souvenir.RealizationOfASouvenirFactory;

import Manufacturer.Manufacturer;
import Souvenir.Souvenir;
import Souvenir.TypeOfSouvenirs.Keychain;

import java.time.LocalDate;

public class KeychainFactory implements SouvenirFactory {
    @Override
    public Souvenir createSouvenir(String name, LocalDate dateOfRelease, double price, Manufacturer manufacturer) {
        return new Keychain(name, dateOfRelease, price, manufacturer);
    }
}
