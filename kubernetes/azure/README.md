# Azure Kubernetes Service - MAD Platform

You will need the azure command line tools installed.

## Login

    az login

## Set MAD Platform as active Azure context

    az account set --subscription 'MAD Platform'

## Add credentials as a kubectl context

    az aks get-credentials -g ite-tech-aks-rg -n ite-tech-mad-aks
    
Cluster admins can add `--admin` at the end of this command

## Browse dashboard

    az aks browse -g ite-tech-aks-rg -n ite-tech-mad-aks

## Check current kubectl context

    kubectl config current-context

## Change kubectl current context to MAD platform 

    kubectl config use-context ite-tech-mad-aks

Cluster admins (--admin above) will have context name `ite-tech-mad-aks-admin`

## Azure content repository

set subscription so that docker has the correct credentials

    az acr login -n itetechmadacr
    
to push

    docker push itetechmadacr.azurecr.io/<your-image>:<yourversion>
