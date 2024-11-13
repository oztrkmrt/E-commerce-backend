package com.spring.ecommerce.service;

import com.spring.ecommerce.dto.CreditCardResponse;
import com.spring.ecommerce.dto.DtoConverter;
import com.spring.ecommerce.entity.CreditCard;
import com.spring.ecommerce.exceptions.EcommerceException;
import com.spring.ecommerce.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.smartcardio.Card;
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
    public List<CreditCardResponse> findAllCards() {
        return DtoConverter.convertCardListToCardResponseList(creditCardRepository.findAll());
    }

    @Override
    public CreditCardResponse findCardById(long id) {
        CreditCard creditCard = creditCardRepository
                .findById(id)
                .orElseThrow(() -> {
                    throw new EcommerceException("Credit card with given id not exist: " + id, HttpStatus.NOT_FOUND);
                });
        return DtoConverter.convertCardToCardResponse(creditCard);
    }

    @Override
    public CreditCardResponse saveCreditCard(CreditCard creditCard) {
        return DtoConverter.convertCardToCardResponse(creditCardRepository.save(creditCard));
    }

    @Override
    public CreditCardResponse updateCreditCard(long id, CreditCard updatedCard) {
        Optional<CreditCard> existingCardOpt = creditCardRepository.findById(id);

        if (existingCardOpt.isPresent()){
            CreditCard existingCard = existingCardOpt.get();
            existingCard.setFullName(updatedCard.getFullName());
            existingCard.setCardNo(updatedCard.getCardNo());
            existingCard.setExpireDate(updatedCard.getExpireDate());
            existingCard.setCvv(updatedCard.getCvv());

            return DtoConverter.convertCardToCardResponse(creditCardRepository.save(existingCard));
        } else {
            throw new EcommerceException("Credit card not exist", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public CreditCardResponse deleteCreditCard(long id) {
        CreditCard deletedCard = creditCardRepository
                .findById(id)
                .orElseThrow(() -> {
                    throw new EcommerceException("Credit card with given id not exist: " + id, HttpStatus.NOT_FOUND);
                });
        creditCardRepository.delete(deletedCard);
        return DtoConverter.convertCardToCardResponse(deletedCard);
    }
}
