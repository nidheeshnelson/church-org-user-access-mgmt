# church-org-user-access-mgmt
 Features:
User registration & login (email, mobile, or social login)

Role-based access control (members, volunteers, pastors, admins)

Family/group management (for managing household-based participation)

JWT/OAuth2 authentication for secure session handling

Tech:
Spring Security + JWT/OAuth2

Spring Data JPA for user data persistence


Features Overview
User Registration & Login (Email, Mobile, or Social Login):

Users can register using email, mobile, or social login (Google, Facebook, etc.).

For email/mobile, you will need to create endpoints for registration, login, and password management.

Social login can be integrated using OAuth2 (Google, Facebook, etc.).

Role-Based Access Control (RBAC):

Users will have roles such as members, volunteers, pastors, and admins.

You will manage access control using Spring Security by restricting access based on user roles.

Family/Group Management:

Users can be grouped into families or households.

You can create a relationship in your database where users are part of one or more families/groups.

This will allow users to manage household-based participation.

JWT/OAuth2 Authentication:

JWT tokens will be used to secure user sessions.

OAuth2 will be implemented for social login providers (Google, Facebook, etc.).

Tech Stack:

Spring Security: For authentication and authorization (handling login, role-based access control).

JWT/OAuth2: For secure session handling.

Spring Data JPA: For user data persistence (e.g., user profiles, roles, and families/groups).


church-org-user-access-mgmt/
├── src/
│   ├── main/
│   │   ├── java/com/nidheeshnelson/user_access/
│   │   │   ├── config/                   # Security and application config
│   │   │   │   ├── SecurityConfig.java
│   │   │   │   ├── JwtConfig.java
│   │   │   │   ├── CorsConfig.java
│   │   │   │   └── OAuth2Config.java
│   │   │   ├── controller/              # REST Controllers
│   │   │   │   ├── AuthController.java
│   │   │   │   ├── UserController.java
│   │   │   │   └── FamilyController.java
│   │   │   ├── dto/                     # Data Transfer Objects
│   │   │   │   ├── RegisterRequestDTO.java
│   │   │   │   ├── LoginRequestDTO.java
│   │   │   │   ├── UserResponseDTO.java
│   │   │   │   ├── FamilyDTO.java
│   │   │   │   └── OAuth2UserDTO.java
│   │   │   ├── entity/                  # JPA Entities
│   │   │   │   ├── User.java
│   │   │   │   ├── Role.java
│   │   │   │   ├── Family.java
│   │   │   │   └── UserRole.java        # Mapping user to roles
│   │   │   ├── exception/               # Custom exceptions and handlers
│   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   └── CustomExceptions.java
│   │   │   ├── repository/              # Spring Data JPA Repositories
│   │   │   │   ├── UserRepository.java
│   │   │   │   ├── RoleRepository.java
│   │   │   │   └── FamilyRepository.java
│   │   │   ├── security/                # JWT & OAuth2 security logic
│   │   │   │   ├── JwtTokenProvider.java
│   │   │   │   ├── JwtAuthenticationFilter.java
│   │   │   │   ├── CustomOAuth2UserService.java
│   │   │   │   └── OAuth2SuccessHandler.java
│   │   │   ├── service/                 # Business Logic
│   │   │   │   ├── UserService.java
│   │   │   │   ├── AuthService.java
│   │   │   │   ├── RoleService.java
│   │   │   │   └── FamilyService.java
│   │   │   └── ChurchOrgUserAccessMgmtApplication.java
│   │   └── resources/
│   │       ├── application.yml
│   │       └── static/                  # Static files (if needed)
│   └── test/
│       └── java/com/example/church/
│           ├── AuthControllerTest.java
│           ├── UserServiceTest.java
│           └── FamilyControllerTest.java
├── pom.xml
└── README.md


📘 Key Package Responsibilities
1. config/
   Configures Spring Security (SecurityConfig.java), JWT settings (JwtConfig.java), and CORS (CorsConfig.java).

Social login setup through OAuth2Config.java.

2. controller/
   Exposes REST APIs for:

Register/login/logout

Managing user profiles

Household/family grouping actions

3. dto/
   Standardized request and response models.

Keeps internal entities hidden from external APIs.

4. entity/
   JPA mappings for users, roles, and families.

Relationships such as @ManyToOne, @OneToMany, or @ManyToMany.

5. security/
   Contains logic for JWT parsing, OAuth2 user info extraction, and session handling.

Includes filters and token generators.

6. repository/
   Extends JpaRepository to provide DB access with auto-implemented queries.

7. service/
   Application logic like creating users, assigning roles, linking users to families, etc.

8. exception/
   Centralized error handling using @ControllerAdvice.

✅ Suggestions for Further Extension
Add email/mobile verification logic in service and controller layers.

Include a scheduler module if tokens expire or notifications are sent.

Introduce audit package for logging changes and activities.

Use MapStruct in a mapper/ package to convert between entity and dto.

