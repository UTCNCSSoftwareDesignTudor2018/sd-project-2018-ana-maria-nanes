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
		    var username = "";
		 
		    $scope.SendData = function () {	 
		            
		    	   username = $scope.username;
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

		            $http.post('http://localhost:8081/health-portal' + '/user/added', data, _config)
		            .success(function(){
		            	
		            	
		            	if(($scope.gender!="female" && $scope.gender!="male") || $scope.age <=0 ||
								($scope.role!="user" && $scope.role!="admin")){
		            		$window.alert("An error occured! The account has not been created.");
		            	}
		            	else{
		            		$window.alert("The account has been created.");
		            	}
		            	
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
		        
	            //get the user that was created
		        var promise = UserFactory.findByUsername(username);	
		        var user = null;
				promise.success(function(data) {
					user = data;
					
					$window.alert("userCreated: " + user.username);
					 
					//create shoppingCart
		            var cartVar = {		                
			                productNo : 0,
			                totalCost: 0,  		    
			            };
			        
			            var _config = {
			                headers : {
			                    'Content-Type': 'application/json;charset=utf-8;'
			                }
			            }
			            
			           $http.post('http://localhost:8081/health-portal/shoppingCart/added/' + user.userId  , cartVar, _config)
			            .success(function(){
			            }).error(function(){
			            })	
			            
			        	// create wishList
			            var wishListData = {
			            	wishListId : ""
			            };
			            
			            
				        $http.post('http://localhost:8081/health-portal/wishList/added/' + user.userId  , wishListData, _config)
				            .success(function(){
				            }).error(function(){
				            })	 
				           
				           			            
				         // create RecommendationList
				         var recommendationListData = {
				             recommendedListId : ""	
				        };
				        
				        $http.post('http://localhost:8081/health-portal/recommendationList/added/' + user.userId  , recommendationListData, _config)
			            .success(function(){
			            }).error(function(){
			            })	 
				           		            
				}).error(function(){            	
	            })		        
		    }; // end sendData()	                         
		} ]);

})();