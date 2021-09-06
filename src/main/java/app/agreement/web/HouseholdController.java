package app.agreement.web;

import app.agreement.domain.AgreementListener;
import app.agreement.domain.nonlife.HouseholdInsurance;
import app.agreement.domain.nonlife.classifiers.TypeOfProperty;
import app.agreement.service.AgreementService;
import app.agreement.web.resource.nonlife.HouseholdResource;
import app.user.service.UserService;
import app.utils.domain.Address;
import app.utils.domain.Season;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class HouseholdController {
    private final AgreementService agreementService;
    private final UserService userService;

    @Autowired
    public HouseholdController(AgreementService agreementService, UserService userService){
        this.agreementService = agreementService;
        this.userService = userService;
    }

    @GetMapping("/user/{userID}/agreement/edit/household-insurance/{agreementID}")
    public String editAgreement(@PathVariable long userID, @PathVariable long agreementID, Model model){
        try{
            AgreementListener agreement = agreementService.getAgreement(agreementID);

            if(agreement.getInsurerID() != userID){
                throw new IllegalArgumentException("InsurerID is different.");
            }

            model.addAttribute("householdResource", new HouseholdResource(agreement));
            model.addAttribute("insurer", userService.getUser(userID));
            model.addAttribute("typesOfProperty", TypeOfProperty.values());
            model.addAttribute("agreementID", agreementID);
            model.addAttribute("action", "edit");

            return "agreement/add-edit/household-insurance";
        }
        catch (IllegalArgumentException e){
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/user/{userID}/agreement/edit/household-insurance/{agreementID}")
    public String editAgreement(@PathVariable long userID, @PathVariable long agreementID, @ModelAttribute("householdResource") @Valid HouseholdResource householdResource, BindingResult bindingResult, Model model){
        try{
            AgreementListener agreement = agreementService.getAgreement(agreementID);

            if(bindingResult.hasErrors()){
                model.addAttribute("typesOfProperty", TypeOfProperty.values());
                model.addAttribute("insurer", userService.getUser(agreement.getInsurerID()));
                model.addAttribute("agreementID", agreementID);
                model.addAttribute("action", "edit");

                return "/agreement/add-edit/household-insurance";
            }

            householdResource.toAgreement(agreement);

            return "redirect:/user/" + userID;
        }
        catch (IllegalArgumentException e){
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/user/{userID}/agreement/add/household-insurance")
    public String addAgreement(@PathVariable long userID, Model model){
        try{
            model.addAttribute("householdResource", new HouseholdResource());
            model.addAttribute("insurer", userService.getUser(userID));
            model.addAttribute("typesOfProperty", TypeOfProperty.values());
            model.addAttribute("agreementID", "none");
            model.addAttribute("action", "add");

            return "agreement/add-edit/household-insurance";
        }
        catch(IllegalArgumentException e){
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/user/{userID}/agreement/add/household-insurance/{agreementID}")
    public String addAgreement(@ModelAttribute @Valid HouseholdResource householdResource, BindingResult bindingResult, Model model, @PathVariable long userID){
        try{
            if(bindingResult.hasErrors()){
                model.addAttribute("typesOfProperty", TypeOfProperty.values());
                model.addAttribute("insurer", userService.getUser(userID));
                model.addAttribute("agreementID", "none");
                model.addAttribute("action", "add");

                return "agreement/add-edit/household-insurance";
            }

            HouseholdInsurance agreement = new HouseholdInsurance(
                    householdResource.getInsurerID(),
                    new Season(householdResource.getBeginningDate(), householdResource.getEndingDate()),
                    householdResource.getInsuranceIndemnity(),
                    householdResource.getMonthlyPayment(),
                    householdResource.getTypeOfProperty(),
                    new Address(householdResource.getPropertyPostalCode(), householdResource.getPropertyCity(), householdResource.getPropertyStreet(), householdResource.getPropertyHouseNumber()),
                    householdResource.getValueOfProperty(),
                    householdResource.getEquipmentValue()
            );

            agreementService.addAgreement(agreement);

            return "redirect:/user/" + userID;
        }
        catch(IllegalArgumentException e){
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }
}
