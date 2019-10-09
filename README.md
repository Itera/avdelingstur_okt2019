# Company Trip to Oscarsborg

## Focus

During the trip we will be focussing on containerization and orchestration of containers - docker/kubernetes.

## Homework

Before you arrive - you need to have certain software installed and running on your machine.

### Windows/Mac

Both windows and mac can use Docker Desktop:

* [Docker Desktop for Windows](https://hub.docker.com/editions/community/docker-ce-desktop-windows)
* [Docker Desktop for Mac](https://hub.docker.com/editions/community/docker-ce-desktop-mac)

#### Installation

* Install the application and start it
* On the command line - make sure that `docker version` gives information on server and client versions.
* Under settings - select the Kubernetes section and enable Kubernetes.
* On the command line - make sure that `kubectl version` gives information on server and client versions.
* Under settings > advanced (win)/preferences > resources (mac) give docker at least 4Gb ram and 2 or 3 CPUs.

### Linux

// TODO - install docker and minikube

### Kubernetes Dashboard

This allows us to see what is running on our kubernetes cluster. Setting it up can be a little fiddly - so it's best to get this running in advance too.

#### Win/Mac

To install the kubernetes dashboard - run the following command:

```shell
$ kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v1.10.1/src/deploy/recommended/kubernetes-dashboard.yaml
```

To start the kubernetes dashboard - run the following command:

```shell
$ kubectl proxy
```

You should now be able to open the dashboard on http://localhost:8001/api/v1/namespaces/kube-system/services/https:kubernetes-dashboard:/proxy/

If you open the page you will be asked to authenticate. For this we will need to create a user.

Run the following commands:

// TODO - not sure what syntax for the secrets bit on windows - grep and awk etc.

```shell
$ kubectl apply -f kubernetes-dashboard/dashboard-serviceaccount.yml

$ kubectl apply -f kubernetes-dashboard/dashboard-clusterrolebinding.yml

$ kubectl -n kube-system describe secret $(kubectl -n kube-system get secret | grep admin-user | awk '{print $1}')
```

This should give you a token you can use to access the dashboard.

### Linux

// TODO Minikube - `minikube dashboard` ??