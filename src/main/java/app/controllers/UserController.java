package app.controllers;

import app.exceptions.UserNotFoundException;
import app.services.loan.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private LoanService loanService;

    @GetMapping("/loan")
    @ResponseBody
    public Map<String, String> getLoan(@RequestParam(value = "userId") Integer userId) throws Exception {
        Map<String, String> response = new HashMap<>();
        var userMaxLoan = loanService.calculateUserMaxLoan(userId);
        response.put("userApprovedLoan", userMaxLoan.toString());
        return response;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleBadRequest(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
