# Hosting a static website

The goal of this task is to serve a website on localhost:8080

We will do this with docker using the nginx image: https://hub.docker.com/_/nginx

## Using mounted volume
We will first solve this by running the base nginx image and mounting the current directory into the /usr/share/nginx/html folder in the container.

```
docker run --rm --name my-static-website -p 8080:80 -v $(pwd):/usr/share/nginx/html:ro nginx
```

You should then be able to view the website by opening http://localhost:8080 in a browser.

Try changing the content of the index.html file and reload the webpage. Did the content change? Why/why not?

## Using custom image
In this task we will copy the index.html file into the container instead of mounting our local file system.

In order to do this we need to create a Dockerfile that inherits from the nginx image and add a copy the index.html file to the /usr/share/nginx/html folder in the container.

You can create an image from your Dockerfile using the `docker build` command:
```
docker build -t static-website .
```

You can then start a container from the image using the `docker run` command:
```
docker run --rm -p 8080:80 static-website
```
You should then be able to view the website by opening http://localhost:8080 in a browser.

Try changing the content of the index.html file and reload the webpage. Did the content change? Why/why not?
