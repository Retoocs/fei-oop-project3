package app;

import app.agreement.domain.AgreementListener;
import app.agreement.domain.life.AccidentInsurance;
import app.agreement.domain.life.TravelInsurance;
import app.agreement.domain.life.classifiers.PurposeOfTrip;
import app.agreement.domain.life.classifiers.Territory;
import app.agreement.domain.nonlife.HouseInsurance;
import app.agreement.domain.nonlife.HouseholdInsurance;
import app.agreement.domain.nonlife.classifiers.TypeOfProperty;
import app.agreement.service.AgreementService;
import app.utils.domain.Address;
import app.utils.domain.Season;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import app.user.domain.User;
import app.user.service.UserService;

import java.time.LocalDate;

@SpringBootApplication
public class Application implements CommandLineRunner {
    private final UserService userService;
    private final AgreementService agreementService;

    public Application(UserService userService, AgreementService agreementService){
        this.userService = userService;
        this.agreementService = agreementService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("Open in browser: http://localhost:8080");
    }

    @Override
    public void run(String... args) throws Exception{
        User user1 = new User(
                "Matej",
                "Salazar",
                "001125/0000",
                "m.salazar@stuba.sk",
                new Address("90023", "Lipnik", "Lipova", "1"),
                false
        );

        User user2 = new User(
                "Viktor",
                "Salazar",
                "001125/0000",
                "m.sssalazar@stuba.sk",
                new Address("90023", "Lipnik", "Lipova", "1"),
                false
        );

        User user3 = new User(
                "Lukas",
                "Salazar",
                "001125/0020",
                "m.sawwlazar@stuba.sk",
                new Address("90023", "Lipnik", "Lipova", "1"),
                false
        );

        AgreementListener agr1 = new TravelInsurance(
                user1.getId(),
                new Season(LocalDate.now(), LocalDate.now()),
                1,
                1,
                user1.getId(),
                true,
                PurposeOfTrip.working
        );

        AgreementListener agr2 = new AccidentInsurance(
                user1.getId(),
                new Season(LocalDate.now(), LocalDate.now()),
                1,
                2,
                user1.getId(),
                3,
                4,
                5,
                Territory.Slovakia
        );

        AgreementListener agr3 = new HouseholdInsurance(
                user1.getId(),
                new Season(LocalDate.now(), LocalDate.now()),
                1,
                2,
                TypeOfProperty.brickHouse,
                new Address("55533", "Polakovo", "Kvetna", "17"),
                4,
                5
        );

        AgreementListener agr4 = new HouseInsurance(
                user1.getId(),
                new Season(LocalDate.now(), LocalDate.now()),
                1,
                2,
                TypeOfProperty.brickHouse,
                new Address("55533", "Polakovo", "Kvetna", "17"),
                4,
                true
        );

        userService.addUser(user1);
        userService.addUser(user2);
        userService.addUser(user3);

        agreementService.addAgreement(agr1);
        agreementService.addAgreement(agr2);
        agreementService.addAgreement(agr3);
        agreementService.addAgreement(agr4);

        user1.addAgreement(agr1.getId());
        user1.addAgreement(agr2.getId());
        user1.addAgreement(agr3.getId());
        user1.addAgreement(agr4.getId());
    }


}
