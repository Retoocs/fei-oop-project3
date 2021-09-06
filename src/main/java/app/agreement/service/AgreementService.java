package app.agreement.service;

import app.agreement.domain.AgreementListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class AgreementService {
    private Map<Long, AgreementListener> agreements;

    public AgreementService(){
        this.agreements = new HashMap<>();
    }

    public Map<Long, AgreementListener> getAgreements() {
        return agreements;
    }

    public void setAgreements(Map<Long, AgreementListener> agreements) {
        if(agreements == null){
            throw new IllegalArgumentException("'agreements' is null.");
        }
        this.agreements = agreements;
    }

    public void addAgreement(AgreementListener agreement){
        if(agreement == null){
            throw new IllegalArgumentException("'app.agreement' is null.");
        }
        this.agreements.put(agreement.getId(), agreement);
    }

    public AgreementListener getAgreement(long agreementID){
        if(agreements.containsKey(agreementID)){
            return agreements.get(agreementID);
        }
        throw new IllegalArgumentException("Agreement with ID: " + agreementID + " does not exist.");
    }

    public Set<AgreementListener> getUserAgreements(long userID){
        Set<AgreementListener> agreements = new HashSet<>();

        for(AgreementListener agreement : this.agreements.values()){
            if(agreement.getInsurerID() == userID){
                agreements.add(agreement);
            }
        }
        return agreements;
    }

    public void removeAgreement(long agreementID){
        if(agreements.remove(agreementID) == null){
            throw new IllegalArgumentException("Agreement with ID: " + agreementID + " does not exist.");
        }
    }


}
