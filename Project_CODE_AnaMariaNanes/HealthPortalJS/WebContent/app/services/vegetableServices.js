(function() {
	var vegetableServices = angular.module('vegetableServices', []);

	vegetableServices.factory('VegetableFactory', [ '$http', 'config',     
			function($http, config) {

		var privateAllVegetables = function() {
			return $http.get(config.API_URL + '/product/vegetable/all');
		};
		
		var privateVegetableDetails = function(vegetableId) {
			return $http.get(config.API_URL + '/product/' + vegetableId);    
		};
		
		var privateDeleteVegetableById = function(vegetableId){
			return $http.delete(config.API_URL + '/product/' + vegetableId);
		};
		
		var privateUpdateVegetable = function(id,data,_config){
			return $http.put(config.API_URL + '/product/' + id, data, _config);
		};
		
		
		 return {
				      findAll : function() {                 
					      return privateAllVegetables()
				          },
				          
				       findVegetableById : function(vegetableId) {           
					          return privateVegetableDetails(vegetableId);
				              },
					          
					   deleteVegetableById : function(vegetableId){         
								return privateDeleteVegetableById(vegetableId);
					      	},
					      	
					   updateVegetable : function(id,data,_config){
								return privateUpdateVegetable(id,data,_config);
							},
		         };
		  	
			} ]);

})();