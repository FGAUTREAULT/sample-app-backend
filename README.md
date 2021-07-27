## Building and testing jar file

To test API with jar file run <kbd>.\mvnw package</kbd> to generate the file under <kbd>target\rest-0.0.1-SNAPSHOT.jar</kbd>.

Then run the jar localy with the following command line:
<pre>
java -jar target/rest-0.0.1-SNAPSHOT.jar -Dserver.port=8080
</pre>

## Running with docker

Very easy with docker, Dockerfile just need the jar file.

Generate the jar file with <kbd>.\mvnw package</kbd> under <kbd>target\rest-0.0.1-SNAPSHOT.jar</kbd>.

<pre>
docker build . -t interview/factory-backend:latest
docker run --name backend -d -p 8080:8080 interview/factory-backend:latest
</pre>

Use <kbd> --build-arg JAR_FILE=target/*.jar</kbd> if you changed something in the configuration.

Run <kbd>docker ps</kbd> and you should see your backend container running.

Open your browser and query api against <kbd>http://localhost:8080/api/greeting?name=<your_name></kbd> as we redirected the ports to 8080 by default.

# API documentation - Swagger

Context path of the webservices is <kbd>"/api"</kbd>
To see the ressources deployed: <kbd>http://localhost:8080/api/swagger-resources/</kbd>
Raw docs: <kbd>http://localhost:8080/api/v2/api-docs</kbd>
UI: <kbd>http://localhost:8080/api/swagger-ui/index.html</kbd>

