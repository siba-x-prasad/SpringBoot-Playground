# Spring Interview questions
## 📘 Table of Contents
- Core Spring Boot Concepts
- Dependency Injection & Beans
- Configuration & Profiles
- Spring Boot Auto-Configuration
- RESTful APIs & Web Layer
- Data Access & Persistence
- Security
- Actuator & Monitoring
- Microservices & Cloud
- Testing in Spring Boot
- Performance & Optimization
- Miscellaneous & Architecture
## Core Spring Boot Concepts
- Q: What is Spring Boot, and how does it differ from the core Spring Framework?
- A: Spring Boot is an opinionated framework that builds on Spring to simplify application setup and production readiness. 
- It provides auto-configuration, starters, embedded servers, and production-grade features so developers write less boilerplate compared to configuring core Spring manually.
- Q: What are starters in Spring Boot?
- A: Starters are curated dependency descriptors (like spring-boot-starter-web) that bring a sensible set of libraries and autoconfiguration for a given capability. They speed up setup and reduce dependency management complexity.
- Q: What does @SpringBootApplication do?
- A: It's a convenience meta-annotation that combines @Configuration, @EnableAutoConfiguration, and @ComponentScan. It marks the primary configuration class and triggers auto-configuration and component scanning.
- Q: What happens when you run SpringApplication.run()?
- A: Spring Boot bootstrap builds an ApplicationContext, applies Environment and property sources, performs auto-configuration, creates and wires beans, starts any embedded server, and runs ApplicationRunner/CommandLineRunner beans.
- Q: How does Spring Boot handle embedded servers?
- A: It provides embedded servlet containers (Tomcat, Jetty, Undertow) via starters and auto-configures them. The embedded server runs in the same JVM process so you can package apps as executable jars.
## Dependency Injection & Beans
- Q: What is a Spring Bean?
- A: Any object instantiated, configured, and managed by the Spring IoC container (ApplicationContext). Beans are created via component scanning (@Component, @Service, etc.) or declared via @Bean methods.
- Q: @Component vs @Service vs @Repository vs @Controller
- A: Functionally similar: all mark components for scanning. Use semantic stereotypes to express intent (@Repository adds persistence exception translation; @Service indicates business logic; @Controller/@RestController for web layer).
- Q: Bean scopes and when to use them
- A: singleton (single instance, default) — most use cases; prototype — on-demand new instances; request, session — web-specific session/request state; application or custom scopes exist. Prefer stateless beans unless state is necessary.
- Q: Constructor vs Setter injection
- A: Constructor injection is preferred for immutability and easier testing. Setter or field injection used sparingly (e.g., optional dependencies). Avoid field injection in production code.
- Q: What is circular dependency and how to resolve it?
- A: Two beans depend on each other; constructor injection fails. Solutions: refactor to remove cycle, use @Lazy on one dependency, use setter injection for one side, or introduce a third bean to hold shared behavior.
- Q: @Autowired, @Qualifier, @Primary differences
- A: @Autowired injects by type; @Qualifier selects a specific bean by name; @Primary marks a bean as the default when multiple candidates exist.
## Configuration & Profiles

