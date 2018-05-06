(function() {
	var fruitServices = angular.module('fruitServices', []);

	fruitServices.factory('FruitFactory', [ '$http', 'config', function($http, config) {

		var privateAllFruits = function() {
			return $http.get(config.API_URL + '/product/fruit/all');
		}
		
		var privateFruitDetails = function(fruitId) {
			return $http.get(config.API_URL + '/product/' + fruitId);    
		};
		
		var privateDeleteFruitById = function(fruitId){
			return $http.delete(config.API_URL + '/product/' + fruitId);
		};
		
		var privateUpdateFruit = function(id,data,_config){
			return $http.put(config.API_URL + '/product/' + id, data, _config);
		};
		
		 return {
				      findAll : function() {                 
					      return privateAllFruits();
				          },
		             
		              findFruitById : function(fruitId) {           
				          return privateFruitDetails(fruitId);
			              },
				          
				       deleteFruitById : function(fruitId){         
							return privateDeleteFruitById(fruitId);
				      	},
				      	
						updateFruit : function(id,data,_config){
							return privateUpdateFruit(id,data,_config);
						},
                      
		         };
		  	
			} ]);

})();