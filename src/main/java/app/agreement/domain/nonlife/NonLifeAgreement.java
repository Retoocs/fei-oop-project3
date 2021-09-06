package app.agreement.domain.nonlife;

import app.agreement.domain.AgreementAdapter;
import app.agreement.domain.nonlife.classifiers.TypeOfProperty;
import app.utils.domain.Address;
import app.utils.domain.Season;

public abstract class NonLifeAgreement extends AgreementAdapter {

    private TypeOfProperty typeOfProperty;
    private Address addressOfProperty;
    private double valueOfProperty;

    public NonLifeAgreement(long insurerID, Season season, double insuranceIndemnity, double monthlyPayment, TypeOfProperty typeOfProperty, Address addressOfProperty, double valueOfProperty) {
        super(insurerID, season, insuranceIndemnity, monthlyPayment);
        setTypeOfProperty(typeOfProperty);
        setAddressOfProperty(addressOfProperty);
        setValueOfProperty(valueOfProperty);
    }

    @Override
    public TypeOfProperty getTypeOfProperty() {
        return typeOfProperty;
    }

    @Override
    public void setTypeOfProperty(TypeOfProperty typeOfProperty) {
        if(typeOfProperty == null){
            throw new IllegalArgumentException("'typeOfProperty' is null.");
        }
        this.typeOfProperty = typeOfProperty;
    }

    @Override
    public Address getAddressOfProperty() {
        return addressOfProperty;
    }

    @Override
    public void setAddressOfProperty(Address addressOfProperty) {
        if(addressOfProperty == null){
            throw new IllegalArgumentException("'addressOfProperty' is null.");
        }
        this.addressOfProperty = addressOfProperty;
    }

    @Override
    public double getValueOfProperty() {
        return valueOfProperty;
    }

    @Override
    public void setValueOfProperty(double valueOfProperty) {
        if(valueOfProperty < 0){
            throw new IllegalArgumentException("'valueOfProperty' is negative.");
        }
        this.valueOfProperty = valueOfProperty;
    }
}
