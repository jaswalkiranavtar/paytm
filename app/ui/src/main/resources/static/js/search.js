var app = angular.module('searchApp', []);
app.controller('tweetsCtrl', function($scope, $timeout) {
	
	document.getElementById("loader").style.display = "none"
	
	$scope.auth = function() {
		authWindow = window.open('/ui/auth-popup.html', '', 'width=400,height=300');
	};
	$scope.query = "";
	$scope.searchResults = []
	$scope.filtered = []
	
    $scope.search = function() {
        
        if(document.getElementById("danger"))
            document.getElementById("danger").style.display = 'none'
        if(document.getElementById("success"))
            document.getElementById("success").style.display = 'none'
        document.getElementById("loader").style.display = "block"
        if(document.getElementById("linksTableClass"))
            document.getElementById("linksTableClass").style.display = "none"
        
        if (angular.equals($scope.searchResults, [])) {
        	fetch('/ui/search-twitter?query=' + $scope.query, {
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
        	$scope.searchResults = data.tweets
        })
    }
}).directive('tweetsGrid', function() {
    return {
        templateUrl: 'tweets-grid.html'
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