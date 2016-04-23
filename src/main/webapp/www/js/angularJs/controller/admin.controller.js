'use strict';

angular.module('twinkApp')
.controller('AdminCtrl', function($scope,$state,$rootScope, Admin){
	
	$scope.userEventsList = [];
	$scope.rs = {};
	
	Admin.getRestaurantInfo({id:parseInt(localStorage.getItem('adminId'))}).$promise.then(function(result){
		console.log(result);
		$scope.rs.restaurantName = result.restaurantName;
	}, function(err){
		
	});
	
	
	Admin.query({pId:parseInt(localStorage.getItem('adminId'))}).$promise.then(function(result){
		console.log(result);
		$scope.userEventsList = result;
	}, function(err){
		console.log(err.status);
	});
	
	$scope.release = function(id){
		Admin.release({id:id}).$promise.then(function(result){
			$scope.userEventsList = result;
		}, function(err){
			console.log(err.status);
		});
	}
	
	$scope.serve = function(id){
		Admin.serve({id:id}).$promise.then(function(result){
			$scope.userEventsList = result;
		}, function(err){
			console.log(err.status);
		});
	}
	
	$scope.cancel = function(id){
		Admin.cancel({id:id}).$promise.then(function(result){
			$scope.userEventsList = result;
		}, function(err){
			console.log(err.status);
		});
	}
	
	$scope.informNoti = function(id){
		Admin.informNoti({id:id});
	}
	
	$scope.informSms = function(id){
		Admin.informSms({id:id});
	}

	
})
