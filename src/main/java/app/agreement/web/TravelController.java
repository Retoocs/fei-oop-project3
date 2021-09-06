package app.agreement.web;

import app.agreement.domain.AgreementListener;
import app.agreement.domain.life.TravelInsurance;
import app.agreement.domain.life.classifiers.PurposeOfTrip;
import app.agreement.service.AgreementService;
import app.agreement.web.resource.life.TravelResource;
import app.user.service.UserService;
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
public class TravelController {
    private final AgreementService agreementService;
    private final UserService userService;

    @Autowired
    public TravelController(AgreementService agreementService, UserService userService){
        this.agreementService = agreementService;
        this.userService = userService;
    }

    @GetMapping("/user/{userID}/agreement/edit/travel-insurance/{agreementID}")
    public String editAgreement(@PathVariable long userID, @PathVariable long agreementID, Model model){
        try{
            AgreementListener agreement = agreementService.getAgreement(agreementID);

            if(agreement.getInsurerID() != userID){
                throw new IllegalArgumentException("InsurerID is different.");
            }

            model.addAttribute("travelResource", new TravelResource(agreement));
            model.addAttribute("insurer", userService.getUser(userID));
            model.addAttribute("users", userService.getUsers().values());
            model.addAttribute("purposesOfTrip", PurposeOfTrip.values());
            model.addAttribute("agreementID", agreementID);
            model.addAttribute("action", "edit");

            return "agreement/add-edit/travel-insurance";
        }
        catch (IllegalArgumentException e){
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/user/{userID}/agreement/edit/travel-insurance/{agreementID}")
    public String editAgreement(@PathVariable long userID, @PathVariable long agreementID, @ModelAttribute("travelResource") @Valid TravelResource travelResource, BindingResult bindingResult, Model model){
        try{
            AgreementListener agreement = agreementService.getAgreement(agreementID);

            if(bindingResult.hasErrors()){
                model.addAttribute("users", userService.getUsers().values());
                model.addAttribute("purposesOfTrip", PurposeOfTrip.values());
                model.addAttribute("insurer", userService.getUser(userID));
                model.addAttribute("agreementID", agreementID);
                model.addAttribute("action", "edit");

                return "/agreement/add-edit/travel-insurance";
            }

            travelResource.toAgreement(agreement);

            return "redirect:/user/" + userID;
        }
        catch (IllegalArgumentException e){
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/user/{userID}/agreement/add/travel-insurance")
    public String addAgreement(@PathVariable long userID, Model model){
        try{
            model.addAttribute("travelResource", new TravelResource());
            model.addAttribute("insurer", userService.getUser(userID));
            model.addAttribute("users", userService.getUsers().values());
            model.addAttribute("purposesOfTrip", PurposeOfTrip.values());
            model.addAttribute("agreementID", "none");
            model.addAttribute("action", "add");

            return "agreement/add-edit/travel-insurance";
        }
        catch(IllegalArgumentException e){
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/user/{userID}/agreement/add/travel-insurance/{agreementID}")
    public String addAgreement(@ModelAttribute @Valid TravelResource travelResource, BindingResult bindingResult, Model model, @PathVariable long userID){
        try{
            if(bindingResult.hasErrors()){
                model.addAttribute("users", userService.getUsers().values());
                model.addAttribute("purposesOfTrip", PurposeOfTrip.values());
                model.addAttribute("insurer", userService.getUser(userID));
                model.addAttribute("action", "add");

                return "agreement/add-edit/travel-insurance";
            }

            TravelInsurance agreement = new TravelInsurance(
                    travelResource.getInsurerID(),
                    new Season(travelResource.getBeginningDate(), travelResource.getEndingDate()),
                    travelResource.getInsuranceIndemnity(),
                    travelResource.getMonthlyPayment(),
                    travelResource.getInsuredID(),
                    travelResource.getIsInEurope(),
                    travelResource.getPurposeOfTrip()
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
