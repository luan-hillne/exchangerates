# Build an automated CICD pipeline using Jenkins and ArgoCD 

## Following is the list of videos for the jenkins CICD tutorials
- [Introduction](https://youtu.be/KJNGXZOfCmo)
- [Step 1 : Develop multi-module project](https://youtu.be/3BeLrFj4K6U)
- [Step 2 : Setup Jenkins & SonarQube server](https://youtu.be/ni8IIh4MxVk)
- [Step 3 : Create Jenkins pipeline](https://youtu.be/XqSSil-mvN8)
- [Step 4 : ArgoCD Setup](https://youtu.be/e411eouHAbQ)

This project, demonstrates how to build an automated CI/CD pipeline using Jenkins and ArgoCD for a Spring Boot project. It covers setting up Jenkins for CI, containerizing the app with Docker, and using ArgoCD for automated deployment to Kubernetes.

### Prerequisites:
 - [Docker](https://docs.docker.com/engine/install/) or [Docker alternative - Colima](https://github.com/abiosoft/colima)
 - [Minikube](https://minikube.sigs.k8s.io/docs/start/)
 - [Helm](https://helm.sh/docs/intro/install/)
 - [kubectl](https://kubernetes.io/docs/tasks/tools/)
 - IDE and JDK-21


## Following is the list of commands and links used in the video.
- Start Docker.
- Build Docker image. (cd into the exchangerates project directory.)
   ```
    docker build -t exchangerates .
   ```
- Run exchangerates project container
   ```
    docker run -p 8080:8080 exchangerates
   ```
- Docker compose command to run the Jenkins and SonarQube server (cd into the cicd/ci directory.)
   ```
    sudo docker-compose up -d 
   ``` 
- Get jenkins password
  ``` 
   docker exec -it jenkins bash
  ```
  ```
   cat /var/jenkins_home/secrets/initialAdminPassword
  ``` 
- Plugins to install in Jenkins : SonarQube scanner,  pipeline-stage-view, Docker Pipeline, Pipeline Utility Steps
- Start Minikube command:
  ```
  minikube start --driver=docker
  ```
- Create Helm chart (cd into exchangerates project directory.)
  ```
  helm create k8chart
  ``` 
- Install helm chart:
    ```
    helm install demochart k8chart
    ```
- ArgoCD chart install : Refer recent [ArgoCD document here](https://argo-cd.readthedocs.io/en/stable/)
    ```
    kubectl create namespace argocd
    kubectl apply -n argocd -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml
    ```
- Port forwarding for ArgoCD service
    ```
   kubectl port-forward svc/argocd-server -n argocd 8091:443
    ```
- Get Admin password for ArgoCD:
  ```
  kubectl get secret argocd-initial-admin-secret -n argocd -o jsonpath="{.data.password}" | base64 --decode
  ```
- Command to generate secret yaml config for dockerhub secret:
   ```
    kubectl create secret docker-registry dockerhub-secret \
    --docker-username=YOUR-USERNAME \
    --docker-password=YOUR-DOCKERHUB-SECRET-TOKEN \
    --docker-email=YOUR-DOCKERHUB_EMAIL-ID \
    --namespace app \
    --dry-run=client -o yaml
   ```
- Create app namespace for exchangerates project:
    ```
  kubectl create namespace app
    ```
- Apply the secrets from command line (cd into cicd/cd directory.):
  - Docker hub secret:
    ```
    kubectl apply -f dockerhub-token.yaml
    ```
  - Github secret:
    ```
    kubectl apply -f github-token.yaml
    ```
- Deploy application.yaml for ArgoCD project deployment.
    ```
  kubectl apply -f application.yaml
    ```
- Get application
    ```
  kubectl get applications -n argocd
   ```
- Mock API website [link here](https://designer.mocky.io/).


### Sample JSON Request for the API :
```
{
  "base": "USD",
  "rates": {
    "AED": 3.6725,
    "JPY": 153.232404,
    "USD": 1,
    "INR": 84.109224
  },
  "last_updated": "2025-03-09T14:37:43.515+00:00"
}

```
### Note : On the actual production environment, do not commit file with credentials.
