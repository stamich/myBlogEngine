package pl.codecity.perun.account.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.codecity.perun.account.model.UserStatus;
import pl.codecity.perun.account.service.UserStatusService;

@Component
public class StatusConverter implements Converter<Object, UserStatus> {

    static final Logger logger = LoggerFactory.getLogger(RoleConverter.class);

    @Autowired
    UserStatusService userStatusService;

    /**
     * This method Gets UserStatus by Id
     * @see org.springframework.core.convert.converter.Converter#convert(Object)
     */
    public UserStatus convert(Object element) {
        Long id = Long.parseLong((String)element);
        UserStatus status = userStatusService.findOneById(id);
        logger.info("User status: {}", status);
        return status;
    }
}
