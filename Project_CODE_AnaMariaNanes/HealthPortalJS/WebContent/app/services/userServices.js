(function() {
	var userServices = angular.module('userServices', []);

	userServices.factory('UserFactory', [ '$http', 'config',     
			function($http, config) {

				var privateUserById = function(id) {
					return $http.get(config.API_URL + '/user/' + id); 
				};
				
				var privateUserByUsername =  function(username){
					return $http.get(config.API_URL + '/user/account/' + username); 
				};
				
				return {
					findById : function(id) {                 
						return privateUserById(id);
					  },
				
				    findByUsername: function(username){
				    	return privateUserByUsername(username);
				      }
				   
				      };
			} ]);

})();