package com.UserInfo.user.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CryptoTest {
    @Test
    public void testCrypto_SetterAndGetters() {
        Crypto crypto = new Crypto();

        crypto.setCoin("Bitcoin");
        crypto.setWallet("My Bitcoin Wallet");
        crypto.setNetwork("Mainnet");

        assertEquals("Bitcoin", crypto.getCoin());
        assertEquals("My Bitcoin Wallet", crypto.getWallet());
        assertEquals("Mainnet", crypto.getNetwork());
    }

    @Test
    public void testCrypto_EmptyConstructor() {
        Crypto crypto = new Crypto();
        assertNull(crypto.getCoin());
        assertNull(crypto.getWallet());
        assertNull(crypto.getNetwork());
    }
}
