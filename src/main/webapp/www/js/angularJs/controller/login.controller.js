'use strict';

angular.module('twinkApp')
.controller('LoginCtrl', function($scope,$state,$rootScope,$modal, Register){
	
    $rootScope.data = {};
    $rootScope.account = {};
	
//	document.addEventListener("deviceready", function () {
//			gcm.module.checkDirection(function(res){
//				alert(res.redirect);
//				if(res.redirect){
//					alert(res.url);
//					$state.go(res.url);
//				}
//			}, function(err){
//				alert(err);
//			});
//		}, false);
	
	
	
	$scope.login = function(){
		//$rootScope.account.id = 2;
		//localStorage.setItem('userId', 2);
		//$state.go('main');
		//$state.go('admin');
		Register.login($scope.account).$promise.then(function(result){
			
			if(result.userRole>=99){
				localStorage.setItem('userId', result.id);
				$state.go('main');
			}
			else{
				localStorage.setItem('adminId', result.userRole);
				$state.go('admin');
			}
			
			
			
			
		}, function(err){
			if(err.status==400){
				alert("Failed to authorize");
			}
		})
		
	}
	
	$scope.register = function(){
//		$modal.open({
//		      templateUrl: 'html/a.html',
//		      controller: 'ACtrl'
//		});
		$state.go('register');
	}
	
})
