package com.spring.ecommerce.service;

import com.spring.ecommerce.dto.CreditCardResponse;
import com.spring.ecommerce.entity.CreditCard;


import java.util.List;

public interface CreditCardService {

    List<CreditCardResponse> findAllCards();

    CreditCardResponse findCardById(long id);

    CreditCardResponse saveCreditCard(CreditCard creditCard);

    CreditCardResponse updateCreditCard(long id, CreditCard creditCard);

    CreditCardResponse deleteCreditCard(long id);

}
