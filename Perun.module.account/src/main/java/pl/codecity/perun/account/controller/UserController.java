package pl.codecity.perun.account.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import pl.codecity.perun.account.model.User;
import pl.codecity.perun.account.model.UserRole;
import pl.codecity.perun.account.model.UserStatus;
import pl.codecity.perun.account.service.UserRoleService;
import pl.codecity.perun.account.service.UserService;
import pl.codecity.perun.account.service.UserStatusService;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/user")
@RolesAllowed({"UserRoleType.ADMIN", "UserRoleType.DBA", "UserRoleType.WRITER"})
public class UserController {

    private UserService userService;
    private UserRoleService roleService;
    private UserStatusService statusService;
    private MessageSource messageSource;
    private AuthenticationTrustResolver trustResolver;
    private PersistentTokenBasedRememberMeServices rememberMeServices;

    @Autowired
    public UserController(UserService userService, UserRoleService roleService, UserStatusService statusService, MessageSource messageSource,
                          AuthenticationTrustResolver trustResolver, PersistentTokenBasedRememberMeServices rememberMeServices){
        this.userService = userService;
        this.roleService = roleService;
        this.statusService = statusService;
        this.messageSource = messageSource;
        this.trustResolver = trustResolver;
        this.rememberMeServices = rememberMeServices;
    }

    /**
     * This method will list all existing users.
     */
//    @RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET, produces = "application/json")
//    public String listUsers(ModelMap model) {
//        List<User> users = userService.findAll();
//        model.addAttribute("users", users);
//        model.addAttribute("loggedinuser", getPrincipal());
//        return "userslist";
//    }

    @ApiOperation(value = "Listing all users")
    @GetMapping(value = {"/", "/list"}, produces = "application/json")
    public ResponseEntity<?> listUsers() {
        return (ResponseEntity) userService.findAll();
    }

    /**
     * This method will provide the medium to add a new user.
     */
    @GetMapping(value = { "/newuser" }, produces = "application/json")
    public String newUser(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", getPrincipal());
        return "registration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     */
    @PostMapping(value = { "/newuser" }, produces = "application/json")
    public String saveUser(@Valid User user, BindingResult result,
                           ModelMap model) {

        if (result.hasErrors()) {
            return "registration";
        }

        /*
         * Preferred way to achieve uniqueness of field [sso] should be implementing custom @Unique annotation
         * and applying it on field [sso] of Model class [User].
         *
         * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
         * framework as well while still using internationalized messages.
         *
         */
        if(!userService.isUserNickNameUnique(user.getId(), user.getNickName())){
            FieldError ssoError =new FieldError("user","ssoId",messageSource.
                    getMessage("non.unique.ssoId", new String[]{user.getNickName()}, Locale.getDefault()));
            result.addError(ssoError);
            return "registration";
        }

        userService.save(user);

        model.addAttribute("success", "User " + user.getNickName() + " registered successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        //return "success";
        return "registrationsuccess";
    }


    /**
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = { "/edit-user-{nickName}" }, method = RequestMethod.GET)
    public String editUser(@PathVariable String nickName, ModelMap model) {
        User user = userService.findByNickName(nickName);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", getPrincipal());
        return "registration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = { "/edit-user-{nickName}" }, method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult result, ModelMap model, @PathVariable String nickName) {

        if (result.hasErrors()) {
            return "registration";
        }

		/*//Uncomment below 'if block' if you WANT TO ALLOW UPDATING SSO_ID in UI which is a unique key to a User.
		if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
			FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
		    result.addError(ssoError);
			return "registration";
		}*/


        userService.update(user);

        model.addAttribute("success", "User " + user.getNickName() + " updated successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        return "registrationsuccess";
    }


    /**
     * This method will delete an user by it's nickName value.
     */
    @RequestMapping(value = { "/delete-user-{nickName}" }, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String nickName) {
        userService.deleteByNickName(nickName);
        return "redirect:/list";
    }


    /**
     * This method will provide UserRole list to views
     */
    @ModelAttribute("roles")
    public List<UserRole> initializeProfiles() {
        return roleService.findAll();
    }

    /**
     * This method will provide UserStatus list to views
     */
    @ModelAttribute("statuses")
    public List<UserStatus> initializeStatuses(){
        return statusService.findAll();
    }

    /**
     * This method handles Access-Denied redirect.
     */
    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("loggedinuser", getPrincipal());
        return "accessDenied";
    }

    /**
     * This method handles login GET requests.
     * If users is already logged-in and tries to goto login page again, will be redirected to list page.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        if (isCurrentAuthenticationAnonymous()) {
            return "login";
        } else {
            return "redirect:/list";
        }
    }

    /**
     * This method handles logout requests.
     * Toggle the handlers if you are RememberMe functionality is useless in your app.
     */
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            //new SecurityContextLogoutHandler().logout(request, response, auth);
            rememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/login?logout";
    }

    /**
     * This method returns the principal[user-name] of logged-in user.
     */
    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    /**
     * This method returns true if users is already authenticated [logged-in], else false.
     */
    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return trustResolver.isAnonymous(authentication);
    }
}