Let me know if you want this turned into a starter Spring Boot project or need help implementing any feature like JWT filters or OAuth2 login!

Explanation of Each Controller
1. AuthController.java:
   /register: Handles user registration. It accepts a RegisterRequestDTO object and calls the register method in the AuthService.

/login: Handles user login. It accepts a LoginRequestDTO object and returns a JWT token for the authenticated user.

2. UserController.java:
   /profile (GET): Fetches the current user's profile using the UserService.

/profile (PUT): Updates the current user's profile using the provided UserResponseDTO.

3. FamilyController.java:
   /my-families (GET): Fetches the list of families the logged-in user belongs to, using the FamilyService.

/add (POST): Adds the logged-in user to a family, passing a FamilyDTO object.

/remove (DELETE): Removes the user from a family, passing a FamilyDTO object.



Explanation of Each DTO
RegisterRequestDTO:

Contains user information required for registration (name, email, password, etc.).

LoginRequestDTO:

Contains the necessary fields for user login (username and password).

UserResponseDTO:

Used to send user details back to the client after successful registration/login. Includes user profile info.

FamilyDTO:

Represents family/group data where users are organized. Contains the familyName, description, and user relationships.

OAuth2UserDTO:

Represents user data retrieved from social login providers (Google/Facebook). Contains id, name, email, and profilePictureUrl.



Explanation of Each Entity
1. User.java:
   Represents the user entity with basic details like firstName, lastName, email, and phoneNumber.

The password field is for the encrypted password.

It has a many-to-many relationship with roles and families through user_roles and user_families tables, respectively.

2. Role.java:
   Represents the role entity (e.g., "ADMIN", "MEMBER", etc.).

A user can have multiple roles, and a role can be assigned to multiple users.

3. Family.java:
   Represents a family or household group to which users can belong.

A family can have multiple users, and a user can belong to multiple families (many-to-many).

4. UserRole.java:
   A mapping entity to establish a many-to-many relationship between users and roles. This table contains references to both User and Role.

These entities are set up for typical user authentication and group management features. Let me know if you need further customization or adjustments!

Explanation of Each Exception:
1. GlobalExceptionHandler.java:
   This class handles various exceptions globally using @RestControllerAdvice.

It catches specific exceptions (e.g., ResourceNotFoundException, InvalidCredentialsException) and returns a structured ErrorResponse object with an appropriate HTTP status code.

2. CustomExceptions.java:
   ResourceNotFoundException: Thrown when a resource (e.g., user or family) cannot be found.

InvalidCredentialsException: Thrown during authentication if the credentials are invalid.

ErrorResponse: A standard response format used to send error messages, with code and message fields.



Explanation of Each Repository:
1. UserRepository.java:
   Extends JpaRepository for CRUD operations on the User entity.

Includes custom methods like findByEmail, findByPhoneNumber, and checks for existence of a user by email or phone number.

2. RoleRepository.java:
   Extends JpaRepository for CRUD operations on the Role entity.

Contains the method findByName to look up roles by name (e.g., "ADMIN", "MEMBER").

3. FamilyRepository.java:
   Extends JpaRepository for CRUD operations on the Family entity.

Includes methods like findByFamilyName to retrieve a family by name and findByMembers_Id to fetch all families for a specific user by userId.

These repositories will handle database interactions for their respective entities, allowing you to perform CRUD operations seamlessly.


Explanation:
SecurityConfig.java:

Configures JWT, OAuth2 login, CORS, and stateless session management.

Uses JwtAuthenticationFilter to check for valid JWT tokens in the HTTP request.

Customizes the OAuth2 login process by using CustomOAuth2UserService to load user details from the OAuth2 provider.

JwtTokenProvider.java:

Provides utility methods for generating JWT tokens, validating them, and extracting user information (e.g., username) from the token.

JwtAuthenticationFilter.java:

Intercepts incoming requests and checks for a JWT token in the Authorization header.

If the token is valid, it sets the Authentication object in the SecurityContext.

CustomOAuth2UserService.java:

Custom OAuth2 service to handle user data retrieved from an OAuth2 provider (e.g., Google, Facebook).

If the user doesn't exist in the database, a new user is created.

OAuth2SuccessHandler.java:

Handles the success of the OAuth2 login process by generating and sending a JWT token as a response.

This completes the security setup for handling JWT and OAuth2 login processes in your application. Let me know if you need further adjustments or explanations!


Explanation of Each Service:
UserService.java:

Contains business logic for retrieving and updating user profiles.

Uses UserRepository to fetch and modify user data.

AuthService.java:

Manages user authentication, including registration (register) and login (login).

Verifies user credentials and generates JWT tokens on successful login.

RoleService.java:

Manages role assignments. It includes logic to add new roles and assign them to users.

FamilyService.java:

Handles family management, including adding users to families and retrieving all families a user belongs to.

Also includes methods for adding/removing a user from a family.



