package com.UserInfo.user.entity;

import com.UserInfo.user.entity.Coordinates;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoordinatesTest {
    @Test
    public void testCoordinates_ConstructorAndGetters() {
        Coordinates coordinates = new Coordinates();

        coordinates.setLat(10.0);
        coordinates.setLng(20.0);
        assertEquals(10.0, coordinates.getLat());
        assertEquals(20.0, coordinates.getLng());
    }
}
