# Hub Forum API

#### Project challenge proposed by Alura in partnership with Oracle Next Education(ONE) in the JAVA Back-end course.

## Requirements

- [JAVA (version 17 onwards)](https://www.oracle.com/br/java/technologies/downloads)
- [Maven](https://maven.apache.org/install.html)
- [MySQL](https://dev.mysql.com/downloads/mysql/)
## Class Diagram
```mermaid
    classDiagram
    class User {
        UUID id
        String name
        String email
        String password
        boolean status
        Collection<GrantedAuthority> getAuthorities()
        String getPassword()
        String getUsername()
        boolean isAccountNonExpired()
        boolean isAccountNonLocked()
        boolean isCredentialsNonExpired()
        boolean isEnabled()
    }
    class Profile {
        UUID id
        String name
    }
    class Course {
        UUID id
        String name
        String category
    }
    class TopicPost {
        UUID id
        String title
        String messenger
        LocalDateTime createdAt
        boolean status
    }
    class TopicResponses {
        UUID id
        String messenger
        LocalDateTime createdAt
        boolean solution
    }

    User "1" --o "*" TopicPost : "topicsPost"
    User "1" --o "*" TopicResponses : "topicResponses"
    User "1" --o "*" Profile : "profiles"
    Profile "1" --o "*" User : "users"
    Course "1" --o "*" TopicPost : "topicsPosts"
    TopicPost "1" --o "*" TopicResponses : "topicResponses"
    TopicPost "1" --o "1" User : "author"
    TopicPost "1" --o "1" Course : "course"
    TopicResponses "1" --o "1" User : "author"
    TopicResponses "1" --o "1" TopicPost : "topicPost"
```
## Cloning and configuring the project
1. Run in terminal:
    ```bash
    git clone git@github.com:woliveira1728/forum-hub-api.git
    ```
2. Configure a MySQL database, define connection details and access credentials in the application.properties file
3. Starting the Server
    ```bash
    mvn spring-boot:run
    ```
4. Navigate to http://localhost:8080 to access the API.
5. Routes
   - Access route documentation at http://localhost:8080/swagger-ui/index.html.
