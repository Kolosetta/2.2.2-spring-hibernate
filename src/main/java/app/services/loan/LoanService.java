package app.services.loan;

import app.exceptions.UserNotFoundException;
import app.model.User;
import app.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class LoanService {

    @Value("${loan.usersIncomeUri}")
    private String usersIncomeUri;
    @Value("${loan.minParametersForLoanApprove.carCost}")
    private int minCarCost;
    @Value("${loan.minParametersForLoanApprove.userIncome}")
    private int minUserIncome;
    @Value("${loan.maxApproveSumVariables.percentsOfCarCost}")
    private double carCostPercents;
    @Value("${loan.maxApproveSumVariables.countOfMonthsIncome}")
    private int countOfMonths;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserService userService;

    public Double calculateUserMaxLoan(int userId) throws Exception {
        User user = getUserWithActualIncome(userId);
        if (user.getIncome() <= minUserIncome && user.getCar().getPrice() <= minCarCost) {
            return 0.0;
        }
        return Math.max(user.getIncome() * countOfMonths, user.getCar().getPrice() * carCostPercents);
    }

    private User getUserWithActualIncome(int userId) throws Exception {
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new UserNotFoundException("User with ID " + userId + " not found.");
        }

        List<User> usersIncomeList = restTemplate.exchange(
                usersIncomeUri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                }
        ).getBody();

        Optional.ofNullable(usersIncomeList)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(us -> us.getId().equals(user.getId()))
                .findFirst()
                .ifPresent(us -> user.setIncome(us.getIncome()));
        return user;
    }

}
