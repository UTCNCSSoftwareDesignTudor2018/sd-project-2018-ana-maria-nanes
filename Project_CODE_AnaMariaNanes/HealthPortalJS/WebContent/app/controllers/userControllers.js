(function() {
	
	var usersModule = angular.module('userControllers', [ 'ngRoute' ])

	usersModule.config(function($routeProvider) {
		$routeProvider.when('/user/:id', {
			templateUrl : 'app/views/user/user-mainpage.html',
			controller : 'UserController',
			controllerAs : "userCtrl"
	   })
	   
	});
	
	usersModule.controller('UserController', [ '$scope','$routeParams','$window', 'UserFactory',
		function($scope,$routeParams, $window, UserFactory) {
			
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