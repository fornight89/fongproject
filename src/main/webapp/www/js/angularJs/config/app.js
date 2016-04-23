angular.module('twinkApp', ['ui.router','ngResource','ngAnimate', 'ui.bootstrap'])
.config(function($stateProvider, $urlRouterProvider) {
    
    $urlRouterProvider.otherwise('/login');
    

    
    $stateProvider

    	.state('main', {
            url: '/main',
            templateUrl: 'html/main.html',
            controller:'MainCtrl'
        })
        
        .state('login', {
        	url: '/login',
            templateUrl: 'html/login.html',
            controller:'LoginCtrl'
        })
        
        .state('register', {
        	url: '/register',
            templateUrl: 'html/register.html',
            controller:'RegisterCtrl'
        })
        
        .state('admin', {
        	url: '/admin',
            templateUrl: 'html/admin.html',
            controller:'AdminCtrl'
        })
    
    	.state('product', {
    		url: '/product/{name}',
    		templateUrl: 'html/product.html',
    		controller:'ProdCtrl',
    		param:{
    			name:null
    		}
    	});
        
});