pipeline {
    agent {
        docker {
            image 'maven:3.8.1-jdk-11'  // Maven이 설치된 Docker 이미지
            args '-v /root/.m2:/root/.m2'  // 필요에 따라 로컬 Maven 캐시 볼륨 마운트
        }
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
