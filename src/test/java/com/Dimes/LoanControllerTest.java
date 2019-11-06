package com.Dimes;

import com.Dimes.Controllers.LoanController;
import com.Dimes.Models.JwtRequest;
import com.Dimes.Models.Lender;
import com.Dimes.Models.Loan;
import com.Dimes.Services.LoanService;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import java.util.ArrayList;
import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.Date;
import java.util.List;


@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
//@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
class LoanControllerTest extends  JsonManager {

    @MockBean
    public LoanService loanService;

    @Autowired
    private MockMvc mockMvc;

    private Loan loan = new Loan("Nehemiah Kamolu", "0734125591", "NIN12347UG", 200000, "month", 2, 0,new Date(),"active",2);

    @Before
    HttpHeaders getAuthHeader() throws  Exception
    {
        JwtRequest jwtRequest = new JwtRequest("nehe","nehe");

        MvcResult mvcResult = mockMvc.perform(post("/api/authenticate")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(jwtRequest)))
                .andExpect(status().isOk())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        Token token = super.mapFromJson(result,Token.class);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization","Bearer "+token.getToken());

        return  httpHeaders;

    }

    @Test
    void createLoan() throws Exception {
        when(loanService.createLoan(any(Loan.class))).thenReturn(true);

        MvcResult result = mockMvc.perform(post("/api/createLoan")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(loan))
                .headers(getAuthHeader()))
                .andExpect(status().isOk())
                .andReturn();

        String actual = result.getResponse().getContentAsString().replaceAll("\"","");

        assertEquals("Loan created successfully", actual);

        verify(loanService,times(1)).createLoan(any(Loan.class));
    }

    @Test
    void createLoanFails() throws Exception {
        when(loanService.createLoan(any(Loan.class))).thenReturn(false);

        MvcResult result = mockMvc.perform(post("/api/createLoan")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(loan))
                .headers(getAuthHeader()))
                .andExpect(status().isExpectationFailed())
                .andReturn();

        String actual = result.getResponse().getContentAsString().replaceAll("\"","");

        assertEquals("An error occurred while creating loan", actual);

        verify(loanService,times(1)).createLoan(any(Loan.class));
    }


    @Test
    void getLoansTest() throws Exception
    {
        List<Loan> loanList = Arrays.asList(
                new Loan("Nehemiah Kamolu", "0734125591", "NIN12347UG", 200000, "month", 2, 0,new Date(),"active",2),
               new Loan("Nehemiah John", "0734145591", "NIN12347UG", 200000, "month", 2, 0,new Date(),"active",2)
        );

        when(loanService.getAllLoans()).thenReturn(loanList);

        mockMvc.perform(get("/api/getAllLoans")
                .headers(getAuthHeader()))
                .andExpect(status().isOk());

        verify(loanService, times(1)).getAllLoans();

    }

    @Test
    void deleteLoanTest() throws  Exception
    {
        when(loanService.getLoanById(7)).thenReturn(new Loan());

       MvcResult  result =  mockMvc.perform(delete("/api/deleteLoan/7")
                             .headers(getAuthHeader()))
                            .andExpect(status().isOk())
                            .andReturn();


       assertEquals("Loan deleted successfully",result.getResponse().getContentAsString().replaceAll("\"",""));

        verify(loanService,times(1)).getLoanById( any(Integer.class) );
    }

    @Test
    void deleteLoanBadRequestTest() throws  Exception
    {
        when(loanService.getLoanById(10)).thenReturn(null);
        MvcResult result = mockMvc.perform(delete("/api/deleteLoan/10")
                           .headers(getAuthHeader()))
                           .andExpect(status().isBadRequest())
                           .andReturn();

        assertEquals("Loan does not exist",result.getResponse().getContentAsString().replaceAll("\"",""));
    //400

        verify(loanService,times(1)).getLoanById( any(Integer.class) );
    }


    @Test
    void getLoanByIdTest() throws  Exception
    {
        when(loanService.getLoanById(7)).thenReturn(new Loan());

        mockMvc.perform(get("/api/getLoan/7")
                 .headers(getAuthHeader()))
                .andExpect(status().isOk())
                .andReturn();
        verify(loanService,times(1)).getLoanById(7);

    }



    @Test
    @DisplayName("deleteLoan returns 404")
    void deleteLoanTest2() throws  Exception
    {
        when(loanService.getLoanById(7)).thenReturn(null);
        mockMvc.perform(delete("/api/deleteLoan/7")
                .headers(getAuthHeader()))
                .andExpect(status().isBadRequest()); //expected status is 404 because path variable is null

        verify(loanService,times(1)).getLoanById(7);
    }


}
