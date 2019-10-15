# Hosting some simple static content

In this task you will serve a website using nginx.

We will do this by running the base nginx image and mount the local file system directly into the html folder on the container.

docker run --rm --name static-content -p 8080:80 -v $(pwd):/usr/share/nginx/html:ro nginx

We will then then write our own dockerfile that copies the files into the image.

docker build -t static-content .

docker run --name some-static-content -p 8080:80 static-content
