pipeline {
    agent any

    tools {
        maven 'mave:3.8.1'  // Global Tool Configuration에 정의된 Maven 이름 사용
    }

    environment {
        DOCKER_IMAGE = "starsinriver/test"
        DOCKER_TAG = "v1"
        KUBE_CONTEXT = "your_kube_context"
        KUBE_NAMESPACE = "default"
        KUBE_DEPLOYMENT = "test1"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/StarsinLiver/kube_test.git'
            }
        }

        stage('Modify') {
            steps {
                writeFile file: 'myfile.txt', text: 'Modified content'
            }
        }

        stage('Build Maven Project') {
            steps {
                script {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKER_IMAGE}:${DOCKER_TAG}")
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('', 'dockerhub_credentials_id') {
                        docker.image("${DOCKER_IMAGE}:${DOCKER_TAG}").push()
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                script {
                    kubernetesDeploy(
                        configs: 'k8s/deployment.yaml',
                        kubeconfigId: 'kubeconfig_credentials_id',
                        context: KUBE_CONTEXT,
                        enableConfigSubstitution: true
                    )
                }
            }
        }
    }
}
