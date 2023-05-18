package Souvenir;

import Manufacturer.Manufacturer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public abstract class Souvenir implements Serializable {
    protected String name;
    protected LocalDate dateOfRelease;
    protected double price;
    protected Manufacturer manufacturer;

    public  Souvenir(String name, LocalDate dateOfRelease, double price, Manufacturer manufacturer) {
        this.name = name;
        this.dateOfRelease = dateOfRelease;
        this.price = price;
        this.manufacturer = manufacturer;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(LocalDate dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Souvenir{" +
                "name='" + name + '\'' +
                ", dateOfRelease=" + dateOfRelease +
                ", price=" + price +
                ", manufacturer=" + manufacturer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Souvenir souvenir = (Souvenir) o;
        return Double.compare(souvenir.price, price) == 0 && Objects.equals(name, souvenir.name) && Objects.equals(dateOfRelease, souvenir.dateOfRelease) && Objects.equals(manufacturer, souvenir.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateOfRelease, price, manufacturer);
    }
}
