(function() {
	var diseaseServices = angular.module('diseaseServices', []);

	diseaseServices.factory('DiseaseFactory', [ '$http', 'config',     
			function($http, config) {

		var privateAllDiseases = function() {
			return $http.get(config.API_URL + '/disease/all');
		};
		
		var privateDiseaseDetails = function(diseaseId) {
			return $http.get(config.API_URL + '/disease/' + diseaseId);    
		};
		
		var privateDeleteDiseaseById = function(diseaseId){
			return $http.delete(config.API_URL + '/disease/' + diseaseId);
		};
		
		var privateUpdateDisease = function(id,data,_config){
			return $http.put(config.API_URL + '/disease/' + id, data, _config);
		};
		
		 return {
				       findAll : function() {                 
					      return privateAllDiseases()
				          },
				          
				       findDiseaseById : function(diseaseId) {           
					          return privateDiseaseDetails(diseaseId);
				              },
					          
					   deleteDiseaseById : function(diseaseId){         
								return privateDeleteDiseaseById(diseaseId);
					      	},
					      	
					    updateDisease : function(id,data,_config){
								return privateUpdateDisease(id,data,_config);
							},
	                      
		         };
		  	
			} ]);

})();