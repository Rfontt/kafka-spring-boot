<h1 align="center">Kafka</h1>

In this repository, I used kafka with spring boot and docker-compose to create some topics, 
producers and consumers.

### Run project:

1 - First, you need to run the docker compose to start the kafka and zookeeper:

```
docker-compose up
```

2 - Run the KafkaApplication file: `src/main/java/com/microservice/kafka/KafkaApplication.java`

3 - Use the postman or insomnia to test all rotes.

### Routes:

***Create a message about user***

http://localhost:8080/user-kafka/user - POST

```json
{
  "name": "Rfontt2",
  "email": "rfontt@gmail.com"
}
```

***Get all messages about users***

http://localhost:8080/user-kafka/user - GET

***Create a message about user email***

http://localhost:8080/user-kafka/email - POST

```json
{
  "email": "rfontt@gmail.com"
}
```

***Get all messages about users email***

http://localhost:8080/user-kafka/email - GET

## !Important!

You can get all these routes in ***routes-insomnia*** directory
