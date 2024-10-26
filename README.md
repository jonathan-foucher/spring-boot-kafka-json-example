## Introduction
This project is an example of Kafka producing/consuming with Spring Boot.

The repository contains two Spring Boot projects :
- a kafka producer fed by a controller (REST API)
- a kafka consumer that displays the received records in the logs

## Run the project
### Kafka environment
To deploy the kafka required environment you will need docker installed and run the `docker/docker-compose.yml` file.

It will launch different containers:
- zookeeper
- kafka
- akhq: a browser GUI to check out topics, messages and schemas
- init-kafka: init container to create the required Kafka topic


```
docker-compose -f docker/docker-compose.yml up -d
```

You will be able to access akhq on [this url](http://localhost:8190/)

### Application
Once the Kafka environment started and healthy, you can start the Spring Boot projects and try them out.

Save a movie
```
curl --request POST \
  --url http://localhost:8090/kafka-producer/movies \
  --header 'Content-Type: application/json' \
  --data '{
	"id": 26,
	"title": "Some movie title",
	"release_date": "2022-02-26"
}'
```

Delete a movie
```
curl --request DELETE \
  --url http://localhost:8090/kafka-producer/movies/26
```
