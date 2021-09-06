package app.agreement.web.resource.life;

import app.agreement.domain.AgreementListener;
import app.agreement.domain.life.classifiers.Territory;
import app.utils.domain.Season;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AccidentResource extends LifeResource {
    @Min(0)
    protected double permanentConsequences;
    @Min(0)
    protected double deathByInjury;
    @Min(0)
    protected double dailyCompensation;
    @NotNull
    protected Territory territory;

    public AccidentResource(AgreementListener agreement){
        super(agreement);
        this.permanentConsequences = agreement.getPermanentConsequences();
        this.deathByInjury = agreement.getDeathByInjury();
        this.dailyCompensation = agreement.getDailyCompensation();
        this.territory = agreement.getTerritory();
    }


    @Override
    public void toAgreement(AgreementListener agreement) {
        agreement.setInsurerID(this.insurerID);
        agreement.setSeason(new Season(this.beginningDate, this.endingDate));
        agreement.setInsuranceIndemnity(this.insuranceIndemnity);
        agreement.setMonthlyPayment(this.monthlyPayment);
        agreement.setInsuredID(this.insuredID);
        agreement.setPermanentConsequences(this.permanentConsequences);
        agreement.setDeathByInjury(this.deathByInjury);
        agreement.setDailyCompensation(this.dailyCompensation);
        agreement.setTerritory(this.territory);
    }
}
