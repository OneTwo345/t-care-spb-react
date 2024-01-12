package cg.tcarespb.registration.event;

import cg.tcarespb.models.Account;
import lombok.*;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * @author Sampson Alfred
 */
@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {
    private Account account;
    private String applicationUrl;

    public RegistrationCompleteEvent(Account account, String applicationUrl) {
        super(account);
        this.account = account;
        this.applicationUrl = applicationUrl;
    }
}