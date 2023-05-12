pipeline {
    agent any
    stages {
        stage('Clone Git') {
            steps {
                git 'https://github.com/arnabnath180/SpeMajorProject.git'
            }
        }
        stage('Maven Build') {
            steps {
                sh '''
                cd JunkTrade-master-master
                mvn clean install
                '''
            }
        }
        stage('Building backend image') {
            steps {
                sh '''
                cd JunkTrade-master-master
                docker build -t arnabxyz/junktradebackend:1.0 .
                '''
            }
        }
        stage('Pushing backend image to Dockerhub') {
            steps{
                script {
                   docker.withRegistry('', "dockerhub" ) {
                        sh '''
                        cd JunkTrade-master-master
                        docker push arnabxyz/junktradebackend:1.0
                        '''
                    }
                }
            }
        }
        stage('React test') {
            steps {
                sh '''
                    cd junktradefrontend-master
                    npm install --force
                    npm test
                    '''
            }
        }
        stage('Building frontend image') {
            steps {
                sh '''
                cd junktradefrontend-master
                docker build -t arnabxyz/junktradefrontend:1.0 .
                '''
            }
        }
        stage('Pushing frontend image to Dockerhub') {
            steps{
                script {
                   docker.withRegistry('', "dockerhub" ) {
                        sh '''
                        cd junktradefrontend-master
                        docker push arnabxyz/junktradefrontend:1.0
                        '''
                    }
                }
            }
        }
        stage('Ansible Deploy') {
            steps {
                ansiblePlaybook credentialsId: 'ansible', disableHostKeyChecking: true, installation: 'Ansible', inventory: 'inventory', playbook: 'playbook.yml'
            }
        }
    }
}


