(function() {

	var homeModule = angular.module('mainControllers', [ 'ngRoute' ])

	homeModule.config(function($routeProvider) {
		$routeProvider.when('/', {
			templateUrl : 'app/views/homeview.html',
			controller : 'HomeController',
			controllerAs : "homeCtrl"
		}).when('/user/form', {
			templateUrl : 'app/views/user/user-form.html',
			controller : 'UserAddNewController',
			controllerAs : "userAddNewCtrl"
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
	
	homeModule.controller('UserAddNewController', [ '$scope','$http', '$window', '$routeParams', '$window', 'UserFactory',
		function($scope, $http, $window, $routeParams, $window, UserFactory) {
			
		    $scope.message = "WELCOME!";		  
		 
		    $scope.SendData = function () {	 
		            var data = {
	                
		                name : $scope.name,
		                username: $scope.username,
		    		    password : $scope.password,
		    		    address : $scope.address,			    		 
		    		    age : $scope.age,
		    		    gender : $scope.gender,
		    		    role : $scope.role,	    		    
		            };
		        
		            var _config = {
		                headers : {
		                    'Content-Type': 'application/json;charset=utf-8;'
		                }
		            }

		            $http.post('http://localhost:8080/health-portal' + '/user/added', data, _config)
		            .success(function(){
		            	$window.alert("The account has been created.");
		            	
		            	$scope.name = "";
		    		    $scope.username = "";
		    		    $scope.password = "";
		    		    $scope.address = "";
		    		    $scope.age = "";
		    		    $scope.gender = "";
		    		    $scope.role = ""
	    		    	
		            }).error(function(){
		            	$window.alert("An error occured! The account has not been created.");
		            	
		            	$scope.name = "";
		    		    $scope.username = "";
		    		    $scope.password = "";
		    		    $scope.address = "";
		    		    $scope.age = "";
		    		    $scope.gender = "";
		    		    $scope.role = ""		    		    	
		            })		                   
		        };
		} ]);

})();