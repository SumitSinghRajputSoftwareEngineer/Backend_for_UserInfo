package com.UserInfo.user.entity;

import com.UserInfo.user.entity.Bank;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankTest {
    @Test
    public void testBank_SetterAndGetters() {
        Bank bank = new Bank();

        bank.setCardExpire("12/25");
        bank.setCardNumber("1234567890123456");
        bank.setCardType("VISA");
        bank.setCurrency("USD");
        bank.setIban("DE89370400440532013000");

        assertEquals("12/25", bank.getCardExpire());
        assertEquals("1234567890123456", bank.getCardNumber());
        assertEquals("VISA", bank.getCardType());
        assertEquals("USD", bank.getCurrency());
        assertEquals("DE89370400440532013000", bank.getIban());
    }

    @Test
    public void testBank_EmptyConstructor() {
        Bank bank = new Bank();
        assertNull(bank.getCardExpire());
        assertNull(bank.getCardNumber());
        assertNull(bank.getCardType());
        assertNull(bank.getCurrency());
        assertNull(bank.getIban());
    }
}
