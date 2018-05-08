(function() {
	
	var herbsModule = angular.module('herbControllers', [ 'ngRoute' ])

	herbsModule.config(function($routeProvider) {
		$routeProvider.when('/herbs/admin', {
			templateUrl : 'app/views/herb/herbs-admin-list.html',
			controller : 'HerbAdminListController',
			controllerAs : "herbAdminListCtrl"
	   }).when('/herb/form', {
			templateUrl : 'app/views/herb/herb-form.html',
			controller : 'HerbAddNewController',
			controllerAs : "herbAddNewCtrl"
		}).when('/herb/admin/:herbId', {
			templateUrl : 'app/views/herb/herb-admin-details.html',
			controller : 'HerbController',
			controllerAs : "herbCtrl"
		}).when('/herb/admin/update/:herbId',{
			templateUrl : 'app/views/herb/herb-update.html',
			controller : 'HerbUpdateController',
			controllerAs : "herbUpdateCtrl"
		}).when('/herb/:herbId/user/:id', {
			templateUrl : 'app/views/herb/herb-user-details.html',
			controller : 'HerbUserDetailsController',
			controllerAs : "herbUserDetailsCtrl"
		})
	   
	});
	
	herbsModule.controller('HerbAdminListController', [ '$scope','$routeParams','$window', 'HerbFactory',
		function($scope,$routeParams, $window, HerbFactory) {
			
		     $scope.herbs = [];
		     $scope.message = "Herbalism is nature's way of healing us.";
		
		     var promise = HerbFactory.findAll();					
		     promise.success(function(data) {
			      $scope.herbs= data;
		     }).error(function(data, status, header, config) {
			      alert(status);
		     });
					
		} ]);
	
	herbsModule.controller('HerbAddNewController', [ '$scope','$http',
		'$routeParams', '$window', 'HerbFactory',
		function($scope,$http, $routeParams, $window, HerbFactory) {

			$scope.message = "Herbalism is nature's way of healing us.";
			
			   $scope.SendData = function () {	 
		            var data = {
	                
		                productName : $scope.productName,
		                type: "herb",
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
		            	$scope.feedbackMessage = "The herb was successfully inserted in the system.";
		            	
		            	$scope.productName = "";
		    		    $scope.benefits = "";
		    		    $scope.distributor = "";
		    		    $scope.price = "";
		    		    $scope.stock = "";
		    		    $scope.diseaseList = "";
		    		    $scope.readMoreLink = ""

		    		    	
		            }).error(function(){
		            	$scope.feedbackMessage = "An error occured! The herb was not inserted in the system!";
		            	
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
	
	  herbsModule.controller('HerbController', [ '$scope', '$window', '$routeParams', 'HerbFactory', 
		 function($scope, $window, $routeParams, HerbFactory) {
				
		        var herbId = $routeParams.herbId;
		        
			    $scope.message = "Herbalism is nature's way of healing us.";
		        
				var promise = HerbFactory.findHerbById(herbId);     
				$scope.herb = null;									
				promise.success(function(data) {
					$scope.herb = data;
				}).error(function(data, status, header, config) {
					alert(status);
				});
				
				$scope.DeleteData = function(){
					  HerbFactory.deleteHerbById(herbId);  
					  $window.alert("The herb has been deleted.");				
					};				
			} ]);
	  
		 herbsModule.controller('HerbUpdateController', [ '$scope', '$routeParams', 'HerbFactory', 
			 function($scope, $routeParams, HerbFactory) {
					
			        var id = $routeParams.herbId;		
			        
				     $scope.message = "Herbalism is nature's way of healing us.";
			        
			        var promise = HerbFactory.findHerbById(id);     
					$scope.herb = null;									
					promise.success(function(data) {
						$scope.herb = data;
					}).error(function(data, status, header, config) {
						alert(status);
					});
				    
				    $scope.UpdateData = function () {	 
			            var data = $scope.herb;
			        
			            var _config = {
			                headers : {
			                    'Content-Type': 'application/json;charset=utf-8;'
			                }
			            }
			            
			           HerbFactory.updateHerb(id,data,_config)
			           .success(function(){
			        	   $scope.feedbackMessage ="The herb info was successfully updated.";
			           })
			           .error(function(){
			        	   $scope.feedbackMessage ="An error occured while updating the herb info.";
			           })
			        };
			    }]);
		 
		  herbsModule.controller('HerbUserDetailsController', [ '$scope', '$window', '$routeParams', 'HerbFactory', 'UserFactory','CartProductFactory',
				 function($scope, $window, $routeParams, HerbFactory, UserFactory, CartProductFactory) {
						
				        var herbId = $routeParams.herbId;
				        
					    $scope.message = "Herbalism is nature's way of healing us.";
				        
						var promise = HerbFactory.findHerbById(herbId);     
						$scope.herb = null;									
						promise.success(function(data) {
							$scope.herb = data;
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
						
						$scope.AddInShoppingCart = function () {	      //add herb
						    var data = null;
							var _config = {
					                headers : {
					                    'Content-Type': 'application/json;charset=utf-8;'
					                }
					            }
					            						
					     CartProductFactory.addProductToCart(herbId,userId,data,_config)
					        .success(function(){
					        	$window.alert("Herb has been added to the shopping cart.");
					         }).error(function(){
					        	$window.alert("An error occured."); 	
					         })		                   
					        };
										
					} ]);
	
	
	
})();