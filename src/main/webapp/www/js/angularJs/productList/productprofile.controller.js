angular.module("ProductProfileApp", [])
.controller("ProductProfileCtrl", [ '$scope','$timeout', function($scope,$timeout) {
	
	$scope.btnValue = "Join Now";
	$scope.myvar = 0;
	
	$scope.join = function(){

		if(angular.element(logoShop).hasClass('animationMove')){
			angular.element(logoShop).removeClass('hideIt');
			angular.element(logoShop).removeClass('animationMove');
			angular.element(logoShop).addClass('showIt');
			angular.element(logoShop).addClass('topDiv');
			angular.element(progressBar).removeClass('progressBarShow');
			angular.element(progressBar).addClass('progressBarHide');
			angular.element(info).removeClass('animationMove3');
			angular.element(shopName).removeClass('showIt');
			angular.element(shopName).removeClass('animationMoveShopName');
			angular.element(shopName).addClass('hideIt');
			angular.element(shopLogo).removeClass('animationMoveShopLogo');
			angular.element(shopLogo).removeClass('showIt');
			angular.element(shopLogo).addClass('hideIt');
			$scope.myvar = 0;
			$scope.btnValue = "Join Now";
		}
		else{
			angular.element(logoShop).removeClass('topDiv');
			angular.element(logoShop).removeClass('showIt');
			angular.element(logoShop).addClass('animationMove');
			angular.element(logoShop).addClass('hideIt');
			$timeout(function() {
			angular.element(shopName).removeClass('hideIt');
			angular.element(shopName).addClass('showIt');
			angular.element(shopName).addClass('animationMoveShopName');
			angular.element(shopLogo).removeClass('hideIt');
			angular.element(shopLogo).addClass('animationMoveShopLogo');
			angular.element(shopLogo).addClass('showIt');
			angular.element(progressBar).removeClass('progressBarHide');
			angular.element(progressBar).addClass('progressBarShow');
			angular.element(progressBar).addClass('animationMove2');
			angular.element(info).addClass('animationMove3');
			
			
			
			$('.dial').trigger('configure',{"min":0,"max":100,'draw': function(){$(this.i).css('font-family', 'orbiton');}});
			
			var randomnumber = 0;
			
			var trig = function(randomnumber){
				if(randomnumber <= 75){ 
					$('.dial').val(randomnumber).trigger('change');
				}
				else{
					
					clearInterval(trig);
				}
			}
			
			setInterval(function() {
			      //var randomnumber = Math.round(Math.random() * 100);
			      randomnumber = randomnumber + 1;
			      trig(randomnumber);
			}, 25);
			
			},2000);
			
			$scope.btnValue = "Cancel Request";
			$scope.myvar = 1;
			
		}
	}
	
	
	
}]);