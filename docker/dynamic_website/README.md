# Fetching content from an API

The goal of this task is to serve a website on localhost:8080 that fetches some content from an API from localhost:5000/api/values and renders the result.


## Website
We will serve this website the same way as we did for the static website so you can reuse your solution from that task. Note that we have a new index.html file in this task so you need to build a new image that copies the correct file.

## API
The code for the API we will use is in the [ValuesApi](../ValuesApi) folder.

This API should be run in a separate container and port 80 in this container should be exposed on port 5000 on your system.

- **Write a Dockerfile that that:**

1) Builds the API
2) Runs the API

See the [README](../ValuesApi/README.md) for the API code on how to do this.

- **Create an image from your Dockerfile using the `docker build` command:**
```
docker build --tag values-api .
```

- **Start a container from the image using the `docker run` command:**
```
docker run --rm --publish 5000:80 values-api
```

You should now be able to view the website by opening http://localhost:8080 in a browser.

Can you see the values we got from the API?
