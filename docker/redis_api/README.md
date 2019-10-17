# API with redis integration

The goal of this task is to read and write values to a redis cache by using an API.

The API and the redis cache should run in separate containers.

## API
The code for the API we will use is in the [ValuesApi](../ValuesApi) folder.

This is the same API used in the dynamic website task so you should be able to reuse your image from that task.

## Redis
We will use the redis image for our redis cache: https://hub.docker.com/_/redis

You can start a container using the `docker run` command:
```
docker run --rm --name values-redis --publish 6379:6379 redis
```

## Accessing redis via the API
We can now access our redis cache on localhost:6379, but our API can't reach it. Why not?

Let's try to access it using a docker network or link instead.

## Using link
Try accessing it through a docker link using the --link flag when running the values-api container. This will require you to update the config by updating the `RedisEndpoint` environment variable as well.

```
docker run --rm --link values-redis:redis-endpoint --publish 5000:80 -it -e "RedisEndpoint=redis-endpoint" values-api
```

## Using network
- **Create a network for this application using the `docker network` command:**
```
docker network create redis-api-net
```

- **Start your containers in this network:**
```
docker run --rm --name values-redis --net redis-api-net --publish 6379:6379 redis
```

```
docker run --rm --net redis-api-net --publish 5000:80 -it -e "RedisEndpoint=values-redis" values-api
```

- **Try adding and reading values from the the redis cache using the /api/redis endpoints in the swagger documentation for the API on http://localhost:5000/swagger**

- **Try terminating the redis container and create a new one**

Does it still contain the values we stored previously? Why/why not?
