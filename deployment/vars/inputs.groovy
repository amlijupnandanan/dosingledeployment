import groovy.transform.Field

@Field
def skipStages = ['Perf':true, 'E2E':true, 'perfCentos':true]

@Field
def serviceReleaseVersions = ['do-intervention':'', 'do-order':'']


@Field
def stagePriorities = [dev:1, stg:2, 'perf':3, ppe: 4, promote-prod:5, prod:9]

@Field
def envParameters = [dev:'', stg:'', perf:'', ppe:'', prod:'']

def getPriority(String stage) {
    echo "getPriority Initialization"
    return stagePriorities["$stage"]
}

def getIsSkipStage(String stage) {
     echo "getIsSkipStage Initialization"
     return skipStages["$stage"]
}
def getReleaseVersion(String stage) {
    envParams = envParameters["$stage"]
    return envParameters["$stage"]
}

def selectEnvParams(String stage) {
 echo "selectEnvParams Initialization"
}

def selectIsSkipStage(String stage) {
    timeout(time: 5, unit: 'MINUTES') {
        // Define the base parameter for skipping the current stage
        def parameters = [
            booleanParam(name: "Skip Jenkins Stage - $stage", defaultValue: false, description: 'Check to skip')
        ]

        // Add the "Skip All Subsequent Stages" parameter only if stage != 'ProdSOO'
        if (stage != 'ProdSOO') {
            parameters << booleanParam(name: "Skip All Subsequent Stages", defaultValue: false, description: 'Check to skip all subsequent stages')
        }

        // Capture the input from the user
        def skipOptions = input(
            message: "Run Jenkins Stage - $stage?",
            ok: 'Submit',
            parameters: parameters
        )

        // Handle the case where input returns a single value
        if (stage == 'ProdSOO') {
            skipStages["$stage"] = skipOptions
            echo 'parameters size is 1'
        } else {
            // Safely handle the "Skip Jenkins Stage - $stage" property
            skipStages["$stage"] = skipOptions["Skip Jenkins Stage - $stage"] ?: false

            // Handle the "Skip All Subsequent Stages" option if applicable
            if (stage != 'ProdSOO' && skipOptions["Skip All Subsequent Stages"]) {
                echo "Skip all option selected on stage $stage"
                skipStages["$stage"] = "SKIP_ALL"
            }
        }
    }
}
return this