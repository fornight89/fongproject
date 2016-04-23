'use strict';

angular.module('twinkApp')
    .factory('Product', function ($resource) {
        return $resource('http://192.168.0.100:8080/twinklexsh/api/getPin', {}, {
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
            'getUserEventsByUserId': {
                method: 'GET',
                url: 'http://192.168.0.100:8080/twinklexsh/api/getUserEvents/:uId',
                params:{
                	uId:'@uId'
                }
//            	,
//                transformResponse: function (data) {
//                	console.log("value is " + data);
//                	return {result:data};
//                }
            },
            'registerEvents': {
                method: 'POST',
                url: 'http://192.168.0.100:8080/twinklexsh/api/registerUserEvents'
            },
            'cancelEvents': {
                method: 'PUT',
                url: 'http://192.168.0.100:8080/twinklexsh/api/cancelUserEvents/:userEventsId',
                params:{userEventsId:'@userEventsId'}
            }
        });
    });
