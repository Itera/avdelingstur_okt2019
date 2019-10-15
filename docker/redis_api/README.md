In this task we will run the values API used in the previous task and a redis cache in separate containers.

We should be able to read and write values to the redis cache using the API GET/PUT /api/redis/{id} endpoint.

docker run --rm --name values-redis -p 6379:6379 -d redis

docker build -t values-api .

docker run --rm --link values-redis:redis -p 5000:80 -it -e "RedisEndpoint=redis" values-api
