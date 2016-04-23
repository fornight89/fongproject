angular.module("ProductApp", [])
.controller("ProductCtrl", [ '$scope', function($scope) {
	
	var products = [{
		name:'StarBucks',
		hours:'08:00a.m. - 12:00a.m.',
		location:'Cheras',
		price:'MYR 20 and above',
		image:'sb.png'
	},{
		name:'Jogoya',
		hours:'08:00a.m. - 12:00a.m.',
		location:'Cheras',
		price:'MYR 20 and above',
		image:'jy.png'
	},{
		name:'Beer Factory',
		hours:'08:00a.m. - 12:00a.m.',
		location:'Cheras',
		price:'MYR 20 and above',
		image:'bf.png'
	},{
		name:'Home Town',
		hours:'08:00a.m. - 12:00a.m.',
		location:'Cheras',
		price:'MYR 20 and above',
		image:'ht.png'
	},{
		name:'Paparich',
		hours:'08:00a.m. - 12:00a.m.',
		location:'Cheras',
		price:'MYR 20 and above',
		image:'pr.png'
	},{
		name:'Sushi Zamai',
		hours:'08:00a.m. - 12:00a.m.',
		location:'Cheras',
		price:'MYR 20 and above',
		image:'sz.png'
	}];
	
	$scope.formData = {};
	$scope.formData.products = products;
	
}]);