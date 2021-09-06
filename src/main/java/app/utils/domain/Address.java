package app.utils.domain;

import lombok.Data;

@Data
public class Address {
    private String postalCode;
    private String city;
    private String street;
    private String houseNumber;

    public Address(String postalCode, String city, String street, String houseNumber){
        setPostalCode(postalCode);
        setCity(city);
        setStreet(street);
        setHouseNumber(houseNumber);
    }

    public void setPostalCode(String postalCode) {
        if(postalCode == null){
            throw new IllegalArgumentException("'postalCode' is null.");
        }
        this.postalCode = postalCode;
    }

    public void setCity(String city) {
        if(city == null){
            throw new IllegalArgumentException("'city' is null.");
        }
        this.city = city;
    }

    public void setStreet(String street) {
        if(street == null){
            throw new IllegalArgumentException("'street' is null.");
        }
        this.street = street;
    }

    public void setHouseNumber(String houseNumber) {
        if(houseNumber == null){
            throw new IllegalArgumentException("'houseNumber' is null.");
        }
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString(){
        return this.postalCode + ' ' + this.city + ", " + this.street + ' ' + this.houseNumber;
    }
}
