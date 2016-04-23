angular.module('twinkApp')
.controller('MainCtrl', function($scope,$state, $rootScope,MainService){
	
	
	$scope.productList = [];
	
	MainService.query().$promise.then(function(val){
		//console.log(JSON.parse(angular.toJson(val)));
		//console.log(val);
		$scope.products = val;
		
		$scope.formData = {};
		$scope.formData.products = $scope.products;
	},
	function(val){
		console.log("sorry");
//		$scope.products = [{
//		restaurantName:'StarBucks',
//		hours:'08:00a.m. - 12:00a.m.',
//		location:'Cheras',
//		price:'MYR 20 and above',
//		imageItem:'././img/sbProfile.png'		
//		image:'././img/sbbg.png',
//	},{
//		restaurantName:'Jogoya',
//		hours:'08:00a.m. - 12:00a.m.',
//		location:'Cheras',
//		price:'MYR 20 and above',
//		imageItem:'././img/jg.png',
//		image:'././img/jgbg.png'
//	},{
//		restaurantName:'Beer Factory',
//		hours:'08:00a.m. - 12:00a.m.',
//		location:'Cheras',
//		price:'MYR 20 and above',
//		imageItem:'././img/bfProfile.png',
//		image:'././img/bfbg.png'
//	},{
//		restaurantName:'Dragon-I',
//		hours:'08:00a.m. - 12:00a.m.',
//		location:'Cheras',
//		price:'MYR 20 and above',
//		imageItem:'././img/diProfile.png',
//		image:'././img/ldcrbg.png'
//	},{
//		restaurantName:'Paparich',
//		hours:'08:00a.m. - 12:00a.m.',
//		location:'Cheras',
//		price:'MYR 20 and above',
//		imageItem:'././img/papaProfile.png',
//		image:'././img/papabg.png'
//	},{
//		restaurantName:'Sushi Zamai',
//		hours:'08:00a.m. - 12:00a.m.',
//		location:'Cheras',
//		price:'MYR 20 and above',
//		imageItem:'././img/szProfile.png',
//		image:'././img/szbg.png'
//	},{
//		restaurantName:'StarBucks',
//		hours:'08:00a.m. - 12:00a.m.',
//		location:'Cheras',
//		price:'MYR 20 and above',
//		image:'././img/sbbg.png',
//		imgItem:'././img/sbProfile.png'
//	}]
	});
	

	
	

	$scope.redic = function(name, id){
		//$rootScope.data.restaurantName = name;
		//$rootScope.data.restaurantId = id;
		localStorage.setItem('restaurantName', name);
		localStorage.setItem('restaurantId', id);
		$state.go('product', {name:name});
	}
	
})