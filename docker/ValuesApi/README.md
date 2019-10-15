docker run --rm --name values-redis -p 6379:6379 -d redis

docker build -t values-api .

docker run --rm --link values-redis:redis -p 5000:80 -it -e "RedisEndpoint=redis" values-api
