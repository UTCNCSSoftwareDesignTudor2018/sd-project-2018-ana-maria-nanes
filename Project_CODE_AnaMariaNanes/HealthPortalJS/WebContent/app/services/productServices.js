(function() {
	var productServices = angular.module('productServices', []);

	productServices.factory('ProductFactory', [ '$http', 'config',
			function($http, config) {

				var privateProductById = function(id) {
					return $http.get(config.API_URL + '/product/' + id);
				}

				return {
					findById : function(id) {
						return privateProductById(id);
					},

				};

			} ]);
})();