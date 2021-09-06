package app.agreement.domain;

import app.agreement.domain.life.classifiers.PurposeOfTrip;
import app.agreement.domain.life.classifiers.Territory;
import app.agreement.domain.nonlife.classifiers.TypeOfProperty;
import app.utils.domain.Address;
import app.utils.domain.Season;

import java.time.LocalDate;

public interface AgreementListener{

    /** Every app.agreement has */
    long getId();
    LocalDate getDateOfCreation();
    long getInsurerID();
    void setInsurerID(long insurerID);
    Season getSeason();
    void setSeason(Season season);
    double getInsuranceIndemnity();
    void setInsuranceIndemnity(double insuranceIndemnity);
    double getMonthlyPayment();
    void setMonthlyPayment(double monthlyPayment);
    String getClassName();

    /** Every life-app.agreement has */
    long getInsuredID();
    void setInsuredID(long insuredID);

    /** Life-app.agreement: AccidentInsurance has */
    double getPermanentConsequences();
    void setPermanentConsequences(double permanentConsequences);
    double getDeathByInjury();
    void setDeathByInjury(double deathByInjury);
    double getDailyCompensation();
    void setDailyCompensation(double dailyCompensation);
    Territory getTerritory();
    void setTerritory(Territory territory);

    /** Life-app.agreement: TravelInsurance has */
    boolean isInEurope();
    void setInEurope(boolean inEurope);
    PurposeOfTrip getPurposeOfTrip();
    void setPurposeOfTrip(PurposeOfTrip purposeOfTrip);

    /** Every NonLife-app.agreement has */
    TypeOfProperty getTypeOfProperty();
    void setTypeOfProperty(TypeOfProperty typeOfProperty);
    Address getAddressOfProperty();
    void setAddressOfProperty(Address addressOfProperty);
    double getValueOfProperty();
    void setValueOfProperty(double valueOfProperty);

    /** NonLife-app.agreement: HouseholdInsurance has */
    double getEquipmentValue();
    void setEquipmentValue(double equipmentValue);

    /** NonLife-app.agreement: HouseInsurance has */
    boolean isGarageInsured();
    void setGarageInsured(boolean garageInsured);

}
