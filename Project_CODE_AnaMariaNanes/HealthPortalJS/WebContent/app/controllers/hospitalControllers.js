(function() {
	
	var hospitalsModule = angular.module('hospitalControllers', [ 'ngRoute' ])

	hospitalsModule.config(function($routeProvider) {
		$routeProvider.when('/hospitals/admin', {
			templateUrl : 'app/views/hospital/hospitals-admin-list.html',
			controller : 'HospitalAdminListController',
			controllerAs : "hospitalAdminListCtrl"
	   }).when('/hospital/form', {
			templateUrl : 'app/views/hospital/hospital-form.html',
			controller : 'HospitalAddNewController',
			controllerAs : "hospitalAddNewCtrl"
		}).when('/hospital/admin/:hospitalId', {
			templateUrl : 'app/views/hospital/hospital-admin-details.html',
			controller : 'HospitalController',
			controllerAs : "hospitalCtrl"
		}).when('/hospital/admin/update/:hospitalId',{
			templateUrl : 'app/views/hospital/hospital-update.html',
			controller : 'HospitalUpdateController',
			controllerAs : "hospitalUpdateCtrl"
		}).when('/hospital/:hospitalId/user/:id', {
			templateUrl : 'app/views/hospital/hospital-user-details.html',
			controller : 'HospitalUserDetailsController',
			controllerAs : "hospitalUserDetailsCtrl"
		})
	   
	});
	
	hospitalsModule.controller('HospitalAdminListController', [ '$scope','$routeParams','$window', 'HospitalFactory',
		function($scope,$routeParams, $window, HospitalFactory) {
			
		     $scope.hospital = [];
		     $scope.message = "Find the best hospital for you.";
		
		     var promise = HospitalFactory.findAll();					
		     promise.success(function(data) {
			      $scope.hospitals= data;
		     }).error(function(data, status, header, config) {
			      alert(status);
		     });
					
		} ]);
	
	hospitalsModule.controller('HospitalAddNewController', [ '$scope','$http',
		'$routeParams', '$window', 'HospitalFactory',
		function($scope,$http, $routeParams, $window, HospitalFactory) {

			$scope.message = "Find the best hospital for you.";
			
			   $scope.SendData = function () {	 
		            var data = {
	                
		                hospitalName : $scope.hospitalName,
		                address: $scope.address,
		    		    website : $scope.website,
		    		    phoneNumber : $scope.phoneNumber,			    		 	    		    
		            };
		        
		            var _config = {
		                headers : {
		                    'Content-Type': 'application/json;charset=utf-8;'
		                }
		            }

		            $http.post('http://localhost:8081/health-portal' + '/hospital/added', data, _config)
		            .success(function(){
		            	$scope.feedbackMessage = "The hospital was successfully inserted in the system.";
		            	
		            	$scope.hospitalName = "";
		    		    $scope.address = "";
		    		    $scope.website = "";
		    		    $scope.phoneNumber = "";
		    		    	
		            }).error(function(){
		            	$scope.feedbackMessage = "An error occured! The hospital was not inserted in the system!";
		            	
		            	$scope.hospitalName = "";
		    		    $scope.address = "";
		    		    $scope.website = "";
		    		    $scope.phoneNumber = "";
		    		    	
		            })
		                   
		        };

		} ]);
	
	  hospitalsModule.controller('HospitalController', [ '$scope', '$window', '$routeParams', 'HospitalFactory', 
		 function($scope, $window, $routeParams, HospitalFactory) {
				
		       $scope.message = "Find the best hospital for you.";
		  
		        var hospitalId = $routeParams.hospitalId;
				var promise = HospitalFactory.findHospitalById(hospitalId);     
				$scope.hospital = null;									
				promise.success(function(data) {
					$scope.hospital = data;
				}).error(function(data, status, header, config) {
					alert(status);
				});
				
				$scope.DeleteData = function(){
					  HospitalFactory.deleteHospitalById(hospitalId);  
					  $window.alert("The hospital has been deleted.");			
					};			
			} ]);
	  
		 hospitalsModule.controller('HospitalUpdateController', [ '$scope', '$routeParams', 'HospitalFactory', 
			 function($scope, $routeParams, HospitalFactory) {
					
			        var id = $routeParams.hospitalId;		
			        
			        $scope.message = "Find the best hospital for you.";
			        
			        var promise = HospitalFactory.findHospitalById(id);     
					$scope.hospital = null;									
					promise.success(function(data) {
						$scope.hospital = data;
					}).error(function(data, status, header, config) {
						alert(status);
					});
				    
				    $scope.UpdateData = function () {	 
			            var data = $scope.hospital;
			        
			            var _config = {
			                headers : {
			                    'Content-Type': 'application/json;charset=utf-8;'
			                }
			            }
			            
			           HospitalFactory.updateHospital(id,data,_config)
			           .success(function(){
			        	   $scope.feedbackMessage ="The hospital info was successfully updated.";
			           })
			           .error(function(){
			        	   $scope.feedbackMessage ="An error occured while updating the hospital info.";
			           })
			        };
			    }]);
		 
		  hospitalsModule.controller('HospitalUserDetailsController', [ '$scope', '$window', '$routeParams', 'HospitalFactory', 'UserFactory',
				 function($scope, $window, $routeParams, HospitalFactory, UserFactory) {
						
				        $scope.message = "Find the best hospital for you.";
				  
				        var hospitalId = $routeParams.hospitalId;
						var promise = HospitalFactory.findHospitalById(hospitalId);     
						$scope.hospital = null;									
						promise.success(function(data) {
							$scope.hospital = data;
						}).error(function(data, status, header, config) {
							alert(status);
						});
						
						var id = $routeParams.id;
						var promise = UserFactory.findById(id);               
						$scope.user = null;									 
						promise.success(function(data) {
							$scope.user = data;
						}).error(function(data, status, header, config) {
							alert(status);
						});	
					} ]);
	
})();