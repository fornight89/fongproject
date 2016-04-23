'use strict';

angular.module('twinkApp')
    .factory('Admin', function ($resource) {
        return $resource('http://192.168.0.100:8080/twinklexsh/api/getUserList/:pId', {}, {
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
            'release': {
            	method: 'GET',
            	url:'http://192.168.0.100:8080/twinklexsh/api/releaseUser/:id',
            	isArray: true
            },
            'serve': {
            	method: 'GET',
            	url:'http://192.168.0.100:8080/twinklexsh/api/serveUser/:id',
            	isArray: true
            },
            'cancel': {
            	method: 'GET',
            	url:'http://192.168.0.100:8080/twinklexsh/api/cancelUser/:id',
            	isArray: true
            },
            'informNoti': {
            	method: 'GET',
            	url:'http://192.168.0.100:8080/twinklexsh/api/informUserNoti/:id'
            },
            'informSms': {
            	method: 'GET',
            	url:'http://192.168.0.100:8080/twinklexsh/api/informUserSms/:id'
            },
            'getRestaurantInfo': {
            	method: 'GET',
            	url:'http://192.168.0.100:8080/twinklexsh/api/getRsInfo/:id'
            }
            
            
        });
    });
