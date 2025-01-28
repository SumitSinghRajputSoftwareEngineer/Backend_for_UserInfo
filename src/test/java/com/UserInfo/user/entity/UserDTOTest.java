package com.UserInfo.user.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserDTOTest {
    @Test
    public void testUserDTO_SetterAndGetters() {
        UserDTO user = new UserDTO();

        user.setFirstName("John");
        user.setLastName("Doe");
        user.setMaidenName("Smith");
        user.setAge(30);
        user.setGender("Male");
        user.setEmail("john.doe@example.com");
        user.setPhone("123-456-7890");
        user.setUsername("johndoe");
        user.setPassword("password123");
        user.setBirthDate("1990-01-01");
        user.setImage("profile.jpg");
        user.setBloodGroup("A+");
        user.setHeight(175.0);
        user.setWeight(70.0);
        user.setEyeColor("Brown");
        user.setIp("192.168.1.100");
        user.setMacAddress("AA:BB:CC:DD:EE:FF");
        user.setUniversity("XYZ University");
        user.setEin("123456789");
        user.setSsn("1234567890");
        user.setUserAgent("Mozilla/5.0 ...");
        user.setRole("User");

        // Set embedded objects
        Hair hair = new Hair();
        hair.setColor("Brown");
        hair.setType("Curly");
        user.setHair(hair);

        Address address = new Address();
        address.setAddress("123 Main St");
        address.setCity("Anytown");
        address.setState("CA");
        address.setStateCode("CA");
        address.setPostalCode("12345");
        address.setCountry("USA");
        Coordinates coordinates = new Coordinates(); // Example coordinates
        address.setCoordinates(coordinates);
        user.setAddress(address);

        Bank bank = new Bank();
        bank.setCardExpire("12/25");
        bank.setCardNumber("1234567890123456");
        bank.setCardType("VISA");
        bank.setCurrency("USD");
        bank.setIban("DE89370400440532013000");
        user.setBank(bank);

        Company company = new Company();
        company.setDepartment("Engineering");
        company.setName("Acme Corp");
        company.setTitle("Software Engineer");
        company.setAddress(address); // Assuming company has the same address as the user
        user.setCompany(company);

        Crypto crypto = new Crypto();
        crypto.setCoin("Bitcoin");
        crypto.setWallet("bc1q...wallet_address");
        crypto.setNetwork("Bitcoin");
        user.setCrypto(crypto);

        // Verify all the set values
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("Smith", user.getMaidenName());
        assertEquals(30, user.getAge());
        assertEquals("Male", user.getGender());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("123-456-7890", user.getPhone());
        assertEquals("johndoe", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals("1990-01-01", user.getBirthDate());
        assertEquals("profile.jpg", user.getImage());
        assertEquals("A+", user.getBloodGroup());
        assertEquals(175.0, user.getHeight());
        assertEquals(70.0, user.getWeight());
        assertEquals("Brown", user.getEyeColor());
        assertEquals("192.168.1.100", user.getIp());
        assertEquals("AA:BB:CC:DD:EE:FF", user.getMacAddress());
        assertEquals("XYZ University", user.getUniversity());
        assertEquals("123456789", user.getEin());
        assertEquals("1234567890", user.getSsn());
        assertEquals("Mozilla/5.0 ...", user.getUserAgent());
        assertEquals("User", user.getRole());

        // Verify embedded objects
        assertNotNull(user.getHair());
        assertEquals("Brown", user.getHair().getColor());
        assertEquals("Curly", user.getHair().getType());

        assertNotNull(user.getAddress());
        assertEquals("123 Main St", user.getAddress().getAddress());
        assertEquals("Anytown", user.getAddress().getCity());
        assertEquals("CA", user.getAddress().getState());
        assertEquals("CA", user.getAddress().getStateCode());
        assertEquals("12345", user.getAddress().getPostalCode());
        assertEquals("USA", user.getAddress().getCountry());
        assertNotNull(user.getAddress().getCoordinates());

        assertNotNull(user.getBank());
        assertEquals("12/25", user.getBank().getCardExpire());
        assertEquals("1234567890123456", user.getBank().getCardNumber());
        assertEquals("VISA", user.getBank().getCardType());
        assertEquals("USD", user.getBank().getCurrency());
        assertEquals("DE89370400440532013000", user.getBank().getIban());

        assertNotNull(user.getCompany());
        assertEquals("Engineering", user.getCompany().getDepartment());
        assertEquals("Acme Corp", user.getCompany().getName());
        assertEquals("Software Engineer", user.getCompany().getTitle());
        assertNotNull(user.getCompany().getAddress());
        // ... (verify address details for company)

        assertNotNull(user.getCrypto());
        assertEquals("Bitcoin", user.getCrypto().getCoin());
        assertEquals("bc1q...wallet_address", user.getCrypto().getWallet());
        assertEquals("Bitcoin", user.getCrypto().getNetwork());
    }

    @Test
    public void testUserDTO_EmptyConstructor() {
        UserDTO user = new UserDTO();

        assertNull(user.getFirstName());
        assertNull(user.getLastName());
        assertNull(user.getMaidenName());
        assertEquals(0, user.getAge()); // Assuming age is initialized to 0 by default
        assertNull(user.getGender());
        assertNull(user.getEmail());
        assertNull(user.getPhone());
        assertNull(user.getUsername());
        assertNull(user.getPassword());
        assertNull(user.getBirthDate());
        assertNull(user.getImage());
        assertNull(user.getBloodGroup());
        assertEquals(0.0, user.getHeight());
        assertEquals(0.0, user.getWeight());
        assertNull(user.getEyeColor());
        assertNull(user.getIp());
        assertNull(user.getMacAddress());
        assertNull(user.getUniversity());
        assertNull(user.getEin());
        assertNull(user.getSsn());
        assertNull(user.getUserAgent());
        assertNull(user.getRole());
        assertNull(user.getHair());
        assertNull(user.getAddress());
        assertNull(user.getBank());
        assertNull(user.getCompany());
        assertNull(user.getCrypto());
    }
}
