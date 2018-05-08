(function() {
	
	var vegetablesModule = angular.module('vegetableControllers', [ 'ngRoute' ])

	vegetablesModule.config(function($routeProvider) {
		$routeProvider.when('/vegetables/admin', {
			templateUrl : 'app/views/vegetable/vegetables-admin-list.html',
			controller : 'VegetableAdminListController',
			controllerAs : "vegetableAdminListCtrl"
	   }).when('/vegetable/form', {
			templateUrl : 'app/views/vegetable/vegetable-form.html',
			controller : 'VegetableAddNewController',
			controllerAs : "vegetableAddNewCtrl"
		}).when('/vegetable/admin/:vegetableId', {
			templateUrl : 'app/views/vegetable/vegetable-admin-details.html',
			controller : 'VegetableController',
			controllerAs : "vegetableCtrl"
		}).when('/vegetable/admin/update/:vegetableId',{
			templateUrl : 'app/views/vegetable/vegetable-update.html',
			controller : 'VegetableUpdateController',
			controllerAs : "vegetableUpdateCtrl"
		}).when('/vegetable/:vegetableId/user/:id', {
			templateUrl : 'app/views/vegetable/vegetable-user-details.html',
			controller : 'VegetableUserDetailsController',
			controllerAs : "vegetableDetailsCtrl"
		})   
	});
	
	vegetablesModule.controller('VegetableAdminListController', [ '$scope','$routeParams','$window', 'VegetableFactory',
		function($scope,$routeParams, $window, VegetableFactory ) {
			
		
	     	 $scope.vegetables = [];
		     $scope.message = "Vegetables are the guide to a healthy life.";
		
		     var promise = VegetableFactory.findAll();					
		     promise.success(function(data) {
			      $scope.vegetables= data;
		     }).error(function(data, status, header, config) {
			      alert(status);
		     });
					
		} ]);
	
	vegetablesModule.controller('VegetableAddNewController', [ '$scope','$http',
		'$routeParams', '$window', 'VegetableFactory',
		function($scope,$http, $routeParams, $window, VegetableFactory) {

			$scope.message = "Vegetables are the guide to a healthy life.";
			
			   $scope.SendData = function () {	 
		            var data = {
	                
		                productName : $scope.productName,
		                type: "vegetable",
		    		    benefits : $scope.benefits,
		    		    distributor : $scope.distributor,			    		 
		    		    price : $scope.price,
		    		    stock : $scope.stock,
		    		    diseaseList : $scope.diseaseList,
		    		    readMoreLink : $scope.readMoreLink,			    		    
		            };
		        
		            var _config = {
		                headers : {
		                    'Content-Type': 'application/json;charset=utf-8;'
		                }
		            }

		            $http.post('http://localhost:8080/health-portal' + '/product/added', data, _config)
		            .success(function(){
		            	$scope.feedbackMessage = "The vegetable was successfully inserted in the system.";
		            	
		            	$scope.productName = "";
		    		    $scope.benefits = "";
		    		    $scope.distributor = "";
		    		    $scope.price = "";
		    		    $scope.stock = "";
		    		    $scope.diseaseList = "";
		    		    $scope.readMoreLink = ""

		    		    	
		            }).error(function(){
		            	$scope.feedbackMessage = "An error occured! The vegetable was not inserted in the system!";
		            	
		            	$scope.productName = "";
		    		    $scope.benefits = "";
		    		    $scope.distributor = "";
		    		    $scope.price = "";
		    		    $scope.stock = "";
		    		    $scope.diseaseList = "";
		    		    $scope.readMoreLink = ""
		    		    	
		            })
		                   
		        };

		} ]);
	
	  vegetablesModule.controller('VegetableController', [ '$scope', '$window', '$routeParams', 'VegetableFactory', 
			 function($scope, $window, $routeParams, VegetableFactory) {
					
			        var vegetableId = $routeParams.vegetableId;
					var promise = VegetableFactory.findVegetableById(vegetableId);     
					$scope.vegetable = null;									
					promise.success(function(data) {
						$scope.vegetable = data;
					}).error(function(data, status, header, config) {
						alert(status);
					});
					
					$scope.DeleteData = function(){
						  VegetableFactory.deleteVegetableById(vegetableId);  
						  $window.alert("The vegetable has been deleted.");				
						};	
				} ]);
	  
		 vegetablesModule.controller('VegetableUpdateController', [ '$scope', '$routeParams', 'VegetableFactory', 
			 function($scope, $routeParams, VegetableFactory) {
					
			        var id = $routeParams.vegetableId;		
			        
			        $scope.message = "Vegetable is nature's candy.";
			        
			        var promise = VegetableFactory.findVegetableById(id);     
					$scope.vegetable = null;									
					promise.success(function(data) {
						$scope.vegetable = data;
					}).error(function(data, status, header, config) {
						alert(status);
					});
								    
				    $scope.UpdateData = function () {	 
			            var data = $scope.vegetable;
			        
			            var _config = {
			                headers : {
			                    'Content-Type': 'application/json;charset=utf-8;'
			                }
			            }
			            
			           VegetableFactory.updateVegetable(id,data,_config)
			           .success(function(){
			        	   $scope.feedbackMessage ="The vegetable info was successfully updated.";
			           })
			           .error(function(){
			        	   $scope.feedbackMessage ="An error occured while updating the vegetable info.";
			           })
			        };
			    }]);
		 
		 vegetablesModule.controller('VegetableUserDetailsController', [ '$scope', '$window', '$routeParams', 'VegetableFactory', 'UserFactory','CartProductFactory',
			 function($scope, $window, $routeParams, VegetableFactory, UserFactory, CartProductFactory) {
					
			        var vegetableId = $routeParams.vegetableId;
					var promise = VegetableFactory.findVegetableById(vegetableId);     
					$scope.vegetable = null;									
					promise.success(function(data) {
						$scope.vegetable = data;
					}).error(function(data, status, header, config) {
						alert(status);
					});
						
					var userId = $routeParams.id;
					var promise = UserFactory.findById(userId);               
					$scope.user = null;									 
					promise.success(function(data) {
						$scope.user = data;
					}).error(function(data, status, header, config) {
						alert(status);
					});	
					
					$scope.AddInShoppingCart = function () {	      //add vegetable
					    var data = null;
						var _config = {
				                headers : {
				                    'Content-Type': 'application/json;charset=utf-8;'
				                }
				            }
				            						
				     CartProductFactory.addProductToCart(vegetableId,userId,data,_config)
				        .success(function(){
				        	$window.alert("Vegetable has been added to the shopping cart.");
				         }).error(function(){
				        	$window.alert("An error occured."); 	
				         })		                   
				        };
				} ]);
	  
	
})();