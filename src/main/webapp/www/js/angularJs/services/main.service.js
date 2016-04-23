'use strict';

angular.module('twinkApp')
    .factory('MainService', function ($resource) {
        return $resource('http://192.168.0.100:8080/twinklexsh/api/getProductList', {}, {
            'query': { 
            	method: 'GET', 
            	isArray: true
            },
            'get': {
                method: 'GET'
            },
            'update': {
                method: 'PUT'
            },
            'save': {
                method: 'POST'
            }
        });
    });
