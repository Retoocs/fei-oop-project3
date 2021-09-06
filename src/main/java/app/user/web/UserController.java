package app.user.web;

import app.agreement.service.AgreementService;
import app.user.domain.User;
import app.user.service.UserService;
import app.user.web.resource.UserResource;
import app.utils.domain.Address;
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
public class UserController {

    private final UserService userService;
    private final AgreementService agreementService;

    @Autowired
    public UserController(UserService userService, AgreementService agreementService){
        this.agreementService = agreementService;
        this.userService = userService;
    }

    @GetMapping("/users/")
    public String users(Model model){
        model.addAttribute("users", userService.getUsers());
        return "user/users";
    }

    @GetMapping("/user/{id}")
    public String showUser(@PathVariable long id, Model model){
        try{
            model.addAttribute("user", userService.getUser(id));
            model.addAttribute("userAgreements", agreementService.getUserAgreements(id));
        }
        catch (IllegalArgumentException e){
            model.addAttribute("message", e.getMessage());
            return "error";
        }
        return "user/detail";
    }

    @GetMapping("/user/edit/{id}")
    public String editUser(@PathVariable long id, Model model){
        try{
            model.addAttribute("id", id);
            model.addAttribute("userResource", new UserResource(userService.getUser(id)));
            model.addAttribute("action", "edit");

            return "/user/add-edit";
        }
        catch (IllegalArgumentException e){
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/user/edit/{id}")
    public String editUser(@PathVariable long id, @ModelAttribute @Valid UserResource userResource, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("id", id);
            model.addAttribute("action", "edit");
            return "/user/add-edit";
        }

        User user = userService.getUser(id);

        user.setFirstname(userResource.getFirstname());
        user.setLastname(userResource.getLastname());
        user.setBirthNumber(userResource.getBirthNumber());
        user.setEmail(userResource.getEmail());

        user.setPermanentAddress(new Address(
                userResource.getPerPostalCode(),
                userResource.getPerCity(),
                userResource.getPerStreet(),
                userResource.getPerHouseNumber()
        ));
        user.setCorrespondence(userResource.getCorrespondence());

        try{
            user.setCorrespondenceAddress(new Address(
                    userResource.getCorPostalCode(),
                    userResource.getCorCity(),
                    userResource.getCorStreet(),
                    userResource.getCorHouseNumber()
            ));
        }catch(IllegalArgumentException ignored){}

        return "redirect:/user/" + id;
    }

    @GetMapping("/user/add")
    public String addUser(Model model){
        model.addAttribute("userResource", new UserResource());
        model.addAttribute("id", "none");
        model.addAttribute("action", "add");

        return "/user/add-edit";
    }

    @PostMapping("/user/add/{id}")
    public String addUser(@ModelAttribute @Valid UserResource userResource, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("action", "add");
            model.addAttribute("id", "none");
            return "/user/add-edit";
        }

        try{
            User user = new User(
                    userResource.getFirstname(),
                    userResource.getLastname(),
                    userResource.getBirthNumber(),
                    userResource.getEmail(),
                    new Address(userResource.getPerPostalCode(), userResource.getPerCity(), userResource.getPerStreet(), userResource.getPerHouseNumber()),
                    userResource.getCorrespondence(),
                    new Address(userResource.getCorPostalCode(), userResource.getCorCity(), userResource.getCorStreet(), userResource.getCorHouseNumber())
            );
            userService.addUser(user);
        }
        catch(IllegalArgumentException e){
            User user = new User(
                    userResource.getFirstname(),
                    userResource.getLastname(),
                    userResource.getBirthNumber(),
                    userResource.getEmail(),
                    new Address(userResource.getPerPostalCode(), userResource.getPerCity(), userResource.getPerStreet(), userResource.getPerHouseNumber()),
                    userResource.getCorrespondence()
            );
            userService.addUser(user);
        }

        return "redirect:/users/";
    }

    @GetMapping("/user/remove/{id}")
    public String removeUser(@PathVariable long id, Model model){
        try{
            userService.removeUser(id);
            return "redirect:/users/";
        }
        catch (IllegalArgumentException e){
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }
}
