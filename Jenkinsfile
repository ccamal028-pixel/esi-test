pipeline {
    agent any

    stages {
    stage('test') {
    steps {
    bat 'mvn test'
junit 'target/surefire-reports/*.xml'

    }
}
        stage('build') {
            steps {
                bat 'mvn package'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
            post {
                success {
                    mail(
                        subject: "Build réussi",
                        body: "Le build a réussi avec succès.",
                        to: "andou0590@gmail.com"
                    )
                }
                failure {
                    mail(
                        subject: "Build failed",
                        body: "Le build a echoé.",
                        to: "andou0590@gmail.com"
                    )
                }
            }
        }

        stage('documentation') {
            steps {
                bat 'mvn javadoc:javadoc'
            }
            post {
                always {
                    publishHTML(
                        target: [
                            allowMissing: false,
                            alwaysLinkToLastBuild: true,
                            keepAll: true,
                            reportDir: 'target/site/apidocs',
                            reportFiles: 'index.html',
                            reportName: 'Documentation',
                            reportTitles: 'The Report'
                        ]
                    )
                }
            }
        }
    }
}
