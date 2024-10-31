```
curl --location --request GET 'localhost:8080/student/4'
curl --location --request GET 'localhost:8080/students?offset=0&count=10'
curl --location --request GET 'localhost:8080/courses?offset=0&count=10'

curl --location --request PUT 'localhost:8080/student' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName": "Meghan",
    "lastName": "Dunlap",
    "email": "Dunlap@gmail.com"
}'

curl --location --request PATCH 'localhost:8080/student/1' \
--header 'Content-Type: application/json' \
--data-raw '{
    "lastName": "Oldman"
}'

curl --location --request PATCH 'localhost:8080/student/1' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName": "Siena2"
}'

curl --location --request PATCH 'localhost:8080/student/1/assign' \
--header 'Content-Type: application/json' \
--data-raw '{
    "courseInstances": [2,3]
}'

```