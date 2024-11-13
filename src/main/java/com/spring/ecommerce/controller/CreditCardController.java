package com.spring.ecommerce.controller;

import com.spring.ecommerce.dto.CreditCardRequest;
import com.spring.ecommerce.dto.CreditCardResponse;
import com.spring.ecommerce.entity.CreditCard;
import com.spring.ecommerce.entity.User;
import com.spring.ecommerce.service.CreditCardService;
import com.spring.ecommerce.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
@AllArgsConstructor
public class CreditCardController {

    private CreditCardService creditCardService;
    private UserService userService;

    @GetMapping()
    public List<CreditCardResponse> findAll(){
        return creditCardService.findAllCards();
    }

    @GetMapping("/{id}")
    public CreditCardResponse findById(@PathVariable long id){
        return creditCardService.findCardById(id);
    }

    @PostMapping
    public CreditCardResponse saveCreditCard(@RequestBody CreditCardRequest creditCardRequest){
        User foundUser = userService.findUserEntityById(creditCardRequest.getUserId());

        CreditCard creditCard = new CreditCard();
        creditCard.setFullName(creditCardRequest.getFullName());
        creditCard.setCardNo(creditCardRequest.getCardNo());
        creditCard.setExpireDate(creditCardRequest.getExpireDate());
        creditCard.setCvv(creditCardRequest.getCvv());
        creditCard.setUser(foundUser);

        return creditCardService.saveCreditCard(creditCard);
    }

    @PutMapping("/{id}")
    public CreditCardResponse updateCreditCard(@PathVariable long id, @RequestBody CreditCard creditCard){
        return creditCardService.updateCreditCard(id, creditCard);
    }

    @DeleteMapping("/{id}")
    public CreditCardResponse deleteCreditCard(@PathVariable long id){
        return creditCardService.deleteCreditCard(id);
    }


}
