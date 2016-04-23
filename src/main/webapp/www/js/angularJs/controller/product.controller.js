'use strict';

angular.module('twinkApp')
.controller('ProdCtrl',function($scope,$state,$stateParams,Product,$rootScope){
	
	var getNY = function (value){
		if(value){ 
			return 'Y'; 
		}
		return 'N'; 
	}
	
	localStorage.setItem('userId', 3);

	// 1) check user have any events with "booked" status on today. 
	Product.getUserEventsByUserId({uId:localStorage.getItem('userId')
	}).$promise.then(function(val){
		//console.log(angular.isUndefined(val.userEvents));
		//console.log(JSON.stringify(val));
		if(angular.isUndefined(val.userEvents)){
			$scope.showme = false;
			$scope.hideme = true;
		}else{
			localStorage.setItem('requestResult', JSON.stringify(val));
			console.log(val);
			$scope.message = val;
			$scope.showme= true;
			$scope.hideme = false;
			
			//$scope.message.userRequest.tableSeat = val.userRequest.tableSeat;
			$scope.message.userRequest.smoking = getNY(val.userRequest.smoking);
			$scope.message.userRequest.babychair = getNY(val.userRequest.babychair);
			
			console.log(localStorage.getItem('restaurantName')!=val.userEvents.restaurantDetails.restaurant.restaurantName);
			if(localStorage.getItem('restaurantName')!=val.userEvents.restaurantDetails.restaurant.restaurantName){
				$scope.message.finalResult = "You have joinned : " + val.userEvents.restaurantDetails.restaurant.restaurantName;
			}
			
			
			if(val.inNextPerson < 0){
				//$scope.message.inNextPerson = 0;
				$scope.message.finalResult = "Your seat now is ready : " + val.userEvents.restaurantDetails.restaurant.restaurantName;
			}
			
			if(val.estimateTime <= 0){
				//$scope.message.estimateTime = 0;
			}
			
			
			
		}
	}, function(err){
		
	});

	
	$scope.product = {
		name:localStorage.getItem('restaurantName'),
		shogun:'The best coffee always reserved the best of You.'
	};
	
	$scope.join = function(){

		$scope.userRequest = {};
		$scope.userRequest.tableSeat = 4;
		$scope.userRequest.babychair = false;
		$scope.userRequest.smoking = false;
		$scope.userRequest.restaurantid = localStorage.getItem('restaurantId');
		$scope.userRequest.userid = localStorage.getItem('userId');
		
		Product.registerEvents($scope.userRequest).$promise.then(function(result){
			$scope.message = result;
			$scope.showme = true;
			$scope.hideme = false;
			
			localStorage.setItem('requestResult', JSON.stringify(result)); //JSON.stringify to remove $promise, $resolved
			
			$scope.message.userRequest.smoking = getNY($scope.message.userRequest.smoking);
			$scope.message.userRequest.babychair = getNY($scope.message.userRequest.babychair);
			
			
			if(localStorage.getItem('restaurantName')!=result.userEvents.restaurantDetails.restaurant.restaurantName){
				$scope.message.finalResult = "You have joinned : " + result.userEvents.restaurantDetails.restaurant.restaurantName;
			}

			if(result.inNextPerson < 0){
				$scope.message.inNextPerson = 0;
				$scope.message.finalResult = "Your seat now is ready : " + result.userEvents.restaurantDetails.restaurant.restaurantName;
			}
			
			if(result.estimateTime <= 0){
				$scope.message.estimateTime = 0;
			}
			
			
		}, function(err){
			
		});		

	}
	
	$scope.cancel = function(){

		$scope.haveRequested =	JSON.parse(localStorage.getItem('requestResult')); //parse the [Object object] to {Object object} 
		console.log($scope.haveRequested);

		
		Product.cancelEvents({userEventsId:$scope.haveRequested.userEvents.id}).$promise.then(function(result){
			$scope.showme = false;
			$scope.hideme = true;
			localStorage.removeItem('requestResult');
		}, function(err){
			
		});

	}
	
	
})