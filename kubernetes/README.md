# Kubernetes

## Concepts

* Deployments and pods
* Namespaces and labels
* Service
* Ingress and Ingress Controllers
* Configuration / Secrets (maps)
* Networking
  * https://matthewpalmer.net/kubernetes-app-developer/articles/kubernetes-networking-guide-beginners.html
* Volumes

## Exercises

* Install an ingress controller - ingress-nginx

### Simple static site

A simple static site with external access

* Create a simple nginx based static html docker container
* Create the namespaces
* Deploy this to kubernetes - deployment (-n simple-dev)
* Create the service definition - service (-n simple-dev)
* Define the ingress for the service - ingress-dev (-n simple-dev)
* Deploy this to kubernetes - deployment (-n simple-prod)
* Create the service definition - service (-n simple-prod)
* Define the ingress for the service - ingress-prod (-n simple-prod)
* Update the app (html content)
* Redeploy - deployment (-n simple-dev)
* Scale deployments

### Simple two layer service - one pod

A simple dynamic site with external access to a frontend that has internal access to a backend.

* Create a simple backend docker container
* Create a simple react frontend that grabs data from the backend
* Create the namespaces
* Deploy this to kubernetes - deployment-backend/deployment-frontend
* Create the service definition - service
* Install an ingress controller - ingress-nginx
* Define the ingress for the service - ingress

### Simple two layer service - multi-pod

A simple dynamic site with external access to a frontend that has internal access to a backend.

* Create a simple backend docker container
* Create a simple react frontend that grabs data from the backend
* Create the namespaces
* Deploy this to kubernetes - deployment-backend/deployment-frontend
* Create the service definition - service
* Install an ingress controller - ingress-nginx
* Define the ingress for the service - ingress

### Debugging tips

* Deploying the debug container in different locations.