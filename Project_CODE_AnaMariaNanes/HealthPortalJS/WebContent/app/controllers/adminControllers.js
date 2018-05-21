(function() {
	
	var adminsModule = angular.module('adminControllers', [ 'ngRoute' ])

	adminsModule.config(function($routeProvider) {
		$routeProvider.when('/admin/:id', {
			templateUrl : 'app/views/admin/admin-mainpage.html',
			controller : 'AdminController',
			controllerAs : "adminCtrl"
	   })
	});
	
	adminsModule.controller('AdminController', [ '$scope', '$window', '$routeParams', 'AdminFactory',
		function($scope, $window, $routeParams, AdminFactory) {
			
	        var id = $routeParams.id;
	        
			var promise = AdminFactory.findById(id);               
			$scope.admin = null;									 
			promise.success(function(data) {
				$scope.admin = data;
			}).error(function(data, status, header, config) {
				alert(status);
			});		
			
		    $scope.UpdateData = function () {	 
		          
		    	var data = $scope.admin;
		        
		            var _config = {
		                headers : {
		                    'Content-Type': 'application/json;charset=utf-8;'
		                }
		            }
		            
		           AdminFactory.updateAdmin(id,data,_config)
		           .success(function(){
		        	   
		        	   if(($scope.admin.gender!="female" && $scope.admin.gender!="male") || $scope.admin.age <=0){
		        		   $scope.feedbackMessage ="An error occured while updating the admin account info.";
		        	   }
		        	   else{
		               	   $scope.feedbackMessage ="The admin account info was successfully updated.";
		        	   }
		 
		           })
		           .error(function(){
		        	   $scope.feedbackMessage ="An error occured while updating the admin account info.";
		           })
		        };
		        
		     $scope.DeleteData = function(){
					  AdminFactory.deleteAdminById($scope.admin.userId);  
					  $window.alert("The account has been deleted.");	
					  
					};	
			
			
		} ]);
	
	
})();