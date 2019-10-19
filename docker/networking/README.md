# Networking
## Using plain docker to connect multiple containers in the same docker network

Content:
1. [Simple java api with postgres in same docker network](#simple-java-api-with-postgres-in-same-docker-network)
2. [Postgres data mapped to folder postgres-data in current directory](#spin-up-postgres-with-postgres-data-mapped-to-folder-postgres-data-in-current-directory)
3. [Use docker volumes for database persistence](#use-docker-volumes-for-database-persistence)
4. [Build and deploy to Kubernetes](#build-and-deploy-to-kubernetes)

### Simple java api with postgres in same docker network

We have a simple rest api server written in java that we would like to connect to a postgresql server. 
We could have easily just installed java and postgresql service in a linux vm, but we want to use docker.

The rest api server is built so that it lazy loads the database configuration. 
This enables us to first start the rest api or the other way around without any negative impact.

First create a simple network and start postgres with this network.

```
docker network create simple-network

docker stop some-postgres
docker rm some-postgres
docker run --net=simple-network \
           --name some-postgres \
           -e POSTGRES_PASSWORD=postgres \
           -d postgres
```

Then build the docker image for the rest-api. This will build the Dockerfile residing in rest-api sub folder.

```
docker build rest-api -t rest-api-docker
```

Finally start the rest api server with the network we created above and an environment variable telling the rest api where to find the database.

```
docker stop rest-api-docker
docker rm rest-api-docker
docker run --net=simple-network \
           --name rest-api-docker \
           -p 4567:4567 \
           -e DB_HOST=some-postgres \
           -d rest-api-docker
```

You can now either inspect http://localhost:4567/hello in a browser or use curl:

```
curl http://localhost:4567/hello
 
curl http://localhost:4567/hello?name=Name&age=20
```

### Spin up postgres with postgres data mapped to folder postgres-data in current directory

only works in bash shells that supports ``` `pwd` ``` syntax. 

TODO: Edit the example below to get it working in different environments?

```
mkdir postgres-data
docker stop some-postgres
docker rm some-postgres
docker run --net=simple-network \
            --name some-postgres \
            -e POSTGRES_PASSWORD=postgres \
            -v `pwd`/postgres-data:/var/lib/postgresql/data \
            -d postgres
```


get into docker postgres container:

```
docker exec -it some-postgres bash
```

you can use git bash for ex to get the above example working.

Check that the database has started correctly by inspecting its logs with `docker logs some-postgres`

Then check that the postgres-data folder is now populated with the data files from postgres by running `ls postgres-data` or `dir postgres-data`

Its not difficult to backup this folder as a whole, but I think the main getaway from this is that the location you mount the database files to can reside on a completely different disk. It can even reside on a different server via network mounts.

### Use docker volumes for database persistence

```
docker volume create pgdata
docker stop some-postgres
docker rm some-postgres
docker run --net=simple-network \
           --name some-postgres \
           -e POSTGRES_PASSWORD=postgres \
           -v pgdata:/var/lib/postgresql/data \
           -d postgres
```

Then inspect the volume to find where it stores its data:

```
docker volume inspect pgdata
[
    {
        "CreatedAt": "2019-10-17T19:39:56Z",
        "Driver": "local",
        "Labels": {},
        "Mountpoint": "/var/lib/docker/volumes/pgdata/_data",
        "Name": "pgdata",
        "Options": {},
        "Scope": "local"
    }
]
```

On macOS with Docker for Desktop, this folder is not directly accessible, because docker is running in a vm. You can get around this by inspecting the volume in Kinematic.

On linux the above output will point you directly to where the files are.

## Build and deploy to Kubernetes

In shell

```
cd rest-api
./build.sh
./deployToDev.sh
```

If you run build.sh you must delete the replica set for rest-api. It will then be automatically recreated with the new image.

### Spinning up two kafka brokers with a single zookeeper in host mode

Just an example of real world usage of host network mode for spinning up a kafka cluster.

```
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
```

### Cleanup
docker system prune
