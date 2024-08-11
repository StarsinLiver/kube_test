pipeline {
    agent any

    tools {
        gradle 'gradle'  // Global Tool Configuration에 정의된 Gradle 이름 사용
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

        stage('Build Gradle Project') {
            steps {
                script {
                    // Gradle 빌드 명령 실행
                    sh './gradlew clean build -x test'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // app.jar 파일을 Docker 이미지에 추가하는 경우를 가정합니다.
                    // Dockerfile이 현재 디렉토리의 app.jar 파일을 사용하는지 확인합니다.
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
