package spring.data.pattern.filtering.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class IndexControllerTest {

    private IndexController subject;
    private String accountType = "standard";

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        subject = new IndexController(accountType);
    }

    @Test
    void homePage() {
        subject.homePage(model);

        verify(model).addAttribute(anyString(),any());
    }
}