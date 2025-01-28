package com.UserInfo.user.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HairTest {
    @Test
    public void testHair_SetterAndGetters() {
        Hair hair = new Hair();

        hair.setColor("Brown");
        hair.setType("Curly");

        assertEquals("Brown", hair.getColor());
        assertEquals("Curly", hair.getType());
    }

    @Test
    public void testHair_EmptyConstructor() {
        Hair hair = new Hair();
        assertNull(hair.getColor());
        assertNull(hair.getType());
    }
}
