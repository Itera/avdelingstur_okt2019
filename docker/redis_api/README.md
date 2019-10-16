# API with redis integration

The goal of this task is to read and write values to a redis cache by using an API.

The API and the redis cache should run in separate containers.

## API
We code for the API we will use is in the [ValuesApi](../ValuesApi) folder.

This is the same API used in the dynamic website task so you should be able to reuse your Dockerfile solution from that task in order to run the API.

## Redis
We will use the redis image for our redis cache: https://hub.docker.com/_/redis

You can start a container using the `docker run` command:
```
docker run --rm --name values-redis -p 6379:6379 redis
```

## Linking
We can now access our redis cache on localhost:6379, but our API can't reach it. Why not?

Let's try to access it using a docker network or link instead.

Try accessing it through a docker link using the --link flag when running the values-api container. This will require you to update the config by updating the `RedisEndpoint` environment variable as well.

```
docker run --rm --link values-redis:redis-endpoint -p 5000:80 -it -e "RedisEndpoint=redis-endpoint" values-api
```

You can try adding and reading values from the the redis cache using the /api/redis endpoints in the swagger documentation for the API on http://localhost:5000/swagger

Try terminating the redis container and create a new one. Does it still contain the values we stored previously? Why/why not?
