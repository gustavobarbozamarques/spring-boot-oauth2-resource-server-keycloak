# Spring Boot Oauth2 Resource Server Keycloak

 

### Run project

TODO

Keycloak: ``` http://localhost:8080/ ```

Resource Server API: ``` http://localhost:8081/ ```

### Keycloak (data imported from ``` keycloak-myrealm.json ``` file)

Client Roles:
![Alt text](docs/keycloak_client_roles.png?raw=true)

Users:
![Alt text](docs/keycloak_users.png?raw=true)

User 1 Roles:
![Alt text](docs/keycloak_user_role.png?raw=true)

### Postman

Try to access Resource Server /admin endpoint Unauthenticated (401):
![Alt text](docs/postman_resource_server_unauthenticated.png?raw=true)

Get Access Token:
![Alt text](docs/postman_get_acess_token.png?raw=true)

Access Resource Server /admin endpoint with access token (200):
![Alt text](docs/postman_resource_server_ok.png?raw=true)

Try to access Resource Server /guest endpoint without GUEST_USER role (403)
![Alt text](docs/postman_resource_server_forbidden.png?raw=true)