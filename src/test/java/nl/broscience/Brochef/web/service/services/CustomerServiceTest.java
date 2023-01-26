package nl.broscience.Brochef.web.service.services;

import nl.broscience.Brochef.web.service.dto.CustomerDto;
import nl.broscience.Brochef.web.service.models.Customer;
import nl.broscience.Brochef.web.service.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    CustomerRepository repo;

    @InjectMocks
    CustomerService service;

    @Test
    void findCustomerById(){
        Customer c = new Customer();
        c.setVegetarian(false);
        c.setVegan(false);
        c.setHeight(185);
        c.setWeight(120);
        c.setGender("Unit");
        c.setFirstName("Andrew");
        c.setLastName("PLEASE-WORK");
        c.setId(1L);

       Mockito.when(repo.findById(1L)).thenReturn(Optional.of(c));

        CustomerDto dto = service.getCustomerById(1L);

        assertEquals(c.getFirstName(),dto.getFirstName());
        assertEquals(c.isVegetarian(),dto.isVegetarian());
        assertEquals(c.isVegan(), dto.isVegan());
        assertEquals(c.getHeight(), dto.getHeight());
        assertEquals(c.getWeight(), dto.getWeight());
        assertEquals(c.getGender(), dto.getGender());
        assertEquals(c.getLastName(), dto.getLastName());
    }


}