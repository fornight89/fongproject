'use strict';

angular.module('twinkApp')
    .factory('Register', function ($resource) {
        return $resource('http://192.168.0.100:8080/twinklexsh/api/register', {}, {
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
            },
            'login': {
                method: 'POST',
                url:'http://192.168.0.100:8080/twinklexsh/api/login'
            }
        });
    });
