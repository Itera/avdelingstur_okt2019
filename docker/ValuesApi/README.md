docker run --rm --name values-redis -p 6379:6379 -d redis

docker build -t values-api .

docker run --rm --link values-redis:redis -it -e "RedisEndpoint=redis" values-api
