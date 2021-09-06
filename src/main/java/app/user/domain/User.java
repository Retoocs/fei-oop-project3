package app.user.domain;

import app.utils.domain.UserIdGenerator;
import app.utils.domain.Address;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class User{
    private final long id;
    private String firstname;
    private String lastname;
    private String birthNumber;
    private String email;
    private Address permanentAddress;
    private boolean isCorrespondence;
    private Address correspondenceAddress;
    private Set<Long> agreementIds;

    public User(String firstname, String lastname, String birthNumber, String email, Address permanentAddress, boolean isCorrespondence, Address correspondenceAddress) {
        this.id = UserIdGenerator.newId();
        setFirstname(firstname);
        setLastname(lastname);
        setBirthNumber(birthNumber);
        setEmail(email);
        setPermanentAddress(permanentAddress);
        setCorrespondence(isCorrespondence);
        setCorrespondenceAddress(correspondenceAddress);
        this.agreementIds = new HashSet<>();
    }
    public User(String firstname, String lastname, String birthNumber, String email, Address permanentAddress, boolean isCorrespondence) {
        this.id = UserIdGenerator.newId();
        setFirstname(firstname);
        setLastname(lastname);
        setBirthNumber(birthNumber);
        setEmail(email);
        setPermanentAddress(permanentAddress);
        setCorrespondence(isCorrespondence);
        setCorrespondenceAddress(permanentAddress);
        this.agreementIds = new HashSet<>();
    }

    public void setFirstname(String firstname) {
        if(firstname == null){
            throw new IllegalArgumentException("'firstname' is null.");
        }
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        if(lastname == null){
            throw new IllegalArgumentException("'lastname' is null.");
        }
        this.lastname = lastname;
    }

    public void setBirthNumber(String birthNumber) {
        if(birthNumber == null){
            throw new IllegalArgumentException("'birthNumber' is null.");
        }
        this.birthNumber = birthNumber;
    }

    public void setEmail(String email) {
        if(email == null){
            throw new IllegalArgumentException("'email' is null.");
        }
        this.email = email;
    }

    public void setPermanentAddress(Address permanentAddress) {
        if(permanentAddress == null){
            throw new IllegalArgumentException("'permanentAddress' is null.");
        }
        this.permanentAddress = permanentAddress;
    }

    public boolean getIsCorrespondence(){
        return isCorrespondence;
    }

    public void setCorrespondenceAddress(Address correspondenceAddress) {
        if(correspondenceAddress == null){
            throw new IllegalArgumentException("'correspondenceAddress' is null.");
        }
        this.correspondenceAddress = correspondenceAddress;
    }

    public void setAgreements(Set<Long> agreementIds) {
        if(agreementIds == null){
            throw new IllegalArgumentException("'agreementIds' is null.");
        }
        this.agreementIds = agreementIds;
    }

    public void addAgreement(Long agreementId){
        if(agreementId == null){
            throw new IllegalArgumentException("'agreementId' is null.");
        }
        if(agreementId < 0){
            throw new IllegalArgumentException("'agreementId' is negative.");
        }
        this.agreementIds.add(agreementId);
    }
}
