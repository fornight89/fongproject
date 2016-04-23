angular.module('twinkApp')
.directive('backImg', function(){
    return function(scope, element, attrs){
        var url = attrs.backImg;
        element.css({
            'background-image': 'url(' + url +')',
            'background-size' : 'contain'
        });
    };
})
.directive('itemImg', function(){
	return function(scope, element, attrs){
        var url = attrs.itemImg;
        element.css({
            'background-image': 'url(' + url +')',
            'background-size' : 'cover',
            'background-position' : 'center'
        });
    };
})
.directive('afterRequest', function(){
    return function(scope, element, attrs){
        var url = attrs.backImg;
        element.css({
            'background-color': 'transparent !important',
            'color' : 'white',
            'border': '3px solid white'
        });
    };
})
.directive('beforeRequest', function(){
    return function(scope, element, attrs){
        var url = attrs.backImg;
        element.css({
            'background-color': 'transparent !important',
            'color' : 'red',
            'border': '3px solid black'
        });
    };
})
;