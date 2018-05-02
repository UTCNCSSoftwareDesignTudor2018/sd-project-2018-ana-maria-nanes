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
		} ]);
	
	
})();