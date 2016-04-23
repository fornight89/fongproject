angular.module('twinkApp')
.controller('RegisterCtrl', function($scope,$state, Register){
	
	$scope.error = {};
	
	$scope.create = function(){
		
		Register.save($scope.account).$promise.then(function(result){
			$state.go('main');
		}, function(err){
			if(err.status===400){
				$scope.error.showme = true;
				$scope.error.msg = "Already Exists";
			}
		});
	}
	
	$scope.back = function(){
		$state.go('login');
	}
	
})