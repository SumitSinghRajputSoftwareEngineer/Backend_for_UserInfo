package com.UserInfo.user.entity;

import com.UserInfo.user.entity.Address;
import com.UserInfo.user.entity.Company;
import com.UserInfo.user.entity.Coordinates;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CompanyTest {
    @Test
    public void testCompany_SetterAndGetters() {
        Company company = new Company();

        company.setDepartment("Engineering");
        company.setName("Awesome Inc.");
        company.setTitle("Software Engineer");

        Address address = new Address();
        address.setAddress("1 Main St");
        address.setCity("Anytown");
        address.setState("CA");
        address.setStateCode("CA");
        address.setPostalCode("12345");
        address.setCountry("USA");
        address.setCoordinates(new Coordinates());
        company.setAddress(address);

        assertEquals("Engineering", company.getDepartment());
        assertEquals("Awesome Inc.", company.getName());
        assertEquals("Software Engineer", company.getTitle());
        assertNotNull(company.getAddress());
        assertEquals("1 Main St", company.getAddress().getAddress());
        assertEquals("Anytown", company.getAddress().getCity());
        assertEquals("CA", company.getAddress().getState());
        assertEquals("CA", company.getAddress().getStateCode());
        assertEquals("12345", company.getAddress().getPostalCode());
        assertEquals("USA", company.getAddress().getCountry());
        assertNotNull(company.getAddress().getCoordinates());
//        assertEquals(10.0, company.getAddress().getCoordinates().getLat());
//        assertEquals(20.0, company.getAddress().getCoordinates().getLng());
    }

    @Test
    public void testCompany_EmptyConstructor() {
        Company company = new Company();
        assertNull(company.getDepartment());
        assertNull(company.getName());
        assertNull(company.getTitle());
        assertNull(company.getAddress());
    }
}
