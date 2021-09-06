package app.agreement.web.resource.life;

import app.agreement.domain.AgreementListener;
import app.agreement.web.resource.AgreementResource;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
public abstract class LifeResource extends AgreementResource {
    @Min(0)
    protected long insuredID;

    public LifeResource(AgreementListener agreement){
        super(agreement);
        this.insuredID = agreement.getInsuredID();
    }
}
