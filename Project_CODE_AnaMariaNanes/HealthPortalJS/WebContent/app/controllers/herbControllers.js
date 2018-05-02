(function() {
	
	var herbsModule = angular.module('herbControllers', [ 'ngRoute' ])

	herbsModule.config(function($routeProvider) {
		$routeProvider.when('/herbs/admin', {
			templateUrl : 'app/views/herb/herbs-admin-list.html',
			controller : 'HerbAdminListController',
			controllerAs : "herbAdminListCtrl"
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
	
})();