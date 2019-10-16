# Networking
## Using plain docker to connect multiple nodes on the same network

### Simple java api with postgres in same docker network

docker network create simple-network

docker stop some-postgres
docker rm some-postgres
docker run --net=simple-network --name some-postgres -e POSTGRES_PASSWORD=postgres -d postgres

docker build rest-api -t rest-api-docker

docker stop rest-api-docker
docker rm rest-api-docker
docker run --net=simple-network --name rest-api-docker -p 4567:4567 -e DB_HOST=some-postgres -d rest-api-docker

curl http://localhost:4567/hello
 
curl http://localhost:4567/hello?name=Name&age=20

### Spinning up two kafka brokers with a single zookeeper
docker run -d \
    --net=host \
    --name=zookeeper1 \
    -e ZOOKEEPER_CLIENT_PORT=32181 \
    -e ZOOKEEPER_TICK_TIME=2000 \
    confluentinc/cp-zookeeper:5.0.0
    
docker run -d \
    --net=host \
    --name=kafka1 \
    -e KAFKA_ZOOKEEPER_CONNECT=localhost:32181 \
    -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://localhost:29092 \
    -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 \
    confluentinc/cp-kafka:5.0.0

docker run -d \
    --net=host \
    --name=kafka2 \
    -e KAFKA_ZOOKEEPER_CONNECT=localhost:32181 \
    -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://localhost:29093 \
    -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 \
    confluentinc/cp-kafka:5.0.0
