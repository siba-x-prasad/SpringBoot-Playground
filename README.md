# Springboot
Documentation of SPRINGBOOT for beginners.
its provide a ready made web application project where all the configuration done itself and provide one ready project.Internally it maintain to provide Web server like **TOMCAT or GLASSFISH**. You no need to manually setup server for web application developed in springboot.
## Springboot Project Links
https://www.springboottutorial.com/spring-boot-projects-with-code-examples
  
## Here are the main concept of spring Boot  
1. Dependency Injection.  
2. Annotation Processor  
3. 

## SPRING SUITE AND SPRING TOOLS 
(IDE- Integrated Development Environment)  
### IDE  
1. NetBeans  
2. Eclipse  
3. Spring Tool Suite (STS)  
  
You can download **Spring STS** from the link below  
**https://spring.io/guides/gs/sts/**  
    
For Example if we want to buidl a laptop we need different parts from different companies. To assesmble them and make the laptop will take a lop of effort. For that there is a dependency between all the parts used to develop an laptop.
Same as laptop if we build a project there are different modules are needed and there must be a dependency between each other.    
**Loose Coupling** If One object is not completely dependent on another object. WE can use one object instead of another. This is known as Loose Coupling. LEts for example, while assembling HP laptop, we can use Hard disk of any company(Samsung, HP,ASUS). So this is known as Loose Coupling.  
  
**Tight Coupling** When a object or classes is totaly dependent on another class and we cant use other class/object instead of the class. THis is known as Tight Coupling. FOr Apple Laptop only Apple harddisk is required to assemble it. So this is tight coupling.  
  
## Request Annotations  
**@Controller**  
This annotation is used to make a class as web controller, which can handle client requests and send a response back to the client. This is a class level annotation, which is put on top of your controller class. Similar to @Service and @Repository it is also a stereotype annotation. If you are wondering what is the difference between them then you can also see this article to learn more about their differences.  
  
Here is an example of @Controller annotation in Spring MVC:
  
```
@Controller
public class HelloController{
// handler methods
}
``` 
**@RequestMapping**  
The Controller class contains several handler methods to handle different HTTP request but how does Spring map a particular request to a particular handler method? Well, that's done with the help of the @RequestMapping annotation. It's a method level annotation which is specified over a handler method.  
  
```
@Controller 
public class HelloControler{ 
  @RequestMapping("/") 
    public String hello(){ 
      return "Hello Spring MVC"; 
    }  
}
```   
  
**@RequestParam**  
This is another useful Spring MVC annotation which is used to bind HTTP parameters into method arguments of handler methods. For example, if you send query parameters along with URLlikie for paging or just to supply some key data then you can get them as method arguments in your handler methods.  
  
Here is an example of @RequestParam annotation in Spring MVC from my earlier article about the difference between RequestParam and PathVariable annotation:  
  
```
@RequestMapping("/book") 
public String showBookDetails( @RequestParam("ISBN") String ISBN, Model model){ 
model.addAttribute("ISBN", ISBN); return "bookDetails"; 
}
```   
If you access your web application which provides book details with a query parameter like below:
  
**http://localhost:8080/book?ISBN=900848893**.   
**@PathVariable**  
This is another annotation which is used to retrieve data from the URL. Unlike @RequestParam annotation which is used to extract query parameters, this annotation enables the controller to handle a request for parameterized URLs like URLs that have variable input as part of their path like:  
  
**http://localhost:8080/books/900083838**.  
If you want to retrieve the ISBN number "900083838" from the URL as method argument then you can use @PathVariable annotation in Spring MVC as shown below:  
  
```
@RequestMapping(value="/books/{ISBN}",method= RequestMethod.GET)
public String showBookDetails(@PathVariable("ISBN") String id,Model model){
   model.addAttribute("ISBN", id);
   return "bookDetails";
} 
```  
**@RequestBody**  
This annotation can convert inbound HTTP data into Java objects passed into the controller's handler method. Just as @ResponseBody tells the Spring MVC to use a message converter when sending a response to the client, the @RequestBody annotations tell the Spring to find a suitable message converter to convert a resource representation coming from a client into an object.  
  
Here is an example:  
  ``` 
@RequestMapping(method=RequestMethod.POST, consumers= "application/json")
public @ResponseBody Course saveCourse(@RequestBody Course aCourse){
   return courseRepository.save(aCourse);
}
```  
**@ResponseBody**  
The @ResponseBody annotation is one of the most useful annotations for developing RESTful web service using Spring MVC. This annotation is used to transform a Java object returned from he a controller to a resource representation requested by a REST client. It can completely bypass the view resolution part.  
  
Here is an example of @ResponseBody annotation in Spring MVC:  
```  
@RequestMapping(method=RequestMethod.POST,consumers= "application/json")
public @ResponseBody Course saveCourse(@RequestBody Course aCourse){
  return courseRepository.save(aCourse);
}
```   
**@RestController**  
This is convenience annotation for developing RESTful web service with Spring MVC framework. The @RestController is a combination of @Controller and @ResponseBody, which was introduced in the Spring 3.4 version.  
  
