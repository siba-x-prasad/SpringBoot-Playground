package xmlbased;


//import org.springframework.web.bind.annotation.*;
//import org.springframework.http.MediaType;
//
//@RestController
//@RequestMapping("/api/users")
//public class XmlUserController {
//
//    // Handle XML request and return XML response
//    @PostMapping(
//        consumes = MediaType.APPLICATION_XML_VALUE,
//        produces = MediaType.APPLICATION_XML_VALUE
//    )
//    public UserXml createUser(@RequestBody UserXml user) {
//        // Normally, you would save the user to the database
//        user.setEmail(user.getName().toLowerCase() + "@example.com");
//        return user;
//    }
//
//    // Example of returning XML response
//    @GetMapping(
//        value = "/sample",
//        produces = MediaType.APPLICATION_XML_VALUE
//    )
//    public UserXml getSampleUser() {
//    	UserXml user = new UserXml();
//        user.setName("John Doe");
//        user.setAge(30);
//        user.setEmail("john.doe@example.com");
//        return user;
//    }
//}
