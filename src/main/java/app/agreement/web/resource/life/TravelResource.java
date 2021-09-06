package app.agreement.web.resource.life;

import app.agreement.domain.AgreementListener;
import app.agreement.domain.life.classifiers.PurposeOfTrip;

import app.utils.domain.Season;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
public class TravelResource extends LifeResource {
    private boolean isInEurope;
    @Getter @Setter
    @NotNull
    private PurposeOfTrip purposeOfTrip;

    public TravelResource(AgreementListener agreement){
        super(agreement);
        this.isInEurope = agreement.isInEurope();
        this.purposeOfTrip = agreement.getPurposeOfTrip();
    }

    @Override
    public boolean getIsInEurope(){
        return isInEurope;
    }

    @Override
    public void setIsInEurope(boolean isInEurope){
        this.isInEurope = isInEurope;
    }

    @Override
    public void toAgreement(AgreementListener agreement) {
        agreement.setInsurerID(this.insurerID);
        agreement.setSeason(new Season(this.beginningDate, this.endingDate));
        agreement.setInsuranceIndemnity(this.insuranceIndemnity);
        agreement.setMonthlyPayment(this.monthlyPayment);
        agreement.setInsuredID(this.insuredID);
        agreement.setInEurope(this.isInEurope);
        agreement.setPurposeOfTrip(this.purposeOfTrip);
    }
}
