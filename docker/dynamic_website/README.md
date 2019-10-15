# Fetching content from an API

In this task you will serve a website using nginx that fetches content from an API.

The website will be served in the same way we did for the static website.

docker run --rm --name dynamic-website -p 8080:80 -v $(pwd):/usr/share/nginx/html:ro nginx

docker build -t dynamic-website .

docker run --name my-dynamic-website -p 8080:80 dynamic-website

We will then then write our own dockerfile that copies the files into the image.

docker build -t static-content .

docker run --name some-static-content -p 8080:80 static-content

We will need to create a docker image for the API by writing a dockerfile that builds the API and serves it on a port 5000.

The code for the API is in the ValuesApi folder.

You can build the API using the command:
```
dotnet publish ValuesApi.csproj -c Release -o out
```

This will create a release build of the API and save the files in a folder called `out`.

In order to run the API we can run the command from the `out` folder:
```
dotnet ValuesApi.dll
```
