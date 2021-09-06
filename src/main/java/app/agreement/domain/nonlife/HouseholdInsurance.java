package app.agreement.domain.nonlife;

import app.agreement.domain.nonlife.classifiers.TypeOfProperty;
import app.utils.domain.Address;
import app.utils.domain.Season;

public class HouseholdInsurance extends NonLifeAgreement {
    private double equipmentValue;

    public HouseholdInsurance(long insurerID,
                              Season season,
                              double insuranceIndemnity,
                              double monthlyPayment,
                              TypeOfProperty typeOfProperty,
                              Address addressOfProperty,
                              double valueOfProperty,
                              double equipmentValue ){
        super(insurerID, season, insuranceIndemnity, monthlyPayment, typeOfProperty, addressOfProperty, valueOfProperty);
        setEquipmentValue(equipmentValue);
    }

    @Override
    public double getEquipmentValue() {
        return equipmentValue;
    }

    @Override
    public void setEquipmentValue(double equipmentValue) {
        if(equipmentValue < 0){
            throw new IllegalArgumentException("'equipmentValue' is negative.");
        }
        this.equipmentValue = equipmentValue;
    }

    @Override
    public String getClassName(){
        return "household";
    }
}
