(function() {
	var adminServices = angular.module('adminServices', []);

	adminServices.factory('AdminFactory', [ '$http', 'config',     
			function($http, config) {

				var privateAdmin = function(id) {
					return $http.get(config.API_URL + '/user/' + id); 
				};
				
				var privateDeleteAdminById = function(adminId){
					return $http.delete(config.API_URL + '/user/' + adminId);
				};
				
				var privateUpdateAdmin = function(id,data,_config){
					return $http.put(config.API_URL + '/user/' + id, data, _config);
				};
				
				return {
					  findById : function(id) {                 
						return privateAdmin(id);
					  },
					  
					  updateAdmin : function(id,data,_config){
							return privateUpdateAdmin(id,data,_config);
						},
						
				       deleteAdminById : function(adminId){         
								return privateDeleteAdminById(adminId);
					     },
				      };
			} ]);

})();