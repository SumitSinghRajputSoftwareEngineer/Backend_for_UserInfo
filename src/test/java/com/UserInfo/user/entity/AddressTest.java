package com.UserInfo.user.entity;


import com.UserInfo.user.entity.Address;
import com.UserInfo.user.entity.Coordinates;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddressTest {
    @Test
    public void testAddress_SetterAndGetters() {
        Address address = new Address();

        address.setAddress("123 Main St");
        address.setCity("Anytown");
        address.setState("CA");
        address.setStateCode("CA");
        address.setPostalCode("12345");
        address.setCountry("USA");
        address.setCoordinates(new Coordinates());

        assertEquals("123 Main St", address.getAddress());
        assertEquals("Anytown", address.getCity());
        assertEquals("CA", address.getState());
        assertEquals("CA", address.getStateCode());
        assertEquals("12345", address.getPostalCode());
        assertEquals("USA", address.getCountry());
        assertNotNull(address.getCoordinates());
//        assertEquals(10.0, address.getCoordinates().getLat());
//        assertEquals(20.0, address.getCoordinates().getLng());
    }

    @Test
    public void testAddress_EmptyConstructor() {
        Address address = new Address();
        assertNull(address.getAddress());
        assertNull(address.getCity());
        assertNull(address.getState());
        assertNull(address.getStateCode());
        assertNull(address.getPostalCode());
        assertNull(address.getCountry());
        assertNull(address.getCoordinates());
    }

}
