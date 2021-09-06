package app.agreement.web.resource;

import app.agreement.domain.AgreementListener;
import app.agreement.domain.life.classifiers.PurposeOfTrip;
import app.agreement.domain.life.classifiers.Territory;
import app.agreement.domain.nonlife.classifiers.TypeOfProperty;

import java.time.LocalDate;

public interface AgreementResourceListener {
    /** Every agreement resource has */
    long getInsurerID();
    void setInsurerID(long insurerID);
    LocalDate getBeginningDate();
    void setBeginningDate(LocalDate beginningDate);
    LocalDate getEndingDate();
    void setEndingDate(LocalDate endingDate);
    double getInsuranceIndemnity();
    void setInsuranceIndemnity(double insuranceIndemnity);
    double getMonthlyPayment();
    void setMonthlyPayment(double monthlyPayment);
    void toAgreement(AgreementListener agreement);


    /** Every life-agreement resource has */
    long getInsuredID();
    void setInsuredID(long insuredID);

    /** Life-agreement: AccidentResource has */
    double getPermanentConsequences();
    void setPermanentConsequences(double permanentConsequences);
    double getDeathByInjury();
    void setDeathByInjury(double deathByInjury);
    double getDailyCompensation();
    void setDailyCompensation(double dailyCompensation);
    Territory getTerritory();
    void setTerritory(Territory territory);

    /** Life-agreement: TravelResource has */
    boolean getIsInEurope();
    void setIsInEurope(boolean inEurope);
    PurposeOfTrip getPurposeOfTrip();
    void setPurposeOfTrip(PurposeOfTrip purposeOfTrip);

    /** Every NonLife-agreement resource has */
    TypeOfProperty getTypeOfProperty();
    void setTypeOfProperty(TypeOfProperty typeOfProperty);
    String getPropertyPostalCode();
    void setPropertyPostalCode(String propertyPostalCode);
    String getPropertyCity();
    void setPropertyCity(String propertyCity);
    String getPropertyStreet();
    void setPropertyStreet(String propertyStreet);
    String getPropertyHouseNumber();
    void setPropertyHouseNumber(String propertyHouseNumber);
    double getValueOfProperty();
    void setValueOfProperty(double valueOfProperty);

    /** NonLife-agreement: HouseholdResource has */
    double getEquipmentValue();
    void setEquipmentValue(double equipmentValue);

    /** NonLife-agreement: HouseResource has */
    boolean getIsGarageInsured();
    void setIsGarageInsured(boolean garageInsured);
}
