package com.spring.ecommerce.service;

import com.spring.ecommerce.entity.CreditCard;

import java.util.List;

public interface CreditCardService {

    List<CreditCard> findAllCards();

    CreditCard findCardById(long id);

    CreditCard deleteCard(long id);

}
