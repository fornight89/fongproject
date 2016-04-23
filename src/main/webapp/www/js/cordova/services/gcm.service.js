cordova.define("gcm.service", function(require, exports, module) {  //first param "gcm.service" is a id for register the event on file www/cordova_plugin.js
var gcmService = {
	    registerGCM: function(successCallback, errorCallback) {
	        cordova.exec(
	            successCallback, 	// success callback function //passed from index.js - it is a function //callBack
	            errorCallback, 		// error callback function //passed from index.js - it is a function //callBack
	            'GCMessaging', 		// mapped to our native Java class called "CalendarPlugin" - it is a Class Name
	            'registerGCM', // with this action name - it is a action name //mapping with Class.execute(actionName, arg, callBack) 
	            [{                  // and this array of custom arguments to create our entry
	                "param1": "enter your param value1 here ",
	                "param2": "enter your param value2 here "
	            }]
	        ); 
	    },
	    getGCMEvent: function(successCallback, errorCallback) {
		   cordova.exec(
		       successCallback, 	// success callback function //passed from index.js - it is a function //callBack
		       errorCallback, 		// error callback function //passed from index.js - it is a function //callBack
		       'GCMessaging', 		// mapped to our native Java class called "CalendarPlugin" - it is a Class Name
		       'getGCMEvent', // with this action name - it is a action name //mapping with Class.execute(actionName, arg, callBack) 
		       [{                  // and this array of custom arguments to create our entry
		           "param1": "enter your param value1 here ",
		           "param2": "enter your param value2 here "
		       }]
		    ); 
		},
		setGCMEvent: function(successCallback, errorCallback, shopName, inNextPerson, currentNumber, requestNumber) {
		    cordova.exec(
		        successCallback, 	// success callback function //passed from index.js - it is a function //callBack
		        errorCallback, 		// error callback function //passed from index.js - it is a function //callBack
		        'GCMessaging', 		// mapped to our native Java class called "CalendarPlugin" - it is a Class Name
		        'setGCMEvent', // with this action name - it is a action name //mapping with Class.execute(actionName, arg, callBack) 
		        [{                  // and this array of custom arguments to create our entry
		            "shopName": shopName,
		            "inNextPerson": inNextPerson,
		            "currentNumber": currentNumber,
		            "requestNumber": requestNumber,
		        }]
		    ); 
		},
		cancelGCMEvent: function(successCallback, errorCallback, shopName) {
		    cordova.exec(
		        successCallback, 	// success callback function //passed from index.js - it is a function //callBack
		        errorCallback, 		// error callback function //passed from index.js - it is a function //callBack
		        'GCMessaging', 		// mapped to our native Java class called "CalendarPlugin" - it is a Class Name
		        'cancelGCMEvent', // with this action name - it is a action name //mapping with Class.execute(actionName, arg, callBack) 
		        [{                  // and this array of custom arguments to create our entry
		            "shopName": shopName
		        }]
		    ); 
		},
		checkDirection: function(successCallback, errorCallback) {
		    cordova.exec(
		        successCallback, 	// success callback function //passed from index.js - it is a function //callBack
		        errorCallback, 		// error callback function //passed from index.js - it is a function //callBack
		        'GCMessaging', 		// mapped to our native Java class called "CalendarPlugin" - it is a Class Name
		        'checkDirection', // with this action name - it is a action name //mapping with Class.execute(actionName, arg, callBack) 
		        [{                  // and this array of custom arguments to create our entry
		            "shopName": ""
		        }]
		    ); 
		}
	}
module.exports = gcmService;
});