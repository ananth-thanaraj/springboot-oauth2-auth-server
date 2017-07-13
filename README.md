# springboot-oauth2-auth-server

OAuth2.0 implementation using Spring boot is here.

Authorization server has been set up in such away that the credentials are obtained from Mongo DB.

Steps to test in POSTMAN:

1. Authorization code flow:
    
    a. http://localhost:8080/auth/oauth/authorize?response_type=code&client_id=my-client&redirect_uri=http://example.com
    b. Authorization code is found in the URL of the redirect_uri specified.
    c. http://localhost:8080/auth/oauth/token?grant_type=authorization_code&client_id=my-client&redirect_uri=http://example.com&code=NhHtmR
    d. Use the Client credentials and get the access token to use it on the protected resources
    
2. Client Credentials:
  
    http://localhost:8080/auth/oauth/token?grant_type=client_credentials
    
3. Password

    http://localhost:8080/auth/oauth/token?grant_type=password&username=%username%&password=%password%
    
4. Implicit flow:

    http://localhost:8080/auth/oauth/authorize?response_type=token&client_id=my-client&redirect_uri=http://example.com
    
    use the access token obtained in the re-direct uri to access the proteceted resources.
