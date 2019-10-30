
package com.Dimes;


import com.Dimes.Models.Lender;
import com.Dimes.Models.Loan;
import com.Dimes.Repositories.LoanRepository;
import com.Dimes.Services.LoanService;


import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


/**
 * LoanServiceTests
 */

@SpringBootTest
@ContextConfiguration(classes = {LoanRepository.class,LoanService.class})
public class LoanServiceTests {
    
    @MockBean
    private LoanRepository loanRepository;

    @Autowired
    private LoanService loanService;

    List<Lender> lender = new ArrayList<>();
    private Loan new_loan = new Loan("Nehemiah Kamolu", "0734125591", "NIN12347UG", 200000, "month", 2, 0,new Date(),"active",2);

    @Test
    @DisplayName("Should get all Loans")
    void getAllLoansTest()
    {
       List<Loan> loans = new ArrayList<>();
        
       Mockito.when(loanRepository.findAll()).thenReturn( loans);

       assertEquals(loans, loanService.getAllLoans());
    }


    @Test void createLoanTest()
    {

       Mockito.when(loanRepository.save(new_loan)).thenReturn(new Loan());

       assertTrue(loanService.createLoan(new_loan));
    }

    @Test void deleteLoanTest() {
        Mockito.doNothing().when(loanRepository).deleteById(7);
    }

    @Test
    void getLoanByIdTest()
    {
        Mockito.when(loanRepository.findById(10)).thenReturn(Optional.of(new_loan));

        assertThat(loanService.getLoanById(10)).isEqualTo(new_loan);

    }

   /* @TestConfiguration
    static class TestConfig {
        public LoanService loanService(final LoanRepository repository) {
            return new LoanService(repository);
        }
    }*/
}