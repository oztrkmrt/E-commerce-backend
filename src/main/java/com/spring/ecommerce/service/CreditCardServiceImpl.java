package com.spring.ecommerce.service;

import com.spring.ecommerce.entity.CreditCard;
import com.spring.ecommerce.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditCardServiceImpl implements CreditCardService{

    private final CreditCardRepository creditCardRepository;

    @Autowired
    public CreditCardServiceImpl(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    @Override
    public List<CreditCard> findAllCards() {
        return creditCardRepository.findAll();
    }

    @Override
    public CreditCard findCardById(long id) {
        Optional<CreditCard> foundedCard = creditCardRepository.findById(id);
        if (foundedCard.isPresent()){
            return foundedCard.get();
        }
        //TODO: Exception handling yap
        return null;
    }

    @Override
    public CreditCard deleteCard(long id) {
        CreditCard deletedCard = findCardById(id);
        creditCardRepository.delete(deletedCard);
        return deletedCard;
    }
}
