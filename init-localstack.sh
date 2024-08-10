#!/usr/bin/env bash

if ! [[ $(docker ps -q -f name=localstack) ]]; then
  echo "WARNING: The localstack Docker container is not running. Please, start it first."
  exit 1
fi

echo
echo "Initializing LocalStack"
echo "======================="

echo
echo "Creating alert-topic in SNS"
echo "---------------------------"
docker exec -t localstack aws --endpoint-url=http://localhost:4566 sns create-topic --name alert-topic

echo
echo "Creating alert-consumer-a-queue in SQS"
echo "--------------------------------------"
docker exec -t localstack aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name alert-consumer-a-queue

echo
echo "Creating alert-consumer-b-queue in SQS"
echo "--------------------------------------"
docker exec -t localstack aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name alert-consumer-b-queue

echo
echo "Subscribing alert-consumer-a-queue to alert-topic"
echo "-------------------------------------------------"
docker exec -t localstack aws --endpoint-url=http://localhost:4566 sns subscribe \
  --topic-arn arn:aws:sns:ap-southeast-1:000000000000:alert-topic \
  --protocol sqs \
  --attributes '{"RawMessageDelivery":"true"}' \
  --notification-endpoint arn:aws:sqs:ap-southeast-1:000000000000:alert-consumer-a-queue

echo
echo "Subscribing alert-consumer-b-queue to alert-topic"
echo "-------------------------------------------------"
docker exec -t localstack aws --endpoint-url=http://localhost:4566 sns subscribe \
  --topic-arn arn:aws:sns:ap-southeast-1:000000000000:alert-topic \
  --protocol sqs \
  --attributes '{"RawMessageDelivery":"true"}' \
  --notification-endpoint arn:aws:sqs:ap-southeast-1:000000000000:alert-consumer-b-queue

echo
echo "LocalStack initialized successfully"
echo "==================================="
echo
