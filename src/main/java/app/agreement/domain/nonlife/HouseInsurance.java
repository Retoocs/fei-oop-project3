package app.agreement.domain.nonlife;

import app.agreement.domain.nonlife.classifiers.TypeOfProperty;
import app.utils.domain.Address;
import app.utils.domain.Season;

public class HouseInsurance extends NonLifeAgreement{
    private boolean isGarageInsured;

    public HouseInsurance(long insurerID,
                          Season season,
                          double insuranceIndemnity,
                          double monthlyPayment,
                          TypeOfProperty typeOfProperty,
                          Address addressOfProperty,
                          double valueOfProperty,
                          boolean isGarageInsured){
        super(insurerID, season, insuranceIndemnity, monthlyPayment, typeOfProperty, addressOfProperty, valueOfProperty);
        setGarageInsured(isGarageInsured);
    }

    @Override
    public boolean isGarageInsured() {
        return isGarageInsured;
    }

    @Override
    public void setGarageInsured(boolean garageInsured) {
        isGarageInsured = garageInsured;
    }

    @Override
    public String getClassName(){
        return "house";
    }
}
