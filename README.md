curl --location --request GET 'localhost:8080/student/4' | jq '.'
curl --location --request GET 'localhost:8080/students?offset=0&count=10'  | jq '.'
curl --location --request GET 'localhost:8080/courses?offset=0&count=10'  | jq '.'