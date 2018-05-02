(function() {

	var homeModule = angular.module('mainControllers', [ 'ngRoute' ])

	homeModule.config(function($routeProvider) {
		$routeProvider.when('/', {
			templateUrl : 'app/views/homeview.html',
			controller : 'HomeController',
			controllerAs : "homeCtrl"
		})
	});

	homeModule.controller('HomeController', [ '$scope', '$compile','$routeParams','$window', 'UserFactory',
		function($scope, $compile, $routeParams,$window, UserFactory) {

			
		    $scope.message = "The greatest wealth is HEALTH.";
		
		    $scope.username= "";
			$scope.password = "";              
           					
			$scope.CheckPassword = function(username) {
							
				var promise = UserFactory.findByUsername(username);					
				
				promise.success(function(data) {
					$scope.user = data;
				
				if($scope.password === $scope.user.password){
			        if($scope.user.role === "admin")
			        	{
			        	    $scope.redirectLink = "http://localhost:8086/health-projectJS/#/admin/" + $scope.user.userId;
			        	}
			        else
			        	{
			    		    $scope.redirectLink = "http://localhost:8086/health-projectJS/#/user/" + $scope.user.userId;
			        	}
			
		    	}
		       else{
					
		    	    $window.alert("The password is not correct.");
					$scope.redirectLink = "http://localhost:8086/health-projectJS/#/";
			 	}
				
				}).error(function(data, status, header, config) {
					 $window.alert("No account found with the given username.");
				});
				
			}

		} ]);

})();