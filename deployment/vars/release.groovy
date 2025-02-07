import groovy.transform.Field

// vars/release.groovy
def changesTo(String stage, String skipStatus) {
    echo "changesTo check block"
    echo "checking skipall status in changesTo "
 
        echo "changesTo1"
    // Load inputs.groovy
    def inputs = load 'vars/inputs.groovy'
    def utils = load 'vars/utils.groovy'
    
    if (stage != 'dev') {
        echo "changesTo2"
        echo "$stage"
        inputs.selectIsSkipStage(stage)
        def skipStageValue = inputs.getIsSkipStage(stage)
        echo "Value of inputs.getIsSkipStage('$stage'): $skipStageValue"
        echo "Status of checkbox"
        if (skipStageValue == "SKIP_ALL") {
            skipStatus = 'true'
            echo "Skipping all subsequent stages due to 'Skip All Subsequent Stages' input."
            return skipStatus
            
        }
        else if (inputs.getIsSkipStage("$stage"))
        {
            skipStatus = 'false'
            echo "Skipping current stage."
            utils.skipStage(stage)
            return skipStatus
        }
    }

    if (stage == 'dev') {
        
        releaseChangesToDev(stage)
        return 'false'
    }

    if (stage == 'stg') {
        releaseStg()
        return 'false'
    }
    if (stage == 'perf') {
        releasePERF()
        return 'false'
    }

    if (stage == 'ppe') {
        releasePPE()
        return 'false'
    }

    else if (stage == 'prod') {
        releaseChangesToProdOrDR("$stage")
        return 'false'
    }
    else {
        echo 'Invalid Environment!'
    }
    
}

def releaseChangesToDev(String stage) {
    echo "Release to $stage via single deployment"
    echo " Functional test is done for $stage"
    echo "$stage deploymnet is completed-1"
    
}
return this

def releaseStg() {
    echo "Deploying to stg environment..."
    sh "echo 'Deploying to stg'"
}
return this

def releasePERF() {
    echo "Deploying to PERF environment..."
    sh "echo 'Deploying to PERF'"
}
return this

def releasePPE() {
    echo "Deploying to PPE environment..."
    sh "echo 'Deploying to PPE'"
}
return this

def releasePROD() {
    echo "Deploying to PROD environment..."
    sh "echo 'Deploying to PROD'"
}
return this

def isSkipAllStatus(String isSkipAllStatus){

}

def promotePRODRelease() {

    def inputs = load 'vars/inputs.groovy'
    def utils = load 'vars/utils.groovy'
    def skipStatus = 'false'
    echo "PromotePROD method in release"
    inputs.selectIsSkipStage('Promote-QA')
    def skipStageValue = inputs.getIsSkipStage('promote-prod')

    //this is either true if individual skip click or SKIP_ALL for all subsequence or false -> then got to deployment
    if (skipStageValue == "SKIP_ALL")
    {
            skipStatus = 'true'
            echo "Skipping all subsequent stages due to 'Skip All Subsequent Stages' input."
            return skipStatus
    }
    if (inputs.getIsSkipStage('promote-prod')) {
        utils.skipStage('promote-prod')
        //then its individual skip
        skipStatus = 'false'
        return skipStatus
        
    }
    else {
        echo "Promote-prod Promotion"
        
        def releaseVersion = inputs.getReleaseVersion('promote-prod')
        skipStatus = 'false'
    }
    return skipStatus
}
def addChangeRequestAndPromotePreProd() {
    def inputs = load 'vars/inputs.groovy'
    def utils = load 'vars/utils.groovy'
    def skipStatus = 'false'
    inputs.selectIsSkipStage('Approvals')
    def skipStageValue = inputs.getIsSkipStage('Approvals')
    if (skipStageValue == "SKIP_ALL")
    {
        skipStatus = 'true'
        echo "Skipping all subsequent stages due to 'Skip All Subsequent Stages' input."
        return skipStatus
    }
    if (inputs.getIsSkipStage('Approvals')) {
        utils.skipStage('Approvals')
        skipStatus = 'false'
        return skipStatus
    }
    else {
       echo 'Deploying Approvals and attaching approvals'
    }
    return skipStatus
}

def releaseChangesToProdOrDR(String stage) {

        echo 'Deploying releaseChangesToProdOrDR'
   
}