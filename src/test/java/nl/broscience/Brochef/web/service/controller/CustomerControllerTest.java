package nl.broscience.Brochef.web.service.controller;


import nl.broscience.Brochef.web.service.security.JwtRequestFilter;
import nl.broscience.Brochef.web.service.security.JwtService;
import nl.broscience.Brochef.web.service.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
@AutoConfigureMockMvc(addFilters = false)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService mockService;

    @MockBean
    JwtService jwtService;

    @MockBean
    JwtRequestFilter jwtRequestFilter;

    @Test
    void shouldReturnEmptyList() throws Exception {

        when(mockService.getAllCustomers()).thenReturn(Collections.emptyList());

        final MockHttpServletResponse response = mockMvc.perform(get("/customers")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void shouldDeleteCustomer() throws Exception {

        final MockHttpServletResponse response = mockMvc.perform(delete("/customers/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("Customer Deleted");
        verify(mockService).deleteCustomer(0L);
    }
}
