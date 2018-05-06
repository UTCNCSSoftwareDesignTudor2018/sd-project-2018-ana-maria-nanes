(function() {
	
	var diseasesModule = angular.module('diseaseControllers', [ 'ngRoute' ])

	diseasesModule.config(function($routeProvider) {
		$routeProvider.when('/diseases/admin', {
			templateUrl : 'app/views/disease/diseases-admin-list.html',
			controller : 'DiseaseAdminListController',
			controllerAs : "diseaseAdminListCtrl"
	   }).when('/disease/form', {
			templateUrl : 'app/views/disease/disease-form.html',
			controller : 'DiseaseAddNewController',
			controllerAs : "diseaseAddNewCtrl"
		}).when('/disease/admin/:diseaseId', {
			templateUrl : 'app/views/disease/disease-admin-details.html',
			controller : 'DiseaseController',
			controllerAs : "diseaseCtrl"
		}).when('/disease/admin/update/:diseaseId',{
			templateUrl : 'app/views/disease/disease-update.html',
			controller : 'DiseaseUpdateController',
			controllerAs : "diseaseUpdateCtrl"
		})
	   
	});
	
	diseasesModule.controller('DiseaseAdminListController', [ '$scope','$routeParams','$window', 'DiseaseFactory',
		function($scope,$routeParams, $window, DiseaseFactory) {
			
		     $scope.diseases = [];
		     $scope.message = "Any kind of disease is to be treated accordingly.";
		
		     var promise = DiseaseFactory.findAll();					
		     promise.success(function(data) {
			      $scope.diseases = data;
		     }).error(function(data, status, header, config) {
			      alert(status);
		     });
					
		} ]);
	
	diseasesModule.controller('DiseaseAddNewController', [ '$scope','$http',
		'$routeParams', '$window', 'DiseaseFactory',
		function($scope,$http, $routeParams, $window, DiseaseFactory) {

			$scope.message = "Any kind of disease is to be treated accordingly.";
			
			   $scope.SendData = function () {	 
		            var data = {
	                
		                diseaseName : $scope.diseaseName,
		                cause: $scope.cause ,
		    		    sympthoms : $scope.sympthoms ,
		    		    risks : $scope.risks,
		    		    wikiLink : $scope.wikiLink,
		            };
		        
		            var _config = {
		                headers : {
		                    'Content-Type': 'application/json;charset=utf-8;'
		                }
		            }

		            $http.post('http://localhost:8080/health-portal' + '/disease/added', data, _config)
		            .success(function(){
		            	$scope.feedbackMessage = "The disease was successfully inserted in the system.";
		            	
		            	$scope.diseaseName = "";
		    		    $scope.cause = "";
		    		    $scope.sympthoms = "";
		    		    $scope.risks = "";
		    		    $scope.wikiLink = "";
		    		    	
		            }).error(function(){
		            	$scope.feedbackMessage = "An error occured! The disease was not inserted in the system!";		            	
		            	$scope.diseaseName = "";
		    		    $scope.cause = "";
		    		    $scope.sympthoms = "";
		    		    $scope.risks = "";
		    		    $scope.wikiLink = "";
		    		    	
		            })		                   
		        };
		} ]);
	
	 diseasesModule.controller('DiseaseController', [ '$scope', '$window', '$routeParams', 'DiseaseFactory', 
		 function($scope, $window, $routeParams, DiseaseFactory) {
				
		        $scope.message = "Any kind of disease is to be treated accordingly.";

		        var diseaseId = $routeParams.diseaseId;
				var promise = DiseaseFactory.findDiseaseById(diseaseId);     
				$scope.disease = null;									
				promise.success(function(data) {
					$scope.disease = data;
				}).error(function(data, status, header, config) {
					alert(status);
				});
				
				$scope.DeleteData = function(){
					  DiseaseFactory.deleteDiseaseById(diseaseId);  
					  $window.alert("The disease has been deleted.");			
					};
							
			} ]);
	 
	 diseasesModule.controller('DiseaseUpdateController', [ '$scope', '$routeParams', 'DiseaseFactory', 
		 function($scope, $routeParams, DiseaseFactory) {
				
		        var id = $routeParams.diseaseId;		
		        
		        $scope.message = "Any kind of disease is to be treated accordingly.";
		        
		        var promise = DiseaseFactory.findDiseaseById(id);     
				$scope.disease = null;									
				promise.success(function(data) {
					$scope.disease = data;
				}).error(function(data, status, header, config) {
					alert(status);
				});
			    
			    $scope.UpdateData = function () {	 
		            var data = $scope.disease;
		        
		            var _config = {
		                headers : {
		                    'Content-Type': 'application/json;charset=utf-8;'
		                }
		            }	            
		           DiseaseFactory.updateDisease(id,data,_config)
		           .success(function(){
		        	   $scope.feedbackMessage ="The disease info was successfully updated.";
		           })
		           .error(function(){
		        	   $scope.feedbackMessage ="An error occured while updating the disease info.";
		           })
		        };
		    }]);
	
})();