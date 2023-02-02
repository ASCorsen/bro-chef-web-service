package nl.broscience.Brochef.web.service.services;

import nl.broscience.Brochef.web.service.dto.CustomerDto;
import nl.broscience.Brochef.web.service.exceptions.DeleteRecordException;
import nl.broscience.Brochef.web.service.exceptions.RecordNotFoundException;
import nl.broscience.Brochef.web.service.exceptions.UpdateRecordException;
import nl.broscience.Brochef.web.service.models.Customer;
import nl.broscience.Brochef.web.service.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class CustomerServiceTest {

    @Mock
    private CustomerRepository mockRepos;

    private CustomerService customerServiceUnderTest;

    @BeforeEach
    void setUp() {
        customerServiceUnderTest = new CustomerService(mockRepos);
    }

    @Test
    void ShouldCreateCustomer() {
        final Customer customer = new Customer();
        customer.setId(0L);
        customer.setFirstName("firstName");
        customer.setLastName("lastName");
        customer.setGender("gender");
        customer.setWeight(0);
        customer.setHeight(0);
        customer.setDob(LocalDate.of(2020, 1, 1));
        customer.setVegan(false);
        customer.setVegetarian(false);
        final CustomerDto customerDto = new CustomerDto(customer);

        final Customer customer1 = new Customer();
        customer1.setId(0L);
        customer1.setFirstName("firstName");
        customer1.setLastName("lastName");
        customer1.setGender("gender");
        customer1.setWeight(0);
        customer1.setHeight(0);
        customer1.setDob(LocalDate.of(2020, 1, 1));
        customer1.setVegan(false);
        customer1.setVegetarian(false);
        when(mockRepos.save(any(Customer.class))).thenReturn(customer1);

        final Long result = customerServiceUnderTest.createCustomer(customerDto);

        assertThat(result).isEqualTo(0L);
    }

    @Test
    void ShouldGetAllCustomers() {
        final Customer customer = new Customer();
        customer.setId(0L);
        customer.setFirstName("firstName");
        customer.setLastName("lastName");
        customer.setGender("gender");
        customer.setWeight(0);
        customer.setHeight(0);
        customer.setDob(LocalDate.of(2020, 1, 1));
        customer.setVegan(false);
        customer.setVegetarian(false);
        final Iterable<Customer> customers = List.of(customer);
        when(mockRepos.findAll()).thenReturn(customers);

        final Iterable<CustomerDto> result = customerServiceUnderTest.getAllCustomers();

    }

    @Test
    void CheckCustomerRepo() {
        when(mockRepos.findAll()).thenReturn(Collections.emptyList());

        final Iterable<CustomerDto> result = customerServiceUnderTest.getAllCustomers();


    }

    @Test
    void ShouldDeleteCustomer() {
        final Customer customer1 = new Customer();
        customer1.setId(0L);
        customer1.setFirstName("firstName");
        customer1.setLastName("lastName");
        customer1.setGender("gender");
        customer1.setWeight(0);
        customer1.setHeight(0);
        customer1.setDob(LocalDate.of(2020, 1, 1));
        customer1.setVegan(false);
        customer1.setVegetarian(false);
        final Optional<Customer> customer = Optional.of(customer1);
        when(mockRepos.findById(0L)).thenReturn(customer);

        customerServiceUnderTest.deleteCustomer(0L);

        verify(mockRepos).deleteById(0L);
    }

    @Test
    void CheckDeleteCustomerException() {

        when(mockRepos.findById(0L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> customerServiceUnderTest.deleteCustomer(0L)).isInstanceOf(DeleteRecordException.class);
    }

    @Test
    void ShouldGetCustomerById() {
        final Customer customer1 = new Customer();
        customer1.setId(0L);
        customer1.setFirstName("firstName");
        customer1.setLastName("lastName");
        customer1.setGender("gender");
        customer1.setWeight(0);
        customer1.setHeight(0);
        customer1.setDob(LocalDate.of(2020, 1, 1));
        customer1.setVegan(false);
        customer1.setVegetarian(false);
        final Optional<Customer> customer = Optional.of(customer1);
        when(mockRepos.findById(0L)).thenReturn(customer);

        final CustomerDto result = customerServiceUnderTest.getCustomerById(0L);

    }

    @Test
    void CheckRecordNotFoundException() {
        when(mockRepos.findById(0L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> customerServiceUnderTest.getCustomerById(0L))
                .isInstanceOf(RecordNotFoundException.class);
    }

    @Test
    void ShouldUpdateCustomerById() {
        final Customer newCustomer = new Customer();
        newCustomer.setId(0L);
        newCustomer.setFirstName("firstName");
        newCustomer.setLastName("lastName");
        newCustomer.setGender("gender");
        newCustomer.setWeight(0);
        newCustomer.setHeight(0);
        newCustomer.setDob(LocalDate.of(2020, 1, 1));
        newCustomer.setVegan(false);
        newCustomer.setVegetarian(false);

        final Customer customer1 = new Customer();
        customer1.setId(0L);
        customer1.setFirstName("firstName");
        customer1.setLastName("lastName");
        customer1.setGender("gender");
        customer1.setWeight(0);
        customer1.setHeight(0);
        customer1.setDob(LocalDate.of(2020, 1, 1));
        customer1.setVegan(false);
        customer1.setVegetarian(false);
        final Optional<Customer> customer = Optional.of(customer1);
        when(mockRepos.findById(0L)).thenReturn(customer);

        final Customer customer2 = new Customer();
        customer2.setId(0L);
        customer2.setFirstName("firstName");
        customer2.setLastName("lastName");
        customer2.setGender("gender");
        customer2.setWeight(0);
        customer2.setHeight(0);
        customer2.setDob(LocalDate.of(2020, 1, 1));
        customer2.setVegan(false);
        customer2.setVegetarian(false);
        when(mockRepos.save(any(Customer.class))).thenReturn(customer2);

        final CustomerDto result = customerServiceUnderTest.updateCustomerById(0L, newCustomer);


        verify(mockRepos).save(any(Customer.class));
    }

    @Test
    void CheckUpdateRecordException() {

        final Customer newCustomer = new Customer();
        newCustomer.setId(0L);
        newCustomer.setFirstName("firstName");
        newCustomer.setLastName("lastName");
        newCustomer.setGender("gender");
        newCustomer.setWeight(0);
        newCustomer.setHeight(0);
        newCustomer.setDob(LocalDate.of(2020, 1, 1));
        newCustomer.setVegan(false);
        newCustomer.setVegetarian(false);

        when(mockRepos.findById(0L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> customerServiceUnderTest.updateCustomerById(0L, newCustomer))
                .isInstanceOf(UpdateRecordException.class);
    }
}
