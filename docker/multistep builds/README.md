#Multistage builds

A multistage build is a way to optimize your build process so you get smaller images.
This is because you pull out the production necessary pars of your builds, for example excluding build tools and/or source files

##How

In your docker file add multiple FROM commands labeled see docker file for an example:
https://github.com/Itera/avdelingstur_okt2019/blob/master/docker/multistep%20builds/DockerExample/DockerExample/Dockerfile
