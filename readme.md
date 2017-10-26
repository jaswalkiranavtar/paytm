Steps to run:
1. Make sure you have Java 8 and maven installed.
2. Goto folder: paytm/build-and-run
3. Run this script in bash shell(I use git bash): ./all.sh
4. Following apps will start up:
	(a) OAuth2 server on port 9999
	(b) User activity logger on port 7777
	(c) UI app on port 8080
5. Hit this endpoint to access App: http://localhost:8080/ui/index.html
6. Following are the other functionalities:
	(a) Change Password Endpoint: http://localhost:9999/uaa/change-password
	(b) Register New User: Found on the login page
	(c) All user activity history: http://user:logger@localhost:7777/event?user={username}

Technologies choosen:
1. Java and Spring Boot: Because it is easy to quickly stand up a microservice leveraging autoconfiguration feature of spring boot. And Spring Boot Apps are jar files with tomcat server embedded insdie it. We can run Spring boot App anywhere we have JRE which makesit perfect to deploy on cloud platforms like cloud foundary and AWS.

2. Spring security oauth2: Oauth2 with JWT token is a secure and scalable industry standard. I am using authorization_code flow of oauth2. Auth server runs as a seperate process and we can add any number of microservices to our portfolio and make them protected resource protected by auth server which is respoosible for authentication and generate JWT token which can be used the protected resource application to decide what is authorized to which user. JWT token are self signed such that client app doesn't have to hit auth server for validation once it gets token.

3. Spring Data JPA and H2 in-memory database to store user creds. The reason I added the endpoint to change password on oauth server is that, it uses in-memory database to store credentials and any external service or process cannot access it.

4. Spring AOP: To centrally manage logging of all user actions in all apps. Infact I added the Aspect to common folder so that both UI app and oauth2 server can use it. In practice it should be a seperate module in the form of jar file that any other app can use.

5. UI: Mixture of Angular and JQuery. And Spring Social Twitter API.