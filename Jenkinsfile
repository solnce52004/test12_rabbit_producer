pipeline {

    environment {
        registry = "solnce52004/test12_rabbit_producer"
        registryCredential = 'dockerhub'
        containerName = 'container_test12_rabbit_producer'
    }

    agent any

    stages {
        stage('Build') {
             steps {
                    sh "./gradlew build"
                }
        }
        stage('Docker rmi') {
            steps {
                 sh String.format(
                     '''
                       docker stop %s \
                       || true && docker rm %s && docker rmi -f $(docker images | grep %s | awk '{print $3}') \
                       || true
                     ''',
                     containerName,
                     containerName,
                     registry
                 )
            }
        }
        stage('docker network') {
             steps {
                 sh "docker network create -d bridge test12 || true"
             }
        }
        stage('Docker build') {
             steps {
                  script{
                       myApp =  docker.build(registry + ":latest", ".")
                  }
             }
        }
        stage('Docker run') {
            steps {
                script{
                   myApp.run(' -d -p 188.212.125.157:8001:8001 --network="test12" -v /usr/lib/jvm/java-11-openjdk-amd64/lib/security/:/usr/lib/jvm/java-11-openjdk-amd64/lib/security/ -v /etc/ssl/certs/java/cacerts:/etc/ssl/certs/java/cacerts --name=' + containerName)
                }
            }
        }
        stage('Docker push') {
            steps {
                script{
                  docker.withRegistry('https://registry.hub.docker.com', registryCredential) {
                       myApp.push("latest")
                   }
                }
            }
        }
    }
}
