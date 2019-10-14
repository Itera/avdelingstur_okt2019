# Kubernetes

## Concepts

* Deployments and pods
* Namespaces and labels
* Service
* Ingress and Ingress Controllers
* Configuration / Secrets (maps)
* Networking
  * https://matthewpalmer.net/kubernetes-app-developer/articles/kubernetes-networking-guide-beginners.html

## Exercises

### Hello

A simple static site with external access

* Create a simple nginx based static html docker container
* Create the namespaces
* Deploy this to kubernetes - deployment
* Create the service definition - service
* Install an ingress controller - ingress-nginx
* Define the ingress for the service - ingress

### Simple service

A simple dynamic site with external access to a frontend that has internal access to a backend.

* Create a simple backend docker container
* Create a simple react frontend that grabs data from the backend
* Create the namespaces
* Deploy this to kubernetes - deployment-backend/deployment-frontend
* Create the service definition - service
* Install an ingress controller - ingress-nginx
* Define the ingress for the service - ingress

