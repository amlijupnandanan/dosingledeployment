import org.jenkinsci.plugins.pipeline.modeldefinition.Utils

def abortPipeline(String message) {
    currentBuild.result = 'ABORTED'
    error("$message")
}

def performPostActions(String stage) {
    if (currentBuild.result != 'ABORTED') {
        
    }
}






def skipStage(String stage) {
    echo "Skipped stage $stage due to conditional"
    echo "skipall status"
    Utils.markStageSkippedForConditional("$stage")
}

def isHigherPriority(String currentStage, String selectedStage) {
     echo "isHigherPriority enters"
     echo "curr stage:  $currentStage and selected stage:$selectedStage"
    def inputs = load 'vars/inputs.groovy'
    return inputs.getPriority("$currentStage") >= inputs.getPriority("$selectedStage")
}

def shouldExecuteStage(String currentStage, String selectedStage) {
    echo "shouldExecuteStage enters"
    return isHigherPriority("$currentStage", "$selectedStage")
}

def echoFormattedResults(String name, String value) {
    echo ''
    echo "$name"
    echo "$value"
}





def printSelectedVersions() {
    echo "${env.data}"
}

def promotePreProd() {
    echo "promotePreProd"
}


def registerSchema(stage) {
    echo "Contract Lib Version To Deploy: ${env.contractlibVer}"

}

def getReleaseVersionNumberFromNexus() {
    
}

def incrementReleaseVersionNumberOnNexus() {
       
}

def tagCloudConfigRepo(Boolean isProdStageSkipped) {
   
}

def tagFunctionalTestRepo(releaseVersion=null){

    echo "Tag Functional Test repo with release version: ${releaseVersion}"


}

def triggerWebHook() {
    echo 'Triggering Office365 WebHook'

}

def setComponentsVersions(String stage, String releaseVersion) {
   
    echo "Release Version To Be Deployed : ${releaseVersion}"
   
}

return this
