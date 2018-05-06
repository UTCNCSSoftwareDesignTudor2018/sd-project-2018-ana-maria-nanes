(function() {
	var hospitalServices = angular.module('hospitalServices', []);

	hospitalServices.factory('HospitalFactory', [ '$http', 'config',     
			function($http, config) {

		var privateAllHospitals = function() {
			return $http.get(config.API_URL + '/hospital/all');
		};
		
		var privateHospitalDetails = function(hospitalId) {
			return $http.get(config.API_URL + '/hospital/' + hospitalId);    
		};
		
		var privateDeleteHospitalById = function(hospitalId){
			return $http.delete(config.API_URL + '/hospital/' + hospitalId);
		};
		
		var privateUpdateHospital = function(id,data,_config){
			return $http.put(config.API_URL + '/hospital/' + id, data, _config);
		};
		
		 return {
				      findAll : function() {                 
					      return privateAllHospitals()
				          },
				          
				      findHospitalById : function(hospitalId) {           
					          return privateHospitalDetails(hospitalId);
				              },
					          
					   deleteHospitalById : function(hospitalId){         
								return privateDeleteHospitalById(hospitalId);
					      	},
					   
					   updateHospital : function(id,data,_config){
								return privateUpdateHospital(id,data,_config);
							},
	                      
		         };
		  	
			} ]);

})();