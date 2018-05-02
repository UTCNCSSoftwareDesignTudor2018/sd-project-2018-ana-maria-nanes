(function() {
	var adminServices = angular.module('adminServices', []);

	adminServices.factory('AdminFactory', [ '$http', 'config',     
			function($http, config) {

				var privateAdmin = function(id) {
					return $http.get(config.API_URL + '/user/' + id); 
				};
				
				return {
					findById : function(id) {                 
						return privateAdmin(id);
					  }
				      };
			} ]);

})();