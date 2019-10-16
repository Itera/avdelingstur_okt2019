# Fetching content from an API

The goal of this task is to serve a website on localhost:8080 that fetches some content from an API from localhost:5000/api/values and renders the result.


## Website
We will serve this website the same way as we did for the static website so you can reuse your solution from that task. Note that we have a new index.html file in this task so you need to build a new image that copies the correct file.

## API
We code for the API we will use is in the [ValuesApi](../ValuesApi) folder.

This API should be run in a separate container and exposed on port 5000.

In order to run the API we need to create a Dockerfile that

1) Build the API
2) Run the API

See the README for the API code on how to do this.

Once you have created a Dockerfile for the API we can build an image using the `docker build` command:
```
docker build -t values-api .
```

Then start a container using the `docker run` command:
```
docker run --rm -p 5000:80 values-api
```

You should now be able to view the website by opening http://localhost:8080 in a browser.

Can you see the values we got from the API?
