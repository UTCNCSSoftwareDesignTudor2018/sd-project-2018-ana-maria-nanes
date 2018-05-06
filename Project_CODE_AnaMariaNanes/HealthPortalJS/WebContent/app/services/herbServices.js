(function() {
	var herbServices = angular.module('herbServices', []);

	herbServices.factory('HerbFactory', [ '$http', 'config',     
			function($http, config) {

		var privateAllHerbs = function() {
			return $http.get(config.API_URL + '/product/herb/all');
		};
		
		var privateHerbDetails = function(herbId) {
			return $http.get(config.API_URL + '/product/' + herbId);    
		};
		
		var privateDeleteHerbById = function(herbId){
			return $http.delete(config.API_URL + '/product/' + herbId);
		};
		
		var privateUpdateHerb = function(id,data,_config){
			return $http.put(config.API_URL + '/product/' + id, data, _config);
		};
		
		 return {
				      findAll : function() {                 
					      return privateAllHerbs()
				          },
				          
				       findHerbById : function(herbId) {           
					          return privateHerbDetails(herbId);
				              },
					          
					   deleteHerbById : function(herbId){         
								return privateDeleteHerbById(herbId);
					          },
					   
				       updateHerb : function(id,data,_config){
								return privateUpdateHerb(id,data,_config);
							},
	                      
		         };
		  	
			} ]);

})();