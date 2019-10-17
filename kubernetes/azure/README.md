# Azure Kubernetes Service - MAD Platform

You will need the azure command line tools installed.

## Login

    az login

## Set MAD Platform as active Azure context

    az account set --subscription 'MAD Platform'

## Add credentials as a kubectl context

    az aks get-credentials -g ite-tech-aks-rg -n ite-tech-mad-aks --admin

## Browse dashboard

    az aks browse -g ite-tech-aks-rg -n ite-tech-mad-aks

## Check current kubectl context

    kubectl config current-context

## Change kubectl current context to MAD platform 

    kubectl config use-context ite-tech-mad-aks-admin