# microservices-lab

> Only Keycloak and Eureka are working with Docker Compose in this repository. Other services can be run
> individually using intellij.

## Running

To run Keycloak and Eureka using Docker Compose, you can use the following command:

```bash
docker compose up keycloak eureka-server
```

Keycloak is already configured in the docker-compose file along with a Eureka server. After starting the services, you
can access the Keycloak admin console at `http://localhost:8080` with the following credentials:

- Username: `admin`
- Password: `password`

Then access the realm `MsSecurity` and copy the client secret for the `gateway` client to use in your request.

## Testing

Generate a token using the following command:

```bash
wget --no-check-certificate --quiet \
  --method POST \
  --timeout=0 \
  --header 'Content-Type: application/x-www-form-urlencoded' \
  --body-data 'grant_type=password&username=user&password=user&client_id=gateway&client_secret=SECRET_FROM_CLIENT' \
   'http://localhost:8080/realms/MsSecurity/protocol/openid-connect/token'
```

Use the generated token to access a protected resource:

```bash
wget --no-check-certificate --quiet \
  --method GET \
  --timeout=0 \
  --header 'Authorization: Bearer JWT_FROM_PREVIOUS_REQUEST' \
   'http://localhost:8089/students/all'
```

TODO: **Fix error in gateway (docker):**

```log
gateway-1  | java.lang.IllegalStateException: The Issuer "http://localhost:8080/realms/MsSecurity" provided in the configuration did not match the requested issuer "http://keycloak:8080/realms/MsSecurity"
```