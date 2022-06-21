# graphql
graphql


## Environment for Development
 Those projects were developed with followings.

 * Java SDK 11 (11.0.3)
 * Spring-boot (2.7.0)
 
 ## Run the demo
The whole application has been packaged to be run as mvn spring-boot:run:

```
curl --location --request POST 'http://localhost:8080/getAll' \
--header 'Content-Type: text/plain' \
--header 'Cookie: XSRF-TOKEN=bde58e09-aae9-4faf-a6d9-59ec12638b5c' \
--data-raw '{
        getAllData{
            name
            email
    }
}'
```

### Clone the application codes
 You need a new folder to clone the codes and you can get the codes from git repo.
 ```
 git clone https://github.com/imdadareeph/graphql.git
 ```
 
 ### Screenshots
 ![alt text](https://raw.githubusercontent.com/imdadareeph/graphql/main/screenshots/2.png "preview1")
 ![alt text](https://raw.githubusercontent.com/imdadareeph/graphql/main/screenshots/3.png "preview2")
 ![alt text](https://raw.githubusercontent.com/imdadareeph/graphql/main/screenshots/4.png "preview3")
 ![alt text](https://raw.githubusercontent.com/imdadareeph/graphql/main/screenshots/5.png "preview5")
   