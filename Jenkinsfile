pipeline {
    agent any


    environment {
    ROLLBACK_TAG = "v1.0.0"
    ROLLBACK_BRANCH = "main"
}
// mot de passe : bino ibey wugx vtnv
    stages {

        stage('Init') {
            steps {
                bat 'mvnw.cmd clean'
            }
        }
    /*    stage("PARALLEL"){
         parallel{
         stage('Test') {
                     steps {
                         bat 'mvnw.cmd test'
                         junit 'target/surefire-reports*//*.xml'
                     }
                 }

                 stage('Documentation') {
                      steps {
                          bat 'mvnw.cmd javadoc:javadoc'
                      }
                      post {
                       always {
                                     publishHTML(target: [
                                         allowMissing: false,
                                         alwaysLinkToLastBuild: true,
                                         keepAll: true,
                                         reportDir: 'target/site/apidocs',
                                         reportFiles: 'index.html',
                                         reportName: 'Documentation'
                                     ])
                       }
                      }
                 }
         }}*/


        stage('Build') {
            steps {
                bat 'mvnw.cmd package'
                archiveArtifacts artifacts: 'target/*.jar'
            }
        }


stage('docker-Deploy') {
    when {
        branch 'main'
    }
    steps {
        echo 'Deploying application...'

        // Stop and remove containers safely
        bat 'docker-compose down --remove-orphans'
        bat 'docker rm -f spring-boot-app || exit 0'
        bat 'docker rm -f mysql-db || exit 0'

        // Rebuild and start
        bat 'docker-compose up --build -d'
    }
}


stage('Health Check') {
    steps {
        echo "Checking Health..."
        sleep time: 15, unit: 'SECONDS'

        script {
            def result = bat(
                script: 'curl -s -o response.json -w %%{http_code} http://localhost:8082/actuator/health',
                returnStdout: true
            ).trim()

            def httpCode = result[-3..-1]

            echo "HTTP Code: ${httpCode}"

            if (httpCode == "200") {
                def body = readFile('response.json')
                if (body.contains('"status":"UP"')) {
                    echo "Application is healthy ✅"
                } else {
                    error("Health endpoint returned DOWN")
                }
            } else {
                error("Application not reachable (HTTP ${httpCode})")
            }
        }
    }
}
stage('Rollback') {
           when {
               expression { currentBuild.result == 'FAILURE' }
           }
           steps {
              /*  def stableTag = sh(
                       script: "git tag --sort=-creatordate | head -n 1",
                       returnStdout: true
                   ).trim()
               echo "pro stable ${stableTag}" */
               echo "Starting rollback to tag: ${ROLLBACK_TAG}"
               script {
                   bat """
                        git fetch origin --tags --force
                        git checkout tags/${ROLLBACK_TAG} -b ${ROLLBACK_BRANCH}
                   """
                   echo "Rolled back to tag ${ROLLBACK_TAG} on new branch ${ROLLBACK_BRANCH}"

                   //sh './deploy.sh'
                   echo "Rollback deployment complete"
               }
           }
       }


    }

//     post {
//         success {
//             mail(
//                 subject: "Build réussi",
//                 body: "Le build a réussi.",
//                 to: "andou0590@gmail.com"
//             )
//         }
//
//         failure {
//             mail(
//                 subject: "Build échoué",
//                 body: "Le build a échoué.",
//                 to: "andou0590@gmail.com"
//             )
//         }
//     }
}
