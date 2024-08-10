Demonstration of Spring SNS & SQS & LocalStack

### Steps

1. Start LocalStack
```bash
docker-compose up

./init-localstack.sh
```

2. Start App

<code>./mvnw clean spring-boot:run -Dspring-boot.run.arguments="--server.port=9080 --aws.sqs.destination=alert-consumer-a-queue"</code>

<code>./mvnw clean spring-boot:run -Dspring-boot.run.arguments="--server.port=9081 --aws.sqs.destination=alert-consumer-b-queue"</code>

3. Confirm output
```bash
curl -X POST localhost:8080/api/alerts \
  -H 'Content-Type: application/json' \
  -d '{"level":7, "message":"Heavy rain & strong winds expected. Stay indoors."}'

curl -X POST localhost:8080/api/alerts \
  -H 'Content-Type: application/json' \
  -d '{"level":8, "message":"Flood warning! Move to higher ground."}'

curl -X POST localhost:8080/api/alerts \
  -H 'Content-Type: application/json' \
  -d '{"level":8, "message":"Hurricane alert! Follow local evacuation orders."}'

curl -X POST localhost:8080/api/alerts \
  -H 'Content-Type: application/json' \
  -d '{"level":5, "message":"High pollution alert! Limit outdoor activities."}'

curl -X POST localhost:8080/api/alerts \
  -H 'Content-Type: application/json' \
  -d '{"level":7, "message":"Thunderstorm warning! Seek indoor shelter."}'
```
