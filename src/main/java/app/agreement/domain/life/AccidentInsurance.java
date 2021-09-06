package app.agreement.domain.life;

import app.agreement.domain.life.classifiers.*;
import app.utils.domain.Season;

public class AccidentInsurance extends LifeAgreement {
    private double permanentConsequences;
    private double deathByInjury;
    private double dailyCompensation;
    private Territory territory;

    public AccidentInsurance(long insurerID,
                             Season season,
                             double insuranceIndemnity,
                             double monthlyPayment,
                             long insuredID,
                             double permanentConsequences,
                             double deathByInjury,
                             double dailyCompensation,
                             Territory territory ){
        super(insurerID, season, insuranceIndemnity, monthlyPayment, insuredID);
        setPermanentConsequences(permanentConsequences);
        setDeathByInjury(deathByInjury);
        setDailyCompensation(dailyCompensation);
        setTerritory(territory);
    }

    @Override
    public double getPermanentConsequences() {
        return permanentConsequences;
    }

    @Override
    public void setPermanentConsequences(double permanentConsequences) {
        if(permanentConsequences < 0){
            throw new IllegalArgumentException("'permanentConsequences' is negative.");
        }
        this.permanentConsequences = permanentConsequences;
    }

    @Override
    public double getDeathByInjury() {
        return deathByInjury;
    }

    @Override
    public void setDeathByInjury(double deathByInjury) {
        if(deathByInjury < 0){
            throw new IllegalArgumentException("'deathByInjury' is negative.");
        }
        this.deathByInjury = deathByInjury;
    }

    @Override
    public double getDailyCompensation() {
        return dailyCompensation;
    }

    @Override
    public void setDailyCompensation(double dailyCompensation) {
        if(dailyCompensation < 0){
            throw new IllegalArgumentException("'dailyCompensation' is negative.");
        }
        this.dailyCompensation = dailyCompensation;
    }

    @Override
    public Territory getTerritory() {
        return territory;
    }

    @Override
    public void setTerritory(Territory territory) {
        if(territory == null){
            throw new IllegalArgumentException("'territory' is null.");
        }
        this.territory = territory;
    }

    @Override
    public String getClassName(){
        return "accident";
    }
}
