package com.example.atividades.atividade14;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class EventHandlerTest {

    private EmailService mockEmailService;
    private EventHandler eventHandler;

    @Before
    public void setUp() {
        mockEmailService = Mockito.mock(EmailService.class);
        eventHandler = new EventHandler(mockEmailService);
    }

    @Test
    public void testHandleEvent() {
        String event = "User logged in";
        eventHandler.handleEvent(event);

        verify(mockEmailService, times(1)).sendEmail(
                eq("test@example.com"),
                eq("Event Occurred"),
                eq(event)
        );
    }
}
