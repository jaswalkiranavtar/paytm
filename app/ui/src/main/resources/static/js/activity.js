var app = angular.module('activityApp', [])

app.controller('contentCtrl',function($scope, $http, $timeout, $filter) {

    $scope.logs = []
    $scope.filtered = []
    
    $scope.loadLogs = function() {
        
        if(document.getElementById("danger"))
            document.getElementById("danger").style.display = 'none'
        if(document.getElementById("success"))
            document.getElementById("success").style.display = 'none'
        document.getElementById("loader").style.display = "block"
        if(document.getElementById("linksTableClass"))
            document.getElementById("linksTableClass").style.display = "none"
        
        if (angular.equals($scope.logs, [])) {
            fetch('/ui/logs', {
                method : 'GET',
                credentials : 'same-origin',
                headers : {
                    "Content-type" : "application/json; charset=UTF-16"
                }
            })
            .then(function(response) {
                if(response.statusText.toLowerCase().includes("error")) {
                    document.getElementById("errorMessage").innerHTML = "Error while pulling content: " + response.status 
                    document.getElementById("success").style.display = 'none'
                    document.getElementById("info").style.display = 'none'
                    document.getElementById("danger").style.display = 'block'
                } else {
                    response.json().then(function(result) {
                        if(result.healthCheckStatusCode == "SUCCESS") {
                            $scope.loadGrid(result.data)
                            document.getElementById("linksTableClass").style.display = "table";
                            document.getElementById("success").style.display = 'block'
                            document.getElementById("danger").style.display = 'none'
                        } else {
                            if(result.error) {
                                document.getElementById("errorMessage").innerHTML = result.message
                            } else {
                                document.getElementById("errorMessage").innerHTML = result.healthCheckStatusMessage + "<br>"
                            }
                            document.getElementById("danger").style.display = 'block'
                            document.getElementById("info").style.display = 'none'
                            document.getElementById("success").style.display = 'none'
                        }
                        document.getElementById("loader").style.display = "none"
                        document.getElementById("linksTableClass").style.display = "table"
                    }).catch(function(err){
                        document.getElementById("errorMessage").innerHTML = "Error while parsing JSON response: " + err
                        document.getElementById("danger").style.display = 'block'
                        document.getElementById("info").style.display = 'none'
                        document.getElementById("success").style.display = 'none'
                        document.getElementById("loader").style.display = "none"
                    })
                }
            }).catch(function(err) {  
                document.getElementById("errorMessage").innerHTML = "Request Failed! " + err
                document.getElementById("danger").style.display = 'block'
                document.getElementById("info").style.display = 'none'
                document.getElementById("success").style.display = 'none'
                document.getElementById("loader").style.display = "none"
            });
        } else {
            document.getElementById("linksTableClass").style.display = "table"
            document.getElementById("success").style.display = 'block'
            document.getElementById("danger").style.display = 'none'
            document.getElementById("loader").style.display = "none"
        }
    }
    
    $scope.loadGrid = function(data) {
        $timeout(function() {
        	$scope.logs = data
        })
    }
    
    $scope.loadLogs()
    
}).directive('contentGrid', function() {
    return {
        templateUrl: 'content-grid.html'
    }
}).directive('resultPanel', function() {
    return {
        templateUrl: 'result-panel.html'
    }
}).directive('allTabs', function($timeout) {
    return {
        templateUrl: 'all-tabs.html',
    }
});