When you annotate a controller class with @RestController it does two purposes, first, it says that the controller class is handling a request for REST APIs and second you don't need to annotate each method with the @ResposneBody annotation to signal that the response will be converted into a Resource using various HttpMessageConverers.  
  
Here is an example of @RestController annotation in Spring MVC:  
```  
@RestController
class HelloControler{

@RequestMapping("/")
public String hello(){
  return "Hello Spring Booot";
}
}
```  
**@SprinbBootApplication**.  
This is a relatively new annotation but very useful if you are using Spring Boot for creating Java web application with Spring. This single annotation combines three annotations like @Configuration, @EnableAutoConfiguration, and @ComponentScan. If you use Spring Boot, then you can run your application without deploying into a web server, as it comes with an embedded Tomcat server.  
  
When you annotated your Main class with @SpringBootApplication then it enables Java-based configuration as well as component scanning and auto-configuration feature of Spring Boot, which can magically set up things for you by just dropping the required JAR into classpath.  
  
here is an example of using @SpringBootApplicaiton annotation in Spring:  
```    
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication 
// same as @Configuration @EnableAutoConfiguration @ComponentScan
public class SpringBootApp{

 public static void main(String[] args) {
  SpringApplication.run(SpringBootApp.class, args);
 }
}
```  
**FOR MORE DETAILS https://www.java67.com/2019/04/top-10-spring-mvc-and-rest-annotations-examples-java.html#ixzz6GOE3Vp2B**
  
    
**Model, ModelMap, ModelAndView**  
Model, ModelMap, and ModelAndView are used to define a model in a Spring MVC application. Model defines a holder for model attributes and is primarily designed for adding attributes to the model. ModelMap is an extension of Model with the ability to store attributes in a map and chain method calls. ModelAndView is a holder for a model and a view; it allows to return both model and view in one return value.  
  
**Model**. 
Let's start with the most basic concept here – the Model.  

Simply put, the model can supply attributes used for rendering views.  
  
To provide a view with usable data, we simply add this data to its Model object. Additionally, maps with attributes can be merged with Model instances:   
```  
@GetMapping("/showViewPage")
public String passParametersWithModel(Model model) {
    Map<String, String> map = new HashMap<>();
    map.put("spring", "mvc");
    model.addAttribute("message", "Baeldung");
    model.mergeAttributes(map);
    return "viewPage";
}  
```  
**ModelMap**  
Just like the Model interface above, ModelMap is also used to pass values to render a view.  

The advantage of ModelMap is it gives us the ability to pass a collection of values and treat these values as if they were within a Map:  
```
@GetMapping("/printViewPage")
public String passParametersWithModelMap(ModelMap map) {
    map.addAttribute("welcomeMessage", "welcome");
    map.addAttribute("message", "Baeldung");
    return "viewPage";
} 
```  
**ModelAndView**  
The final interface to pass values to a view is the ModelAndView.  

This interface allows us to pass all the information required by Spring MVC in one return:   
```
@GetMapping("/goToViewPage")
public ModelAndView passParametersWithModelAndView() {
    ModelAndView modelAndView = new ModelAndView("viewPage");
    modelAndView.addObject("message", "Baeldung");
    return modelAndView;
}
```  
**The View**    
All the data, we place within these models, is used by a view – in general, a templated view to render the web page.  
  
If we have a Thymeleaf template file targeted by our controller's methods as their view. A parameter passed through the model will be accessible from within the thymeleaf HTML code:    
```
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Title</title>
</head>
<body>
    <div>Web Application. Passed parameter : th:text="${message}"</div>
</body>
</html>
```  
**In spring object's are provided by another dependency container. In spring all the dependency objects are provided by Soring Container. THis is nothing but Dependency Injection.**  
  
**SpringBoot terms**  
**1. @Component** - 
**2. @AutoWired**-
**3. @Controller**-

**@Bean -** indicates that a method produces a bean to be managed by Spring.  
**@Service -** indicates that an annotated class is a service class.  
**@Repository -** indicates that an annotated class is a repository, which is an abstraction of data access and storage.  
**@Configuration -** indicates that a class is a configuration class that may contain bean definitions.  
**@Controller -** marks the class as web controller, capable of handling the requests.  
**@RequestMapping -** maps HTTP request with a path to a controller method.  
**@Autowired -** marks a constructor, field, or setter method to be autowired by Spring dependency injection.  
**@SpringBootApplication -** enables Spring Boot autoconfiguration and component scanning.  
**@Component** is a generic stereotype for a Spring managed component. It turns the class into a Spring bean at the auto-scan time. Classes decorated with this annotation are considered as candidates for auto-detection when using annotation-based   configuration and classpath scanning.   
**@Repository, @Service, and @Controller** are specializations of @Component for more specific use cases. 
**@Target(ElementType.TYPE)**   
**@Retention(RetentionPolicy.RUNTIME)**
**@Documented**  
**@Inherited**   
**@Configuration**   
**@EnableAutoConfiguration**   
**@ComponentScan**   
  
There are also Hibernate **@Entity, @Table, @Id, and @GeneratedValue** annotations in the example.  
  
  
