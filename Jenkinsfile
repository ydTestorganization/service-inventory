pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/ydTestorganization/service-inventory'
            }
        }

        stage('Build') {
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Deploy') {
            steps {
                sshagent(['ec2-key']) {
                    sh '''
                    scp target/your-app.jar ubuntu@35.172.117.42:/home/ubuntu/
                    ssh ubuntu@35.172.117.42 'pkill -f your-app.jar || true; nohup java -jar your-app.jar > app.log 2>&1 &'
                    '''
                }
            }
        }
    }
}