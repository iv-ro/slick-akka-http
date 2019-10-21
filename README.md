## Running
1. Execute SQL migrations: create db, create user.\
2. ```sbt run```
## Using
#### Create user

###### Curl example:
```
curl --header "Content-Type: application/json"\
  --request POST\
     --data '{"firstName":"john","lastName":"doe","address":"testAddress","birthDate":"2019-05-20"}'\
        http://localhost:8888/api/v1.0/user
```
#### Update user

###### Curl example:
${id} - id of user
```
curl --header "Content-Type: application/json" \ 
    --request PUT \
    --data '{"firstName":"john","lastName":"doe","address":"testAddress2","birthDate":"2019-05-20"}' \
     http://localhost:8888/api/v1.0/user?id=${id}
```

#### Get user

###### Curl example:
${id} - id of user
```
curl --request GET  http://localhost:8888/api/v1.0/user?id=${id}
```

#### Get user list

###### Curl example:
${limit} - limit of users in list [default value = 10] \
${offset} - offset for list of users [default value = 0] \
${sortBy} - field to sort, options: firstName, lastName [default value = lastName]
```
curl --request GET  http://localhost:8888/api/v1.0/user/list?limit=${limit}&offset=${offset}&sortBy=${sortBy}
```