- Q: application.properties vs application.yml
- A: Both hold configuration; YAML supports hierarchical, compact syntax. Functionally equivalent to Spring — choose based on readability preference.
- Q: @Value vs @ConfigurationProperties vs Environment
- A: @Value injects individual properties; @ConfigurationProperties binds groups of related properties into POJOs (preferred for structured config and validation); Environment provides programmatic access to properties.
- Q: How to activate Spring Profiles
- A: Via spring.profiles.active property, environment variable, command-line arg (--spring.profiles.active=prod), or programmatically with ConfigurableEnvironment.
- Q: Handling secrets securely
- A: Use vaults (HashiCorp Vault, AWS Secrets Manager), Spring Cloud Vault/Config, environment variables with restricted access, or encrypted properties. Avoid committing secrets to VCS.
## Spring Boot Auto-Configuration
- Q: What is auto-configuration?
- A: Mechanism that configures Spring beans based on classpath and Environment conditions (using @Conditional* annotations). It reduces explicit configuration.
- Q: How to exclude an auto-configuration?
- A: Use @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}) or property spring.autoconfigure.exclude.
- Q: Conditional annotations (@ConditionalOnClass, @ConditionalOnMissingBean)
- A: They enable auto-configs only if certain classes are present or beans are absent — enabling modular, optional setup.
- Q: How to debug auto-configuration?
- A: Start the app with --debug to view ConditionEvaluationReport or use spring-boot-actuator and the autoconfig endpoint with Actuator (or run SpringBootApplicationTests that assert context).
## RESTful APIs & Web Layer
- Q: @Controller vs @RestController
- A: @RestController = @Controller + @ResponseBody. Use @RestController for REST endpoints that return objects serialized as HTTP responses.
- Q: Exception handling in REST APIs
- A: Use @ControllerAdvice with @ExceptionHandler to centralize error handling and map exceptions to HTTP responses. Return ResponseEntity<ErrorDto> with proper status codes.
- Q: What is ResponseEntity and when to use it?
- A: A full HTTP response representation enabling control over status, headers, and body. Use when you need to specify status codes or response headers explicitly.
- Q: Pagination & sorting
- A: Use Spring Data Pageable and Page<T> objects; accept page/size/sort query params and return Page metadata. Support Link headers for HATEOAS if desired.
- Q: Customizing HTTP message converters
- A: Configure HttpMessageConverters via WebMvcConfigurer#extendMessageConverters or register Jackson modules. Add jackson-dataformat-xml for XML support.
- Q: CORS support
- A: Configure via @CrossOrigin at controller/method-level or global config in WebMvcConfigurer#addCorsMappings. For microservices, consider gateway-level CORS handling.
## Data Access & Persistence
- Q: Spring Data JPA basics
- A: Spring Data provides repositories (JpaRepository, CrudRepository) that implement common CRUD and paging operations. It generates queries from method names and supports @Query.
- Q: CrudRepository vs JpaRepository vs PagingAndSortingRepository
- A: CrudRepository basic CRUD; PagingAndSortingRepository adds paging/sorting; JpaRepository extends both and adds JPA-specific methods (flush, batch operations).
- Q: @Transactional behavior
- A: Declares a transactional boundary. By default, runtime exceptions cause rollback; checked exceptions do not. Prefer applying to service layer methods. Be aware of proxy-based limitations (self-invocation bypasses proxies).
- Q: Entity lifecycle callbacks
- A: Use JPA callbacks (@PrePersist, @PostLoad, etc.) or Spring Data auditing annotations (@CreatedDate) to react to persistence events.
- Q: Migrations: Flyway vs Liquibase
- A: Both provide versioned migrations. Flyway is simpler (SQL-first), Liquibase is more feature-rich (XML/SQL/JSON changelogs). Use migrations for schema evolution in CI/CD.
- Q: Lazy vs Eager loading
- A: Lazy delays loading until accessed (reduces initial cost, risk of LazyInitializationException). Eager loads immediately (safer outside transactions but may fetch unnecessary data). Usually prefer lazy with DTOs/joins or fetch strategies.
- Q: Multiple data sources
- A: Configure multiple DataSource beans, LocalContainerEntityManagerFactoryBean per datasource, and use @Primary and @Qualifier to wire correct repositories. Use separate @EnableJpaRepositories configurations.
## Security
- Q: Spring Security integration with Spring Boot
- A: Add spring-boot-starter-security. Spring Boot auto-configures a secure filter chain; override with SecurityFilterChain beans (in Spring Security 5.7+ functional API) or WebSecurityConfigurerAdapter in older versions.
- Q: JWT authentication
- A: Use a filter to validate tokens and populate SecurityContext. Store user details in token claims; use AuthenticationManager and stateless sessions. Consider refresh tokens and secure storage on clients.
- Q: Filter chain
- A: Security uses servlet filter chains where each Filter has responsibilities (authentication, authorization, CSRF, CORS). Order matters — use SecurityFilterChain config to control.
- Q: Method-level vs URL-level security
- A: URL-level secures endpoints via HTTP/security config; method-level (@PreAuthorize, @PostAuthorize) secures service methods (useful for business logic and non-web entry points).
- Q: Disabling default security
- A: Configure a custom SecurityFilterChain permitting requests or set spring.security.user properties. Avoid disabling security in production.
## Actuator & Monitoring
- Q: What is Spring Boot Actuator?
- A: A set of production-ready endpoints for health, metrics, env, beans, etc. Great for monitoring and operational insights.
- Q: Enabling and securing Actuator endpoints
- A: Add spring-boot-starter-actuator. Control exposure via management.endpoints.web.exposure.include and secure using Spring Security rules or network-level protections.
- Q: Custom metrics & Micrometer
- A: Use Micrometer APIs (MeterRegistry.counter(...), timer(...)) to record custom metrics; integrate with Prometheus, Datadog, etc. Expose metrics endpoint for scraping.
- Q: Custom health indicators
- A: Implement HealthIndicator and register a bean to expose custom health checks (database, queue, third-party service).
## Microservices & Cloud
- Q: Service discovery
- A: Use Eureka, Consul, or Kubernetes DNS for service discovery. Spring Cloud integrates with these patterns — or prefer Kubernetes native service discovery.
- Q: Spring Cloud Config
- A: Centralized configuration server that serves property files from Git or other backends. Clients refresh via /actuator/refresh or spring-cloud-bus for distributed refresh.
- Q: Feign Client
- A: Declarative HTTP client that simplifies REST calls with interfaces. Supports pluggable encoders, decoders, and integration with Ribbon/Resilience4j.
- Q: Circuit breakers (Resilience4j)
- A: Protect downstream calls using circuit breakers, retry, bulkhead patterns. Resilience4j is the modern recommended library (Hystrix deprecated).
- Q: API Gateway: Spring Cloud Gateway vs Zuul
- A: Spring Cloud Gateway is reactive, performant, and modern; Zuul (1.x) is older and blocked on servlet model. Gateway supports advanced routing, filters, and resiliency.
- Q: Securing microservice communication
- A: Use mTLS, OAuth2 client credentials, token exchange, or service mesh (Istio) for mutual auth and policy enforcement.
## Testing in Spring Boot
- Q: @SpringBootTest purpose
- A: Loads the full application context for integration tests. Use when you want end-to-end behavior including auto-configured beans.
- Q: @WebMvcTest, @DataJpaTest, @MockBean
- A: @WebMvcTest spins up web layer (controllers) for focused tests. @DataJpaTest configures JPA repositories and an embedded DB. @MockBean replaces beans in the context with mocks.
- Q: Integration testing strategies
- A: Combine @SpringBootTest with testcontainers for real DBs, WireMock for external services, and use test profiles for isolated config.
- Q: Testcontainers
- A: Lightweight Docker-based test fixtures providing reproducible integration tests (Postgres, Kafka, Redis). Prefer over in-memory DBs for production parity.
- Q: Mocking external APIs
- A: Use WireMock, MockRestServiceServer, or test doubles. For contract testing, consider Pact.
## Performance & Optimization
- Q: Improve startup time
- A: Use lazy bean initialization (spring.main.lazy-initialization=true), remove unused auto-configurations, use lighter starters, native image (Graal), or modularize the app. Profile to find hotspots.
- Q: Lazy bean initialization
- A: Defers bean creation until first use — reduces startup but may add latency on first request. Great for less-frequently used components.
- Q: Caching strategies
- A: Use @EnableCaching and @Cacheable with backed caches (Redis, Caffeine). Cache read-heavy, expensive computations. Invalidate carefully to avoid staleness.
- Q: Monitoring memory and GC
- A: Use JVM flags, profilers (Flight Recorder, JFR), and APM tools (New Relic, Datadog). Tune GC settings for throughput vs latency.
## Miscellaneous & Architecture
- Q: Spring MVC vs WebFlux
- A: MVC is servlet/blocking model for synchronous request handling. WebFlux is reactive and non-blocking (Project Reactor) for high-concurrency, I/O-bound workloads. Choose based on use-case and library support.
- Q: Reactive Streams basics
- A: Asynchronous data streams with backpressure (Publisher, Subscriber, Subscription, Processor). WebFlux uses Reactor types (Mono, Flux).
- Q: AOP in Spring Boot
- A: AOP allows cross-cutting concerns (logging, transactions). Implement with @Aspect, pointcuts, and advice. Prefer for orthogonal concerns to keep code clean.
- Q: Creating custom starters
- A: Wrap shared configuration and dependencies in a starter POM/artifact. Useful for company-wide defaults and DRY config.
- Q: Integrations: Kafka, RabbitMQ, Redis
- A: Use Spring for Apache Kafka (spring-kafka), Spring AMQP for RabbitMQ, and spring-data-redis. Handle serialization, consumer groups, idempotency, and offsets.
- Q: GraphQL with Spring Boot
- A: Use spring-graphql or libraries like graphql-java and graphql-spring-boot-starter. Define schema and resolvers; be mindful of N+1 issues (use DataLoader).
## Practical Scenario Answers (short)
- Q: App starting slowly — how to debug?
- A: Enable startup logs (--debug), analyze ConditionEvaluationReport, use Micrometer/JFR for profiling, and check slow bean creation or DB calls. Consider lazy-init and pruning auto-config.
- Q: REST API returns 500 — how to trace and fix?
- A: Check logs/stack trace, reproduce with request, validate input DTO binding, add @ControllerAdvice for better errors, and add request/response logging to identify failure point.
- Q: API versioning strategies
- A: URI versioning (/v1/resource), header versioning (Accept header), or content negotiation. URI is simple; headers decouple URL but add client complexity.
- Q: Deploy Spring Boot in Docker/Kubernetes
- A: Build minimal runtime image (JDK or JRE, or distroless), use readiness/liveness probes, externalize config via ConfigMaps/Secrets, use resource limits and horizontal scaling.
- Q: Externalize configuration for environments
- A: Use profiles (properties/yml), Spring Cloud Config, or environment variables. Keep secrets in secret stores and avoid baking env-specific config into image.
## Short code snippets (common patterns)
```
Controller returning XML/JSON (content negotiation):
@RestController
@RequestMapping("/api/users")
public class UserController {
@PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
public ResponseEntity<User> create(@RequestBody User user) {
// business logic...
return ResponseEntity.status(HttpStatus.CREATED).body(user);
}
}
Defining a custom SecurityFilterChain (Spring Security 5.7+):
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
http.csrf().disable()
.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
return http.build();
}
@ConfigurationProperties example:
@ConfigurationProperties(prefix = "app")
public class AppProperties {
private String name;
private int maxConnections;
// getters/setters
}
```
## Final tips for senior interviews
Explain trade-offs — why you chose a pattern or technology.
Demonstrate understanding of real-world constraints (operational, security, cost).
Show familiarity with observability (metrics, tracing, logs) and CI/CD practices.
Prefer small code examples and architecture diagrams in answers.