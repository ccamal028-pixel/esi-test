pipeline {
    agent any
// mot de passe : bino ibey wugx vtnv
    stages {

        stage('Init') {
            steps {
                bat 'mvnw.cmd clean'
            }
        }
        stage("PARALLEL"){
         parallel{
         stage('Test') {
                     steps {
                         bat 'mvnw.cmd test'
                         junit 'target/surefire-reports/*.xml'
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
         }}


        stage('Build') {
            steps {
                bat 'mvnw.cmd package'
                archiveArtifacts artifacts: 'target/*.jar'
            }
        }




        stage('Deploy') {
        when { //si la branche production
        branch 'masterr'
        }
                    steps {
                        echo 'deploying'
                        bat 'docker-compose down'
                        bat 'docker-compose up  --build -d'
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
