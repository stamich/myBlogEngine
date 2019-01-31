package pl.codecity.perun.account.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.codecity.perun.account.model.UserRole;
import pl.codecity.perun.account.service.UserRoleService;

@Component
public class RoleConverter implements Converter<Object, UserRole> {

    static final Logger logger = LoggerFactory.getLogger(RoleConverter.class);

    @Autowired
    private UserRoleService userRoleService;

    /**
     * This method Gets UserRole by Id
     * @see org.springframework.core.convert.converter.Converter#convert(Object)
     */
    public UserRole convert(Object element) {
        Integer id = Integer.parseInt((String)element);
        UserRole role = userRoleService.findOneById(id);
        logger.info("User role: {}", role);
        return role;
    }
}
