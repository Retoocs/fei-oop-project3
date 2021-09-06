package app.agreement.web.resource;

import app.agreement.domain.AgreementListener;
import app.agreement.domain.life.classifiers.PurposeOfTrip;
import app.agreement.domain.life.classifiers.Territory;
import app.agreement.domain.nonlife.classifiers.TypeOfProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Data
@NoArgsConstructor
public abstract class AgreementResource implements AgreementResourceListener {
    @Min(0)
    protected long insurerID;
    @NotNull
    protected LocalDate beginningDate;
    @NotNull
    protected LocalDate endingDate;
    @Min(0)
    protected double insuranceIndemnity;
    @Min(0)
    protected double monthlyPayment;

    public AgreementResource(AgreementListener agreement){
        this.insurerID = agreement.getInsurerID();
        this.beginningDate = agreement.getSeason().getBeginningDate();
        this.endingDate = agreement.getSeason().getEndingDate();
        this.insuranceIndemnity = agreement.getInsuranceIndemnity();
        this.monthlyPayment = agreement.getMonthlyPayment();
    }

    @Override
    public long getInsuredID() {
        return -1;
    }

    @Override
    public void setInsuredID(long insuredID) {
    }

    @Override
    public double getPermanentConsequences() {
        return -1;
    }

    @Override
    public void setPermanentConsequences(double permanentConsequences) {
    }

    @Override
    public double getDeathByInjury() {
        return -1;
    }

    @Override
    public void setDeathByInjury(double deathByInjury) {
    }

    @Override
    public double getDailyCompensation() {
        return -1;
    }

    @Override
    public void setDailyCompensation(double dailyCompensation) {
    }

    @Override
    public Territory getTerritory() {
        return null;
    }

    @Override
    public void setTerritory(Territory territory) {
    }

    @Override
    public boolean getIsInEurope() {
        return false;
    }

    @Override
    public void setIsInEurope(boolean inEurope) {
    }

    @Override
    public PurposeOfTrip getPurposeOfTrip() {
        return null;
    }

    @Override
    public void setPurposeOfTrip(PurposeOfTrip purposeOfTrip) {
    }

    @Override
    public TypeOfProperty getTypeOfProperty() {
        return null;
    }

    @Override
    public void setTypeOfProperty(TypeOfProperty typeOfProperty) {
    }

    @Override
    public String getPropertyPostalCode() {
        return null;
    }

    @Override
    public void setPropertyPostalCode(String propertyPostalCode) {
    }

    @Override
    public String getPropertyCity() {
        return null;
    }

    @Override
    public void setPropertyCity(String propertyCity) {
    }

    @Override
    public String getPropertyStreet() {
        return null;
    }

    @Override
    public void setPropertyStreet(String propertyStreet) {
    }

    @Override
    public String getPropertyHouseNumber() {
        return null;
    }

    @Override
    public void setPropertyHouseNumber(String propertyHouseNumber) {
    }

    @Override
    public double getValueOfProperty() {
        return -1;
    }

    @Override
    public void setValueOfProperty(double valueOfProperty) {
    }

    @Override
    public double getEquipmentValue() {
        return -1;
    }

    @Override
    public void setEquipmentValue(double equipmentValue) {
    }

    @Override
    public boolean getIsGarageInsured() {
        return false;
    }

    @Override
    public void setIsGarageInsured(boolean garageInsured) {
    }
}
