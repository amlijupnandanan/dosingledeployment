// vars/deploy.groovy

def deployDev() {
    echo "Deploying to Development environment..."
    // Add deployment logic here
}
return this

def deployQA() {
    echo "Deploying to QA environment..."
    sh "echo 'Deploying to QA'"
}
return this

def deployUAT() {
    echo "Deploying to UAT environment..."
    sh "echo 'Deploying to UAT'"
}
return this
