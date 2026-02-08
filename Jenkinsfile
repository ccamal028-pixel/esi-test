pipeline {
    agent any

    stages {

        stage('build') {
            steps {
                bat 'mvn package'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
            post {
                success {
                    emailext(
                        subject: "Build réussi",
                        body: "Le build a réussi avec succès.",
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
